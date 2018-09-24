package news.factory.com.single.inner_pager_fragment.inner_pager_interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface InnerArticlesInteractor {

    void getTopArticles(NetworkResponseListener listener, String type, String id, String pageNum);
}
