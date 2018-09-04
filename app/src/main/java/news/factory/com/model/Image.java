package news.factory.com.model;

public class Image
{
    private String xxl;

    private String xl;

    private String original;

    private String m;

    public String getXxl ()
    {
        return xxl;
    }

    public void setXxl (String xxl)
    {
        this.xxl = xxl;
    }

    public String getXl ()
    {
        return xl;
    }

    public void setXl (String xl)
    {
        this.xl = xl;
    }

    public String getOriginal ()
    {
        return original;
    }

    public void setOriginal (String original)
    {
        this.original = original;
    }

    public String getM ()
    {
        return m;
    }

    public void setM (String m)
    {
        this.m = m;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [xxl = "+xxl+", xl = "+xl+", original = "+original+", m = "+m+"]";
    }
}
