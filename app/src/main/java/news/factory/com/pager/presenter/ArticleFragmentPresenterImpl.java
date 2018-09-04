package news.factory.com.pager.presenter;

import news.factory.com.model.Content;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.NetworkingHelper;
import news.factory.com.pager.view.ArticleFragment;
import news.factory.com.pager.view.ArticleFragmentView;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;

public class ArticleFragmentPresenterImpl implements ArticleFragmentPresenter {

    private ArticleFragmentView view;
    private NetworkingHelper mNetworkingHelper;

    public ArticleFragmentPresenterImpl(ArticleFragment view, NetworkingHelper networkingHelper) {
        setView(view);
        mNetworkingHelper = networkingHelper;
    }

    @Override
    public void setView(ArticleFragmentView view) {
        this.view = view;
    }

    @Override
    public void setData(int index) {
        mNetworkingHelper.getProductsFromAPI(new NetworkResponseListener<News>() {
            @Override
            public void onSuccess(News callback) {
                Content content =
                view.fillAdapterData(callback.getContent());
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        }, Constants.TYPE,Constants.ID,String.valueOf(index));
    }
}