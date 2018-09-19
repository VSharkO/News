package news.factory.com.base.view_holders.inner_cell_holder;

import news.factory.com.model.Articles;
import news.factory.com.model.TopNews;

public class InnerCellData {
    Articles articles;

    public InnerCellData(Articles articles) {
        this.articles = articles;
    }

    public Articles getArticles() {
        return articles;
    }
}
