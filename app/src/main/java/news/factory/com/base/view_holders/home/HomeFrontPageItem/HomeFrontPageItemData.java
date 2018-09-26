package news.factory.com.base.view_holders.home.HomeFrontPageItem;

import java.util.List;

import news.factory.com.model.single.TopNews;

public class HomeFrontPageItemData {

    private List<TopNews> news;

    public HomeFrontPageItemData(List<TopNews> news) {
        this.news = news;
    }

    public String getTitle(int index) {
        return news.get(index).getName();
    }

    public List<TopNews> getNews() {
        return news;
    }
}
