package news.factory.com.home.main.interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface HomeMainInteractor {

    void getHomeData(NetworkResponseListener listener, String type);
}
