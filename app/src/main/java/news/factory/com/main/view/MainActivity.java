package news.factory.com.main.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import news.factory.com.App;
import news.factory.com.R;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    ViewPager mViewPager;
    ViewPagerFragmentAdapter mAdapter;
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mViewPager = findViewById(R.id.pager);
        mPresenter = new MainActivityPresenterImpl(this);
        mAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mPresenter.getArticlesFromAPI();
    }

    @Override
    public void setNumberOfPages(int numberOfPages){
        mAdapter.setDataCount(numberOfPages);
        mAdapter.notifyDataSetChanged();
    }
}
