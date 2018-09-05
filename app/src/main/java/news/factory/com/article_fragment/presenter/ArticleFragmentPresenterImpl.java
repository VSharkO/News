package news.factory.com.article_fragment.presenter;

import java.util.List;
import news.factory.com.App;
import news.factory.com.model.Content;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.NetworkingHelper;
import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.article_fragment.view.ArticleFragmentView;
import news.factory.com.networking.helpers.NetworkingHelperImpl;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class ArticleFragmentPresenterImpl implements ArticleFragmentPresenter {

    private ArticleFragmentView view;
    private NetworkingHelper mNetworkingHelper;

    public ArticleFragmentPresenterImpl(ArticleFragment view) {
        this.view = view;
        mNetworkingHelper = new NetworkingHelperImpl(App.getInstance().getService());
    }

    @Override
    public void setData(int index) {
        mNetworkingHelper.getProductsFromAPI(new NetworkResponseListener<News>() {
            @Override
            public void onSuccess(News callback) {
                List<Content> content = callback.getContent();
                view.fillAdapterData(content);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Timber.e(throwable);
            }
        }, Constants.TYPE,Constants.ID,String.valueOf(index));
    }
}