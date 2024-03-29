package mk.ukim.finki.NP.ZadaciZaVezbanje.FrontPage;

import java.util.Date;

public class TextNewsItem extends NewsItem {
    private String text;
    public TextNewsItem(String title, Date date, Category category, String text) {
        super(title, date, category);
        this.text = text;
    }

    public String getTeaser() {
        String ret = "";
        ret += getTitle() + "\n";
        ret += getMinutes() + "\n";
        if (text.length() > 80) {
            String tmp = text.substring(0, 80);
            ret += tmp + "\n";
        } else
            ret += text + "\n";

        return ret;
    }
}
