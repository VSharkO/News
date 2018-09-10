package news.factory.com.main.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import news.factory.com.R;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.floatingForwardButton)
    ImageButton forwardButton;
    @BindView(R.id.floatingBackwardButton)
    ImageButton backwardButton;
    ViewPagerFragmentAdapter mAdapter;
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        mPresenter = new MainActivityPresenterImpl(this);
        mAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mPresenter.getArticlesFromAPI();
        setSwipeButtons(mViewPager.getCurrentItem(),mAdapter.getCount());
    }

    @Override
    public void setNumberOfPages(int numberOfPages){
        mAdapter.setDataCount(numberOfPages);
        mAdapter.notifyDataSetChanged();
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
}
