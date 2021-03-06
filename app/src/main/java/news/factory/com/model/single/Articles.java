package news.factory.com.model.single;

import com.google.gson.annotations.SerializedName;

public class Articles {

    @SerializedName("featured_image")
    private FeaturedImage featuredImage;
    private String title;
    @SerializedName("published_at_humans")
    private String publishedAtHumans;
    private String shares;
    private String category;
    private String id;

    public String getId() {
        return id;
    }
    public FeaturedImage getFeaturedImage() {
        return featuredImage;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedAtHumans() {
        return publishedAtHumans;
    }

    public String getShares() {
        return shares;
    }

    public String getCategory() {
        return category;
    }
}
