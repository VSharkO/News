package news.factory.com.base.view_holders.single.article_header;

public class ArticleHeaderData {
    private String image;
    private String category;
    private String source;
    private String caption;
    private boolean isThereImage = false;

    public ArticleHeaderData(String image, String category, String source, String caption) {
        this.image = image;
        this.category = category;
        this.source = source;
        this.caption = caption;
        isThereImage = true;
    }

    public ArticleHeaderData(String category, String source, String caption) {
        this.category = category;
        this.source = source;
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getSource() {
        return source;
    }

    public String getCaption() {
        return caption;
    }

    public boolean isThereImage() {
        return isThereImage;
    }
}
