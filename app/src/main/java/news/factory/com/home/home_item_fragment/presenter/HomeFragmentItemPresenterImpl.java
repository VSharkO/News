package news.factory.com.home.home_item_fragment.presenter;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.Lazy;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.view_holders.single.inner_cell_holder.InnerCellData;
import news.factory.com.home.home_pages_holder.presenter.HomePagesHolderPresenter;
import news.factory.com.model.single.TopNews;
public class HomeFragmentItemPresenterImpl implements HomeFragmentItemPresenter {

    private Lazy<HomePagesHolderPresenter> parentPesenter;
    private Lazy<RecyclerAdapter> adapter;
    private TopNews data;

    @Inject
    public HomeFragmentItemPresenterImpl(Lazy<HomePagesHolderPresenter> parentPesenter, Lazy<RecyclerAdapter> adapter) {

        this.parentPesenter = parentPesenter;
        this.adapter = adapter;
    }

    @Override
    public void setData(int index) {
        data = parentPesenter.get().getData()[index];
        adapter.get().fillData(getSortedItemsForRecycler(data));
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
}
