package news.factory.com.home.home_item_fragment.presenter;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.Lazy;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.view_holders.single.inner_cell_holder.InnerCellData;
import news.factory.com.home.home_item_fragment.interactor.HomePagesHolderInteractor;
import news.factory.com.model.single.TopNews;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class HomeFragmentItemPresenterImpl implements HomeFragmentItemPresenter, NetworkResponseListener {


    private Lazy<RecyclerAdapter> adapter;
    private TopNews data;
    private HomePagesHolderInteractor interactor;

    @Inject
    public HomeFragmentItemPresenterImpl(Lazy<RecyclerAdapter> adapter,HomePagesHolderInteractor interactor) {
        this.adapter = adapter;
        this.interactor = interactor;
    }

    @Override
    public void setData(int index, int index2) {
        interactor.getHomeOtherPagesData(this,index,index2);

    }

    private List<RecyclerWrapper> getSortedItemsForRecycler(TopNews news) {

        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        addArticles(news,recyclerWrappers);
        return recyclerWrappers;
    }

    private void addArticles(TopNews news,List<RecyclerWrapper> recyclerWrappers){
        for (int i=0; i<news.getArticles().size(); i++){
            recyclerWrappers.add(new RecyclerWrapper(new InnerCellData(news.getArticles().get(i)),
                    RecyclerWrapper.TYPE_INNER_ARTICLE_CELL));
        }
    }

    @Override
    public void onSuccess(InteractorData callback) {
        data = (TopNews) callback.getData();
        adapter.get().fillData(getSortedItemsForRecycler(data));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }
}
