package news.factory.com.model;

public class Content
{
    private String id;

    private String article_id;

    private String order;

    private String page;

    private String width;

    private String embed_src;

    private String data_raw;

    private String data;

    private String type;

    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getArticle_id ()
    {
        return article_id;
    }

    public void setArticle_id (String article_id)
    {
        this.article_id = article_id;
    }

    public String getOrder ()
    {
        return order;
    }

    public void setOrder (String order)
    {
        this.order = order;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getEmbed_src ()
    {
        return embed_src;
    }

    public void setEmbed_src (String embed_src)
    {
        this.embed_src = embed_src;
    }

    public String getData_raw ()
    {
        return data_raw;
    }

    public void setData_raw (String data_raw)
    {
        this.data_raw = data_raw;
    }

    public String getData ()
    {
        return data;
    }

    public void setData (String data)
    {
        this.data = data;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", article_id = "+article_id+", order = "+order+", page = "+page+", width = "+width+", embed_src = "+embed_src+", data_raw = "+data_raw+", data = "+data+", type = "+type+"]";
    }
}