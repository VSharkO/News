package news.factory.com.base.adapters.pager_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import news.factory.com.single.article_fragment.view.ArticleFragment;

public class ViewPagerFragmentAdapterImpl extends FragmentStatePagerAdapter implements ViewPagerFragmentAdapter {
    private int countNumber;

    public ViewPagerFragmentAdapterImpl(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            return ArticleFragment.newInstance(position);
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



