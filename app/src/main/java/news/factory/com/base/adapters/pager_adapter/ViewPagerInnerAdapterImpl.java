package news.factory.com.base.adapters.pager_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import javax.inject.Inject;

import news.factory.com.inner_pager_fragment.view.InnerPagerFragment;
import news.factory.com.utils.Constants;

public class ViewPagerInnerAdapterImpl extends FragmentStatePagerAdapter implements ViewPagerInnerAdapter{
    private int countNumber;

    public ViewPagerInnerAdapterImpl(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            return InnerPagerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return countNumber;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

    }

    @Override
    public void setDataCount(int numOfPages) {
        countNumber = numOfPages;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return Constants.MOST_POPULAR_TYPE.substring(0,1).toUpperCase()
                        + Constants.MOST_POPULAR_TYPE.substring(1);
            case 1:
                return Constants.MOST_READ_TYPE.substring(0,1).toUpperCase()
                        + Constants.MOST_READ_TYPE.substring(1);
            case 2:
                return Constants.MOST_NEW_TYPE.substring(0,1).toUpperCase()
                        + Constants.MOST_NEW_TYPE.substring(1);
                default: return "";
        }
    }

}
