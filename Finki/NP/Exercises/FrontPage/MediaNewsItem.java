package mk.ukim.finki.NP.ZadaciZaVezbanje.FrontPage;

import java.util.Date;

public class MediaNewsItem extends NewsItem {
    private String url;
    private int views;
    public MediaNewsItem(String title, Date date, Category category, String url, int views) {
        super(title, date, category);
        this.url = url;
        this.views = views;
    }

    public String getTeaser() {
        String ret = "";
        ret += getTitle() + "\n";
        ret += getMinutes() + "\n";
        ret += url + "\n";
        ret += views + "\n";

        return ret;
    }
}