package news.factory.com.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.adapters.RecyclerViewAdapter;
import news.factory.com.utils.Constants;

public class ArticleFragment extends Fragment implements ArticleFragmentView{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    RecyclerViewAdapter adapter;
    ArticleFragmentPresenter presenter;


    public static Fragment newInstance(int index) {
        Bundle data = new Bundle();
        data.putInt(Constants.FRAGMENT_PUT_DATA_CONSTANT, index);
        ArticleFragment f = new ArticleFragment();
        f.setArguments(data);
        return f;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        ButterKnife.bind(this,view);
        presenter = new ArticleFragmentPresenterImpl(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onStart() {
        super.onStart();
        int index = getArguments().getInt(Constants.FRAGMENT_PUT_DATA_CONSTANT);
        presenter.setData(index);
    }

}

