package news.factory.com.home.home_pages_holder.interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface HomePagesHolderInteractor {

    void getHomeOtherPagesData(NetworkResponseListener listener, int index);
}
