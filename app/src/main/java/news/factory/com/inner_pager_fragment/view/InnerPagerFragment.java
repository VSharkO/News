package news.factory.com.inner_pager_fragment.view;
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
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.BaseFragment;
import news.factory.com.base.adapters.RecyclerAdapterImpl;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.inner_pager_fragment.presenter.InnerPagerFragmentPresenter;
import news.factory.com.utils.Constants;
import news.factory.com.utils.CostumeItemDecorator;

public class InnerPagerFragment extends BaseFragment implements InnerPagerFragmentView {

    @BindView(R.id.innerRecyclerView)
    RecyclerView recyclerView;
    @Inject
    InnerPagerFragmentPresenter presenter;
    @Inject
    RecyclerAdapterImpl adapter;

    public static Fragment newInstance(int index) {
        Bundle data = new Bundle();
        data.putInt(Constants.FRAGMENT_PUT_DATA_CONSTANT, index+1);
        InnerPagerFragment f = new InnerPagerFragment();
        f.setArguments(data);
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inner_article,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        provideRecyclerViewAdapter();
        int index = getArguments().getInt(Constants.FRAGMENT_PUT_DATA_CONSTANT);
        presenter.setData(index);
    }

    public void provideRecyclerViewAdapter() {
        CostumeItemDecorator itemDecorator = new CostumeItemDecorator(this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecorator);
    }

    @Override
    public void fillAdapterDataNews(List<RecyclerWrapper> data) {
        adapter.fillData(data);
    }

}