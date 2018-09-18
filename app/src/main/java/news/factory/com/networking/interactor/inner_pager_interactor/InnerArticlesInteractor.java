package news.factory.com.networking.interactor.inner_pager_interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface InnerArticlesInteractor {

    void getTopArticles(NetworkResponseListener listener, String type, String id, String pageNum);
}
