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
import news.factory.com.base.adapters.ViewPagerFragmentAdapterImpl;
import news.factory.com.main.presenter.MainActivityPresenter;

public class MainActivity extends BaseActivity implements MainActivityView {

    @Inject
    MainActivityPresenter presenter;
    @Inject
    ViewPagerFragmentAdapterImpl adapter;

    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.floatingForwardButton)
    ImageButton forwardButton;
    @BindView(R.id.floatingBackwardButton)
    ImageButton backwardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        viewPager.setAdapter(adapter);
        presenter.getArticlesFromAPI();
        presenter.setSwipeButtons(viewPager.getCurrentItem(), adapter.getCount());
    }

    @OnPageChange(R.id.pager)
    public void handleClicks(){
        presenter.setSwipeButtons(viewPager.getCurrentItem(), adapter.getCount());
    }

    @Override
    public void setForwardButtonVisible(){
        backwardButton.setVisibility(View.GONE);
        forwardButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setBackwardButtonVisible(){
        backwardButton.setVisibility(View.VISIBLE);
        forwardButton.setVisibility(View.GONE);
    }

    @Override
    public void setBothButtonsVisible(){
        backwardButton.setVisibility(View.VISIBLE);
        forwardButton.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.floatingBackwardButton)
    public void onBackwardClick(){
        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
    }

    @OnClick(R.id.floatingForwardButton)
    public void onForwardClick(){
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }

}
