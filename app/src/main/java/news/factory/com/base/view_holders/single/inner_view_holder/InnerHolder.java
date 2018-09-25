package news.factory.com.base.view_holders.single.inner_view_holder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.utils.Constants;

public class InnerHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.innerPager)
    ViewPager pager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    ViewPagerAdapterImpl adapter;

    public InnerHolder(View itemView, FragmentManager fragmentManager) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        adapter = new ViewPagerAdapterImpl(fragmentManager, Constants.SINGLE_INNER_HOLDER_TYPE);
        pager.setAdapter(adapter);
        adapter.setDataCount(3);
        tabLayout.setupWithViewPager(pager);
    }

    public void onBind(int position) {
        if (position != RecyclerView.NO_POSITION) {

        }
    }
}
