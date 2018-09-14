package news.factory.com.main.view;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import news.factory.com.R;
import news.factory.com.base.BaseActivity;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivity implements MainActivityView {

    @Inject
    MainActivityPresenter mPresenter;
    @Inject
    ViewPagerFragmentAdapter mAdapter;

    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.floatingForwardButton)
    ImageButton forwardButton;
    @BindView(R.id.floatingBackwardButton)
    ImageButton backwardButton;
    @Inject
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
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
    protected void onStop() {
        super.onStop();
        mPresenter.killDisposable();
    }
}
