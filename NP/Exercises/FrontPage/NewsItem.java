package mk.ukim.finki.NP.ZadaciZaVezbanje.FrontPage;

import java.util.Date;

public abstract class NewsItem {
    private String title;
    private Date date;
    private Category category;

    public NewsItem(String title, Date date, Category category) {
        this.title = title;
        this.date = date;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public long getMinutes() {
        Date now = new Date();

        long mins = now.getTime() - date.getTime();
        mins /= 60000;

        return mins;
    }

    public Category getCategory() {
        return category;
    }

    public abstract String getTeaser();
}
