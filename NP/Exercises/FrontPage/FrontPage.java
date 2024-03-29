package mk.ukim.finki.NP.ZadaciZaVezbanje.FrontPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FrontPage {
    private ArrayList<NewsItem> news;
    private Category[] categories;

    public FrontPage(Category[] categories) {
        this.categories = categories;
        this.news = new ArrayList<>();
    }

    public void addNewsItem(NewsItem newsItem) {
        news.add(newsItem);
    }

    public List<NewsItem> listByCategory(Category category) {
        List<NewsItem> list = new ArrayList<>();
        for (NewsItem newsItem : news) {
            if (newsItem.getCategory().equals(category)) {
                list.add(newsItem);
            }
        }

        return list;
    }

    public List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        int flag = 0;
        for (Category value : categories) {
            if (value.getName().compareTo(category) == 0) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            throw new CategoryNotFoundException(category);
        } else
            return listByCategory(new Category(category));
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < news.size(); i++) {
            ret += news.get(i).getTeaser();
        }

        return ret;
    }
}
