package news.factory.com.home.home_item_fragment.interactor;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractor;
import news.factory.com.networking.Service;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;

public class HomePagesHolderInteractorImpl extends BaseInteractor implements HomePagesHolderInteractor {
    private Service service;

    @Inject
    public HomePagesHolderInteractorImpl(Service service) {
        this.service = service;
    }

    @Override
    public void getHomeOtherPagesData(NetworkResponseListener listener, int index, int index2) {

        //todo flatmap

        switch (index){
            case 0:
                service.getHomeItemData(Constants.MOST_NEW_TYPE,String.valueOf(index),Constants.PAGE_NUMBER_ARTICLE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(news -> new InteractorData(news))
                        .subscribe(getObserver(listener));
            case 1:
                service.getHomeItemData(Constants.MOST_READ_TYPE,String.valueOf(index),Constants.PAGE_NUMBER_ARTICLE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(news -> new InteractorData(news))
                        .subscribe(getObserver(listener));

        }


    }

}
