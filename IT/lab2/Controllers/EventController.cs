using MVC_lab.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace MVC_lab.Controllers
{
    public class EventController : Controller
    {
        private static List<EventModel> events = new List<EventModel>()
        {
            new EventModel() { Id = 1, Name = "Event", Location = "Macedonia"},
            new EventModel() { Id = 2, Name = "Event2", Location = "England"}
        };

        // GET: Event
        public ActionResult Index()
        {
            return View(events);
        }

        public ActionResult DisplayAll()
        {
            return View(events);
        }

        public ActionResult ShowEvent(int id)
        {
            return View(events[id]);
        }

        public ActionResult NewEvent()
        {
            EventModel model = new EventModel();
            return View(model);
        }

        [HttpPost]
        public ActionResult InsertNewEvent(EventModel model)
        {
            if(!ModelState.IsValid)
            {
                return View("NewEvent", model);
            }

            if(events.Count > 0)
            {
                model.Id = events.Last().Id + 1;
            } else
            {
                model.Id = 1;
            }

            events.Add(model);
            return View("ShowEvent", model);
        }

        public ActionResult EditEvent(int id)
        {
            var model = events.FirstOrDefault(e => e.Id == id);
            if (model == null)
            {
                return HttpNotFound();
            }
            return View(model);
        }

        [HttpPost]
        public ActionResult EditEvent(EventModel model)
        {
            if (!ModelState.IsValid)
            {
                return View("EditEvent", model.Id);
            }
            var forEdit = events.FirstOrDefault(e=>e.Id == model.Id);
            if (forEdit == null)
            {
                return HttpNotFound();
            }
            forEdit.Name = model.Name;
            forEdit.Location = model.Location;
            return View("DisplayAll", events);
        }

        public ActionResult DeleteEvent(int id)
        {
            events.Remove(events.FirstOrDefault(e=>e.Id == id));
            return View("DisplayAll", events);
        }
    }
}