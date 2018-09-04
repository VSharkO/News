package news.factory.com.main.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.App;
import news.factory.com.R;
import news.factory.com.adapters.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;
import news.factory.com.model.News;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.pager)
    ViewPager mViewPager;

    ViewPagerFragmentAdapter mAdapter;
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mViewPager = findViewById(R.id.pager);
        ButterKnife.bind(this);
        mPresenter = new MainActivityPresenterImpl(this, App.getInstance().getHelper());
        mPresenter.getArticlesFromAPI();
    }

    @Override
    public void setupPager(List<News> news){
        mAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mAdapter.setAdapterData(news);
        mViewPager.setAdapter(mAdapter);
    }
}
