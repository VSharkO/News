package news.factory.com.article_fragment.view;
import android.content.Context;
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
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import news.factory.com.R;
import news.factory.com.base.RecyclerAdapter;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.utils.Constants;
import news.factory.com.utils.CostumeItemDecorator;

public class ArticleFragment extends Fragment implements ArticleFragmentView{


    @Inject
    ArticleFragmentPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    RecyclerAdapter adapter;


    public static Fragment newInstance(int index) {
        Bundle data = new Bundle();
        data.putInt(Constants.FRAGMENT_PUT_DATA_CONSTANT, index+1);
        ArticleFragment f = new ArticleFragment();
        f.setArguments(data);
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_article,container,false);
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
        adapter = new RecyclerAdapter();
        CostumeItemDecorator itemDecorator = new CostumeItemDecorator(this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(itemDecorator);
    }

    @Override
    public void fillAdapterDataNews(List<RecyclerWrapper> data) {
        adapter.fillData(data);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}

