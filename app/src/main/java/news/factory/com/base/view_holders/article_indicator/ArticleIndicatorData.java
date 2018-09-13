package news.factory.com.base.view_holders.article_indicator;

public class ArticleIndicatorData {
    private String numberOfPages;
    private String currentPage;

    public ArticleIndicatorData(String numberOfPages,String currentPage) {
        this.numberOfPages = numberOfPages;
        this.currentPage = currentPage;
    }

    public String getIndicatorString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numberOfPages);
        sb.append("/");
        sb.append(currentPage);
        return sb.toString();
    }
}
