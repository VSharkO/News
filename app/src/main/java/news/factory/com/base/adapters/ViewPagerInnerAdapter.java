package news.factory.com.base.adapters;


import android.support.v4.app.FragmentManager;

public interface ViewPagerInnerAdapter {
    void setDataCount(int numOfPages);
    FragmentManager getFragmentManager();
}
