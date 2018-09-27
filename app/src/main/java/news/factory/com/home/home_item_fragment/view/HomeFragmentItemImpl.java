package news.factory.com.home.home_item_fragment.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.home.home_item_fragment.presenter.HomeFragmentItemPresenterImpl;
import news.factory.com.utils.Constants;
import news.factory.com.utils.CostumeItemDecorator;

public class HomeFragmentItemImpl extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recycler;
    @Inject
    HomeFragmentItemPresenterImpl presenter;
    @Inject
    RecyclerAdapterImpl adapter;


    public static Fragment newInstance(int index) {
        Bundle data = new Bundle();
        data.putInt(Constants.FRAGMENT_PUT_DATA_CONSTANT, index);
        HomeFragmentItemImpl f = new HomeFragmentItemImpl();
        f.setArguments(data);
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        int index = getArguments().getInt(Constants.FRAGMENT_PUT_DATA_CONSTANT);
        presenter.setData(index);
        provideRecyclerViewAdapter();
    }

    public void provideRecyclerViewAdapter() {
        CostumeItemDecorator itemDecorator = new CostumeItemDecorator(this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(mLayoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(itemDecorator);
    }


}
