package news.factory.com.single.main.view;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;
import dagger.Lazy;
import news.factory.com.R;
import news.factory.com.base.BaseActivity;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.single.main.presenter.SingleMainActivityPresenter;

public class SingleMainActivity extends BaseActivity implements SingleMainActivityView {

    @Inject
    SingleMainActivityPresenter presenter;
    @Inject
    ViewPagerAdapterImpl adapter;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.floatingForwardButton)
    ImageButton forwardButton;
    @BindView(R.id.floatingBackwardButton)
    ImageButton backwardButton;
    String itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        viewPager.setAdapter(adapter);
        presenter.getArticlesFromAPI();
        presenter.setSwipeButtons(viewPager.getCurrentItem(), adapter.getCount());
        Intent intent = getIntent();
        itemId = intent.getStringExtra(getString(R.string.itemId));
        adapter.setItemId(itemId);
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
