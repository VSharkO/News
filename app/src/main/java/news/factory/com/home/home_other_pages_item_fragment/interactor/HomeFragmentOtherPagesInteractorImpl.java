package news.factory.com.home.home_other_pages_item_fragment.interactor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractor;
import news.factory.com.networking.Service;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;

public class HomeFragmentOtherPagesInteractorImpl extends BaseInteractor implements HomeFragmentOtherPagesInteractor {
    private Service service;

    @Inject
    public HomeFragmentOtherPagesInteractorImpl(Service service) {
        this.service = service;
    }

    @Override
    public void getHomeOtherPagesData(NetworkResponseListener listener, String type, int index) {
        service.getHomeItemData(type,String.valueOf(index),Constants.PAGE_NUMBER_ARTICLE).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(news -> new InteractorData(news))
                .subscribe(getObserver(listener));
    }
}
