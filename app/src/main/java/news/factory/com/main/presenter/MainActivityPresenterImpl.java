package news.factory.com.main.presenter;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class MainActivityPresenterImpl implements MainActivityPresenter,NetworkResponseListener<News> {

    MainActivityView view;
    ArticleInteractor mArticleInteractor;

    public MainActivityPresenterImpl(MainActivityView view, ArticleInteractor interactor) {
        mArticleInteractor = interactor;
        this.view = view;
    }

    @Override
    public void getArticlesFromAPI() {
        mArticleInteractor.getProductsFromAPI(this,Constants.TYPE, Constants.ID, Constants.PAGE_NUMBER);
    }

    @Override
    public void onSuccess(News callback) {
        view.setNumberOfPages(Integer.parseInt(callback.getPagesNo()));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }
}
