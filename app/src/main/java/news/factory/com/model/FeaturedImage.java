package news.factory.com.model;
import com.google.gson.annotations.SerializedName;


public class FeaturedImage
{
    private String slider;

    private String xxxl;

    private String s;

    private String xxl;

    private String xl;

    private String original;

    private String xs;

    private String xxs;

    private String l;

    private String m;

    public String getSlider ()
    {
        return slider;
    }

    public String get3xl ()
    {
        return xxxl;
    }

    public String getS ()
    {
        return s;
    }

    public String getXxl ()
    {
        return xxl;
    }

    public String getXl ()
    {
        return xl;
    }

    public String getOriginal ()
    {
        return original;
    }

    public String getXs ()
    {
        return xs;
    }

    public String getXxs ()
    {
        return xxs;
    }

    public String getL ()
    {
        return l;
    }

    public String getM ()
    {
        return m;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [slider = "+slider+", 3xl = "+xxxl+", s = "+s+", xxl = "+xxl+", xl = "+xl+", original = "+original+", xs = "+xs+", xxs = "+xxs+", l = "+l+", m = "+m+"]";
    }
}