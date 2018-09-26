package news.factory.com.home.home_fragment_other_pages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapter;
import news.factory.com.single.article_fragment.view.ArticleFragmentImpl;
import news.factory.com.utils.Constants;

public class HomeFragmentOtherPagesImpl extends BaseFragment {
    @BindView(R.id.innerPager)
    ViewPager pager;
    @Inject
    HomeFragmentOtherPagesPresenter presenter;
    @Inject
    ViewPagerAdapter adapter;
//TODO
    public static Fragment newInstance(int index) {
        Bundle data = new Bundle();
        data.putInt(Constants.FRAGMENT_PUT_DATA_CONSTANT, index);
        ArticleFragmentImpl f = new ArticleFragmentImpl();
        f.setArguments(data);
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cell_inner_pager,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        adapter.setDataCount(presenter.getDataCount());
        int index = getArguments().getInt(Constants.FRAGMENT_PUT_DATA_CONSTANT);
        presenter.setData(index);
    }
}
