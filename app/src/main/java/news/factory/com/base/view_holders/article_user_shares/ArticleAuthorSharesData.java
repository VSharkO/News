package news.factory.com.base.view_holders.article_user_shares;

public class ArticleAuthorSharesData {

    private String author;
    private String shares;

    public ArticleAuthorSharesData(String user, String shares) {
        this.author = user;
        this.shares = shares;
    }

    public String getAuthor() {
        return author;
    }

    public String getShares() {
        return shares;
    }
}
