package news.factory.com.home.home_other_pages_item_fragment.interactor;

import news.factory.com.utils.NetworkResponseListener;

public interface HomeFragmentOtherPagesInteractor {

    void getHomeOtherPagesData(NetworkResponseListener listener, String type, int index);
}
