package news.factory.com.base.view_holders.home.HomeFrontPagePager;

import news.factory.com.model.single.Articles;

public class HomeFrontPageData {
    Articles articles;

    public HomeFrontPageData(Articles articles) {
        this.articles = articles;
    }

    public Articles getArticles() {
        return articles;
    }
}
