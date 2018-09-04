package news.factory.com.pager.view;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.App;
import news.factory.com.R;
import news.factory.com.adapters.RecyclerViewAdapter;
import news.factory.com.model.Content;
import news.factory.com.pager.presenter.ArticleFragmentPresenter;
import news.factory.com.pager.presenter.ArticleFragmentPresenterImpl;
import news.factory.com.utils.Constants;

public class ArticleFragment extends Fragment implements ArticleFragmentView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    RecyclerViewAdapter adapter;
    ArticleFragmentPresenter presenter;

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
        presenter = new ArticleFragmentPresenterImpl(this);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        provideRecyclerViewAdapter();
    }

    public void provideRecyclerViewAdapter() {
        adapter = new RecyclerViewAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(App.getInstance());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void fillAdapterData(List<Content> content){
        adapter.fillData(content);
        adapter.notifyDataSetChanged();
    }

}

