package news.factory.com.base.view_holders.home.HomeFrontPageCellTitle;

import news.factory.com.model.single.TopNews;

public class HomeFrontPageTitleData {
    private String title;

//TODO
    public HomeFrontPageTitleData(TopNews news) {
       title = news.getName();
    }

    public String getTitle() {
        return title;
    }
}
