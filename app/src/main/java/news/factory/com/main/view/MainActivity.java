package news.factory.com.main.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;
import news.factory.com.R;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityView,HasActivityInjector,HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;
    @Inject
    MainActivityPresenter mPresenter;

    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.floatingForwardButton)
    ImageButton forwardButton;
    @BindView(R.id.floatingBackwardButton)
    ImageButton backwardButton;
    @Inject
    ViewPagerFragmentAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
//        mAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mPresenter.getArticlesFromAPI();
        setSwipeButtons(mViewPager.getCurrentItem(),mAdapter.getCount());
    }

    @Override
    public void setNumberOfPages(int numberOfPages){
        mAdapter.setDataCount(numberOfPages);
    }

    @OnPageChange(R.id.pager)
    public void handleClicks(){
        setSwipeButtons(mViewPager.getCurrentItem(),mAdapter.getCount());
    }

    public void setSwipeButtons(int index,int numOfItems){
        if (index == 0){
            backwardButton.setVisibility(View.GONE);
            forwardButton.setVisibility(View.VISIBLE);
        }
        else if (index==numOfItems-1){
            backwardButton.setVisibility(View.VISIBLE);
            forwardButton.setVisibility(View.GONE);
        }
        else{
            backwardButton.setVisibility(View.VISIBLE);
            forwardButton.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.floatingBackwardButton)
    public void onBackwardClick(){
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
    }

    @OnClick(R.id.floatingForwardButton)
    public void onForwardClick(){
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }

}
