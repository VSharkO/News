package news.factory.com.home.home_item_fragment.view;

import android.content.Intent;
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
import news.factory.com.home.home_item_fragment.presenter.HomeFragmentItemPresenter;
import news.factory.com.single.main.view.SingleMainActivity;
import news.factory.com.utils.Constants;
import news.factory.com.utils.CostumeItemDecorator;
import timber.log.Timber;

public class HomeFragmentItemImpl extends BaseFragment implements HomeFragmentItemView{

    @BindView(R.id.recyclerView)
    RecyclerView recycler;
    @Inject
    HomeFragmentItemPresenter presenter;
    @Inject
    RecyclerAdapterImpl adapter;
    int index,parentIndex;

    public static Fragment newInstance(int index, int parentIndex) {
        Bundle data = new Bundle();
        data.putInt(Constants.FRAGMENT_PUT_DATA_CONSTANT, index);
        data.putInt(Constants.FRAGMENT_PUT_PARENT_INDEX, parentIndex);
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
        parentIndex = getArguments().getInt(Constants.FRAGMENT_PUT_PARENT_INDEX);
        index = getArguments().getInt(Constants.FRAGMENT_PUT_DATA_CONSTANT);
        presenter.setData(index,parentIndex);
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

    @Override
    public void changeToSingleActivity(String id) {
            Intent intent = new Intent(this.getActivity(), SingleMainActivity.class);
            intent.putExtra(getString(R.string.itemId),id);
            startActivity(intent);
    }
}
