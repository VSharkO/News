package news.factory.com.networking.helpers;

import news.factory.com.model.News;
import news.factory.com.utils.NetworkResponseListener;

public interface ArticleInteractor {
    void getProductsFromAPI(NetworkResponseListener<News> listener,String type,String id,String pageNumber);
}
