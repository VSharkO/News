package news.factory.com.home.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseActivity;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.home.main.presenter.HomeMainPresenter;
import news.factory.com.model.home.BottomMenuData;
import timber.log.Timber;

public class HomeMainActivityImpl extends BaseActivity implements HomeMainActivity,NavigationView.OnNavigationItemSelectedListener{

    @Inject
    HomeMainPresenter presenter;
    @Inject
    ViewPagerAdapterImpl adapter;
    @BindView(R.id.homeTabLayout)
    TabLayout tabLayout;
    @BindView(R.id.homePager)
    ViewPager pager;
    @BindView(R.id.nav_view)
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_pager);
        ButterKnife.bind(this);
        setNavigationViewListener();
        pager.setAdapter(adapter);
        presenter.getData();
        tabLayout.setupWithViewPager(pager);
        }

    private void setNavigationViewListener() {
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void setAdapterCount(int size) {
        adapter.setDataCount(size);
        for (int i=0; i < tabLayout.getTabCount(); i++){
            TextView textView = (TextView)(((LinearLayout)((LinearLayout)tabLayout.getChildAt(0)).getChildAt(i)).getChildAt(1));
            textView.setScaleY(-1);
        }
    }

    @Override
    public void setNavigationViewItems(List<BottomMenuData> data){
//        Menu menu = navigationView.getMenu();
//        SubMenu menuGroup = menu.addSubMenu("Dnevni Avaz");
//        for (BottomMenuData menuItem:data) {
//            menu.add(menuItem.getTitle());
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item1){

        }
        Timber.e("dadada");
        return true;
    }
}
