package news.factory.com.base.view_holders.single.inner_cell_holder;

import news.factory.com.model.single.Articles;

public class InnerCellData {
    Articles articles;

    public InnerCellData(Articles articles) {
        this.articles = articles;
    }

    public Articles getArticles() {
        return articles;
    }
}
