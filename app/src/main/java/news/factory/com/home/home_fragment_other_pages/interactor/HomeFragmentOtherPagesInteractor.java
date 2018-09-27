package news.factory.com.home.home_fragment_other_pages.interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface HomeFragmentOtherPagesInteractor {

    void getHomeOtherPagesData(NetworkResponseListener listener, String type, int index);
}
