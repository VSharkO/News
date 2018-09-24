package news.factory.com.home.home_fragment_front_page.interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface HomeFrontPageInteractor {

    void getProductsFromAPI(NetworkResponseListener listener, String type, String id, String pageNum);

}
