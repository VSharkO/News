package news.factory.com.home.main.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseActivity;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.home.main.presenter.HomeMainPresenter;

public class HomeMainActivityImpl extends BaseActivity implements HomeMainActivity {

    @Inject
    HomeMainPresenter presenter;

    @Inject
    ViewPagerAdapterImpl adapter;

    @BindView(R.id.homeTabLayout)
    TabLayout tabLayout;

    @BindView(R.id.homePager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pager);
        ButterKnife.bind(this);
        pager.setAdapter(adapter);
        adapter.setDataCount(3);
        tabLayout.setupWithViewPager(pager);
    }
}