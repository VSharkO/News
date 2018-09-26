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
import news.factory.com.single.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.single.article_fragment.presenter.ArticleFragmentPresenterImpl;
import news.factory.com.single.inner_pager_fragment.presenter.InnerPagerFragmentPresenter;
import news.factory.com.single.inner_pager_fragment.presenter.InnerPagerFragmentPresenterImpl;
import news.factory.com.utils.Constants;

public class InnerHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.innerPager)
    ViewPager pager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    ViewPagerAdapterImpl adapter;

    //tu nekako ubaciti presenter

    public InnerHolder(View itemView, FragmentManager fragmentManager) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        adapter = new ViewPagerAdapterImpl(fragmentManager, Constants.SINGLE_INNER_HOLDER_TYPE,null);
        pager.setAdapter(adapter);
        adapter.setDataCount(3);
        tabLayout.setupWithViewPager(pager);
    }

    public void onBind(int position) {
        if (position != RecyclerView.NO_POSITION) {

        }
    }
}
