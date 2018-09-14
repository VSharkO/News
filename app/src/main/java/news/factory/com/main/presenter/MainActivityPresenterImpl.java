package news.factory.com.main.presenter;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class MainActivityPresenterImpl implements MainActivityPresenter,NetworkResponseListener {

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
    public void killDisposable() {
        mArticleInteractor.killDisposable();
    }

    @Override
    public void onSuccess(InteractorData callback) {
        News data = (News)callback.getData();
        view.setNumberOfPages(Integer.parseInt(data.getPagesNo()));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }

}
