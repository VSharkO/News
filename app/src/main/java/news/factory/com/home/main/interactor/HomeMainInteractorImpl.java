package news.factory.com.home.main.interactor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractor;
import news.factory.com.networking.Service;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;

public class HomeMainInteractorImpl extends BaseInteractor implements HomeMainInteractor {

    private Service service;

    @Inject
    public HomeMainInteractorImpl(Service service) {
        this.service = service;
    }

    @Override
    public void getBottomMenuData(NetworkResponseListener listener, String type) {
        service.getBottomMenuData(type).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(news -> new InteractorData(news))
                .subscribe(getObserver(listener));
    }

}
