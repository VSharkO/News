package news.factory.com.main.presenter;

import javax.inject.Inject;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.model.News;
import news.factory.com.networking.interactor.article_interactor.ArticleInteractor;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class MainActivityPresenterImpl implements MainActivityPresenter,NetworkResponseListener {

    private MainActivityView view;
    private ArticleInteractor articleInteractor;
    private ViewPagerFragmentAdapter adapter;

    @Inject
    public MainActivityPresenterImpl(MainActivityView view, ArticleInteractor interactor, ViewPagerFragmentAdapter adapter) {
        articleInteractor = interactor;
        this.adapter = adapter;
        this.view = view;
    }

    @Override
    public void getArticlesFromAPI() {
        articleInteractor.getProductsFromAPI(this,Constants.TYPE, Constants.ID, Constants.PAGE_NUMBER);
    }

    @Override
    public void setSwipeButtons(int index,int numOfItems){
        if (index == 0){
            view.setForwardButtonVisible();
        }
        else if (index==numOfItems-1){
            view.setBackwardButtonVisible();
        }
        else{
           view.setBothButtonsVisible();
        }
    }

    @Override
    public void onSuccess(InteractorData callback) {
        News data = (News)callback.getData();
        adapter.setDataCount(Integer.parseInt(data.getPagesNo()));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }

}
