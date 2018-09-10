package news.factory.com.article_fragment.view;
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
import android.widget.ImageButton;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerAdapter;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenterImpl;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.utils.Constants;
import news.factory.com.utils.CostumeItemDecorator;
import timber.log.Timber;

public class ArticleFragment extends Fragment implements ArticleFragmentView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    RecyclerAdapter adapter;
    ArticleFragmentPresenter presenter;

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
        presenter = new ArticleFragmentPresenterImpl(this);
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
}

