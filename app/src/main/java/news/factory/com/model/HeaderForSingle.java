package news.factory.com.model;

public class HeaderForSingle {

    private String featuredImage="no";
    private String featuredImageSourceText;
    private String category;
    private String featuredImageCaption;


    public HeaderForSingle(String featuredImage, String featuredImageSourceText, String category, String featuredImageCaption) {
        this.featuredImage = featuredImage;
        this.featuredImageSourceText = featuredImageSourceText;
        this.category = category;
        this.featuredImageCaption = featuredImageCaption;
    }

    public HeaderForSingle(String featuredImageSourceText, String category, String featuredImageCaption) {
        this.featuredImageSourceText = featuredImageSourceText;
        this.category = category;
        this.featuredImageCaption = featuredImageCaption;
    }

    public String getFeaturedImageSourceText() {
        return featuredImageSourceText;
    }

    public void setFeaturedImageSourceText(String featuredImageSourceText) {
        this.featuredImageSourceText = featuredImageSourceText;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFeaturedImageCaption() {
        return featuredImageCaption;
    }

    public void setFeaturedImageCaption(String featuredImageCaption) {
        this.featuredImageCaption = featuredImageCaption;
    }
}
