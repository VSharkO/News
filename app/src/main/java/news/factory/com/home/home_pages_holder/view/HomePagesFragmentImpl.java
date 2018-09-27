package news.factory.com.home.home_pages_holder.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.home.home_pages_holder.presenter.HomePagesHolderPresenter;
import news.factory.com.utils.Constants;

public class HomePagesFragmentImpl extends BaseFragment{

    @BindView(R.id.homeItemPager)
    ViewPager pager;
    @BindView(R.id.tabLayout2)
    TabLayout tab;
    @Inject
    HomePagesHolderPresenter presenter;
    @Inject
    ViewPagerAdapterImpl adapter;

    public static Fragment newInstance(int index) {
        Bundle data = new Bundle();
        data.putInt(Constants.FRAGMENT_PUT_DATA_CONSTANT, index-1);
        HomePagesFragmentImpl f = new HomePagesFragmentImpl();
        f.setArguments(data);
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_other_pages,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        int index = getArguments().getInt(Constants.FRAGMENT_PUT_DATA_CONSTANT);
        presenter.setIndex(index);
        adapter.setPerentIndex(index);
        adapter.setDataCount(2);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
    }
}
