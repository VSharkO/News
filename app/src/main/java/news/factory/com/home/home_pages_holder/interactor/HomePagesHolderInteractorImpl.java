package news.factory.com.home.home_pages_holder.interactor;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractor;
import news.factory.com.model.single.TopNews;
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
    public void getHomeOtherPagesData(NetworkResponseListener listener, int index) {
        TopNews[] array= new TopNews[2];
        //todo flatmap
        service.getHomeItemData(Constants.MOST_NEW_TYPE,String.valueOf(index),Constants.PAGE_NUMBER_ARTICLE).
                zipWith(service.getHomeItemData(Constants.MOST_READ_TYPE,String.valueOf(index),Constants.PAGE_NUMBER_ARTICLE),(news1,news2)->array)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(news -> new InteractorData(news))
                .subscribe(getObserver(listener));
    }
}
