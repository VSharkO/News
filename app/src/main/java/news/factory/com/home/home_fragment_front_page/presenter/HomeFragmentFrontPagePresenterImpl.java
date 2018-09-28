package news.factory.com.home.home_fragment_front_page.presenter;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.Lazy;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.view_holders.home.HomeFrontPageCellTitle.HomeFrontPageTitleData;
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
    private HomeFrontPageInteractor homeFrontPageInteractor;
    private ResourceRepo resourceRepo;
    private Lazy<RecyclerAdapter> adapter;
    @Inject
    public HomeFragmentFrontPagePresenterImpl(HomeFragmentFrontPage view, HomeFrontPageInteractor homeFrontPageInteractor, ResourceRepo resourceRepo, Lazy<RecyclerAdapter> adapter) {
        this.view = view;
        this.homeFrontPageInteractor = homeFrontPageInteractor;
        this.resourceRepo = resourceRepo;
        this.adapter = adapter;
    }

    @Override
    public void setData() {
        homeFrontPageInteractor.getHomeData(this,Constants.INDEX);
    }

    @Override
    public void onSuccess(InteractorData callback) {
        List<TopNews> data = (List<TopNews>)callback.getData();
        adapter.get().fillData(getSortedItemsForRecycler(data));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    private List<RecyclerWrapper> getSortedItemsForRecycler(List<TopNews> news) {

        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        addArticles(news,recyclerWrappers);
        return recyclerWrappers;
    }

        private void addArticles(List<TopNews> news,List<RecyclerWrapper> recyclerWrappers){
        for(int i=1; i<news.size()-1; i++){
            recyclerWrappers.add(new RecyclerWrapper(new HomeFrontPageTitleData(news.get(i).getName()),
                    RecyclerWrapper.TYPE_HOME_FRONT_TITLE));

            for (int j = 0; j < news.get(i).getArticles().size(); j++) {
                recyclerWrappers.add(new RecyclerWrapper(new InnerCellData(news.get(i).getArticles().get(j)), RecyclerWrapper.TYPE_INNER_ARTICLE_CELL));
            }

            recyclerWrappers.add(new RecyclerWrapper(RecyclerWrapper.TYPE_HOME_BUTTON));
        }
    }

    @Override
    public void changeActivityOnItemClick(String itemId){
            view.changeToSingleActivity(itemId);
        }
}
