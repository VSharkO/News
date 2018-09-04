package news.factory.com.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import news.factory.com.App;
import news.factory.com.R;
import news.factory.com.adapters.ViewPagerFragmentAdapter;
import news.factory.com.model.News;

public class MainActivity extends AppCompatActivity implements MainActivityView{

    @BindView(R.id.pager)
    ViewPager mViewPager;

    ViewPagerFragmentAdapter mAdapter;
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenterImpl(this, App.getInstance().getHelper());
        mPresenter
    }

    @Override
    public void setupPager(List<News> news){
        ViewPagerFragmentAdapter mPagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mPagerAdapter.setAdapterData(news);
        mViewPager.setAdapter(mPagerAdapter);
    }
}
