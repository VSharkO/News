package news.factory.com.home.home_fragment_front_page.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.view_holders.single.inner_cell_holder.InnerCellData;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractor;
import news.factory.com.home.home_fragment_front_page.view.HomeFragmentFrontPage;
import news.factory.com.model.single.TopNews;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import news.factory.com.utils.ResourceRepo;
import timber.log.Timber;

public class HomeFragmentFrontPagePresenterImpl implements HomeFragmentFrontPagePresenter,NetworkResponseListener{

    private HomeFragmentFrontPage view;
    private HomeFrontPageInteractor articleInteractor;
    private ResourceRepo resourceRepo;
    private Lazy<RecyclerAdapter> adapter;
    private int index=0;

    @Inject
    public HomeFragmentFrontPagePresenterImpl(HomeFragmentFrontPage view, HomeFrontPageInteractor articleInteractor, ResourceRepo resourceRepo, Lazy<RecyclerAdapter> adapter) {
        this.view = view;
        this.articleInteractor = articleInteractor;
        this.resourceRepo = resourceRepo;
        this.adapter = adapter;
    }

                                            // TEMP
                                            //  |
                                            //  |
                                            //  |
                                            //  |
                                            //  v
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
        articleInteractor.getTopArticles(this,type,Constants.ID_TOP_ARTICLES,Constants.PAGE_NUMBER_TOP_ARTICLES);
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

        addArticles(news,recyclerWrappers);
        return recyclerWrappers;
    }

    private void addArticles(TopNews news,List<RecyclerWrapper> recyclerWrappers){
        for (int i=0; i<6; i++){
            recyclerWrappers.add(new RecyclerWrapper(new InnerCellData(news.getArticles()[i]),
                    RecyclerWrapper.TYPE_INNER_ARTICLE_CELL));
        }

    }

}
