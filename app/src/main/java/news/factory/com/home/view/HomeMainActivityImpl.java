package news.factory.com.home.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseActivity;
import news.factory.com.base.adapters.pager_adapter.ViewPagerFragmentAdapterImpl;
import news.factory.com.home.presenter.HomeMainPresenter;
import timber.log.Timber;

public class HomeMainActivityImpl extends BaseActivity implements HomeMainActivity {

    @Inject
    HomeMainPresenter presenter;
    @Inject
    ViewPagerFragmentAdapterImpl adapter;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        viewPager.setAdapter(adapter);
        presenter.getArticlesFromAPI();
        Timber.e("OVAJ JE");
    }
}
