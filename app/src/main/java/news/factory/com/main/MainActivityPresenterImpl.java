package news.factory.com.main;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.model.News;
import news.factory.com.networking.helpers.NetworkingHelper;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class MainActivityPresenterImpl implements MainActivityPresenter{

    private MainActivityView view;
    private NetworkingHelper mNetworkingHelper;
    private int pagesNo=1;
    private List<News> news = new ArrayList<>();

    public MainActivityPresenterImpl(MainActivityView view, NetworkingHelper networkingHelper) {
        mNetworkingHelper = networkingHelper;
        this.view = view;
    }

    @Override
    public void getArticlesFromAPI() {
        for (int i = 0; i < pagesNo; i++) {
            mNetworkingHelper.getProductsFromAPI(new NetworkResponseListener<News>() {
                @Override
                public void onSuccess(News callback) {
                    pagesNo = Integer.parseInt(callback.getPages_no());
                    news.add(0, callback);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Timber.e(throwable);
                }
            }, Constants.TYPE, Constants.ID, String.valueOf(i));
        }
        view.setupPager(news);
    }
}
