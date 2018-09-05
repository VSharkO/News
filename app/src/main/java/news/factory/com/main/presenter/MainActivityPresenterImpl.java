package news.factory.com.main.presenter;
import news.factory.com.App;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.NetworkingHelper;
import news.factory.com.networking.helpers.NetworkingHelperImpl;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView view;
    private NetworkingHelper mNetworkingHelper;

    public MainActivityPresenterImpl(MainActivityView view) {
        mNetworkingHelper = new NetworkingHelperImpl(App.getInstance().getService());
        this.view = view;
    }

    @Override
    public void getArticlesFromAPI() {
            mNetworkingHelper.getProductsFromAPI(new NetworkResponseListener<News>() {
                @Override
                public void onSuccess(News callback) {
                    view.setNumberOfPages(Integer.parseInt(callback.getPages_no()));
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Timber.e(throwable);
                }
            }, Constants.TYPE, Constants.ID, Constants.PAGE_NUMBER);
    }
}
