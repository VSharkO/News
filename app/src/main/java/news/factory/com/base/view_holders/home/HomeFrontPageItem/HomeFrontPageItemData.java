package news.factory.com.base.view_holders.home.HomeFrontPageItem;

import news.factory.com.model.single.TopNews;

public class HomeFrontPageItemData {
    private TopNews news;

    public HomeFrontPageItemData(TopNews news) {
        this.news = news;
    }

    public String getTitle() {
        return news.getName();
    }

    public TopNews getNews() {
        return news;
    }
}
