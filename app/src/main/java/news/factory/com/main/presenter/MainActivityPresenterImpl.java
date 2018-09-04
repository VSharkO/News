package news.factory.com.main.presenter;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.main.view.MainActivityView;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.NetworkingHelper;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView view;
    private NetworkingHelper mNetworkingHelper;
    private List<News> news = new ArrayList<>();

    public MainActivityPresenterImpl(MainActivityView view, NetworkingHelper networkingHelper) {
        mNetworkingHelper = networkingHelper;
        this.view = view;
    }

    @Override
    public void getArticlesFromAPI() {
            mNetworkingHelper.getProductsFromAPI(new NetworkResponseListener<News>() {
                @Override
                public void onSuccess(News callback) {
                    news.add(callback);
                    view.setupPager(news);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Timber.e(throwable);
                }
            }, Constants.TYPE, Constants.ID, Constants.PAGE_NUMBER);

    }
}
