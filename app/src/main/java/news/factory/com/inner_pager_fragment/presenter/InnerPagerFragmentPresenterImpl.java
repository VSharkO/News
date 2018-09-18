package news.factory.com.inner_pager_fragment.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.RecyclerAdapter;
import news.factory.com.inner_pager_fragment.view.InnerPagerFragmentView;
import news.factory.com.model.TopNews;
import news.factory.com.networking.interactor.inner_pager_interactor.InnerArticlesInteractor;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import news.factory.com.utils.ResourceRepo;
import timber.log.Timber;

public class InnerPagerFragmentPresenterImpl implements InnerPagerFragmentPresenter, NetworkResponseListener{
    private InnerPagerFragmentView view;
    private InnerArticlesInteractor articleInteractor;
    private ResourceRepo resourceRepo;
    private Lazy<RecyclerAdapter> adapter;
    private int index=0;

    @Inject
    public InnerPagerFragmentPresenterImpl(InnerPagerFragmentView view, InnerArticlesInteractor interactor, ResourceRepo resourceRepo, Lazy<RecyclerAdapter> adapter) {
        this.view = view;
        articleInteractor = interactor;
        this.adapter = adapter;
        this.resourceRepo = resourceRepo;
    }

    @Override
    public void setData(int index) {
        String type;
        switch (index){
            case 0:
                type = Constants.MOST_POPULAR_TYPE;
                break;
            case 1:
                type = Constants.MOST_READ_TYPE;
                break;
            case 2:
                type = Constants.MOST_NEW_TYPE;
                break;

                default: type = Constants.MOST_POPULAR_TYPE;
        }
        articleInteractor.getTopArticles(this, type,Constants.ID_TOP_ARTICLES,String.valueOf(index));

        this.index = index;
    }
    @Override
    public void onSuccess(InteractorData callback) {
        TopNews data = (TopNews) callback.getData();
        adapter.get().fillData(getSortedItemsForRecycler(data));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }


    private List<RecyclerWrapper> getSortedItemsForRecycler(TopNews news) {

        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        addTitle(news,recyclerWrappers);
//        if(!news.getUpperTitle().isEmpty())
//            addUpperTitle(news,recyclerWrappers);
//        addAuthorShares(news,recyclerWrappers);
//        addTitle(news,recyclerWrappers);
//        addContents(news,recyclerWrappers);
//        addPublished(news,recyclerWrappers);
//        addIndicator(news,recyclerWrappers);
        return recyclerWrappers;
    }

    private void addTitle(TopNews news,List<RecyclerWrapper> recyclerWrappers){
        recyclerWrappers.add(new RecyclerWrapper(news.getName(),
                RecyclerWrapper.TYPE_INNER_ARTICLE_CELL));
    }
}
