package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.EventService;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;

@WebServlet(name = "EventListServlet", urlPatterns = "")
@ServletComponentScan
public class EventListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;
    public EventListServlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JakartaServletWebApplication application = JakartaServletWebApplication.buildApplication(req.getServletContext());
        WebContext context = new WebContext(application.buildExchange(req, resp));

        context.setVariable("events", eventService.listAll());

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
