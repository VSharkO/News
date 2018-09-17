package news.factory.com.networking.interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface ArticleInteractor {

    void getProductsFromAPI(NetworkResponseListener listener, String type, String id, String pageNum);

}
