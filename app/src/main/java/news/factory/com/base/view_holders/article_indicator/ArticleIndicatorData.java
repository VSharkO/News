package news.factory.com.base.view_holders.article_indicator;

public class ArticleIndicatorData {
    private String indicatorText;

    public ArticleIndicatorData(String numberOfPages,String currentPage) {
       indicatorText = numberOfPages + "/" + currentPage;
    }

    public String getIndicatorString() {
        return indicatorText;
    }
}
