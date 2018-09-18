package news.factory.com.base.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.inner_pager_fragment.view.InnerPagerFragment;
import news.factory.com.utils.Constants;

public class ViewPagerFragmentAdapterImpl extends FragmentPagerAdapter implements ViewPagerFragmentAdapter {

    private int countNumber;
    private String type;

    public ViewPagerFragmentAdapterImpl(FragmentManager fm, String type) {
        super(fm);
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        if(type.equals(Constants.ARTICLE_TYPE))
            return ArticleFragment.newInstance(position);
        if(type.equals(Constants.MOST_READ_TYPE))
            return InnerPagerFragment.newInstance(position);
        else return null;  //fixat
    }

    @Override
    public int getCount() {
        return countNumber;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setDataCount(int numOfPages) {
        countNumber = numOfPages;
        notifyDataSetChanged();
    }
}



