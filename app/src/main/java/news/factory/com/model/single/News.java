package news.factory.com.model.single;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    private String pages_no;

    private String no_featured_image;

    private String published_at_humans;

    private List<Content> content;

    private String shares;

    private String category;

    private String featured_image_source;

    private String id;

    private String uppertitle;

    private String author;

    private String title;

    private String featured_image_caption;

    @SerializedName("featured_image")
    private FeaturedImage mFeatured_image;

    private String subtitle;

    public String getPagesNo()
    {
        return pages_no;
    }

    public String getNoFeaturedImage()
    {
        return no_featured_image;
    }

    public String getPublishedAtHumans()
    {
        return published_at_humans;
    }

    public List<Content> getContent ()
    {
        return content;
    }

    public String getShares ()
    {
        return shares;
    }

    public String getCategory ()
    {
        return category;
    }

    public String getFeaturedImageSource ()
    {
        return featured_image_source;
    }

    public String getId ()
    {
        return id;
    }

    public String getUpperTitle()
    {
        return uppertitle;
    }

    public String getAuthor ()
    {
        return author;
    }

    public String getTitle ()
    {
        return title;
    }

    public String getFeaturedImageCaption()
    {
        return featured_image_caption;
    }

    public FeaturedImage getFeatured_image ()
    {
        return mFeatured_image;
    }


    public String getSubtitle ()
    {
        return subtitle;
    }

}
