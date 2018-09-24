package news.factory.com.home.presenter;

import javax.inject.Inject;

import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapter;
import news.factory.com.home.view.HomeMainActivity;
import news.factory.com.model.single.News;
import news.factory.com.single.article_fragment.article_interactor.ArticleInteractor;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class HomeMainPresenterImpl implements HomeMainPresenter, NetworkResponseListener{

    private HomeMainActivity view;
    private ArticleInteractor articleInteractor;
    private ViewPagerAdapter adapter;

    @Inject
    public HomeMainPresenterImpl(HomeMainActivity view, ArticleInteractor articleInteractor, ViewPagerAdapter adapter) {
        this.view = view;
        this.articleInteractor = articleInteractor;
        this.adapter = adapter;
    }

    @Override
    public void getArticlesFromAPI() {
        articleInteractor.getProductsFromAPI(this, Constants.ARTICLE_TYPE, Constants.ID_ARTICLE, Constants.PAGE_NUMBER_ARTICLE);
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
