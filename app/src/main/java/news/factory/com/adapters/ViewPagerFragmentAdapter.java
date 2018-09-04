package news.factory.com.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.model.News;
import news.factory.com.pager.view.ArticleFragment;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private final List<News> newsList = new ArrayList<>();

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    public void setAdapterData(List<News> dataSource) {
        this.newsList.clear();
        this.newsList.addAll(dataSource);
        notifyDataSetChanged();
    }
}



