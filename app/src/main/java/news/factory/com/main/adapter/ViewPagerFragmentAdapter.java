package news.factory.com.main.adapter;

import android.support.v4.app.Fragment;

public interface ViewPagerFragmentAdapter {
    Fragment getItem(int position);
    int getCount();
    void setDataCount(int numOfPages);
}
