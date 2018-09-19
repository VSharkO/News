package news.factory.com.base.adapters.pager_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import news.factory.com.inner_pager_fragment.view.InnerPagerFragment;

public class ViewPagerInnerAdapterImpl extends FragmentPagerAdapter implements ViewPagerInnerAdapter{
    private int countNumber;

    @Inject
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
    public void setDataCount(int numOfPages) {
        countNumber = numOfPages;
        notifyDataSetChanged();
    }

}
