package news.factory.com.base.view_holders.article_indicator;

import news.factory.com.App;
import news.factory.com.R;

public class ArticleIndicatorData {
    private String numberOfPages;
    private String currentPage;

    public ArticleIndicatorData(String numberOfPages,String currentPage) {
        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
    }

    public String getIndicatorString() {
//        return App.getInstance().getString(R.string.indicator,currentPage,numberOfPages);
        return null;
    }
}
