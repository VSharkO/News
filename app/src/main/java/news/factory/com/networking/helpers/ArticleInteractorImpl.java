package news.factory.com.networking.helpers;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractor;
import news.factory.com.networking.Service;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;

public class ArticleInteractorImpl extends BaseInteractor implements ArticleInteractor{

    private Service mService;

    public ArticleInteractorImpl(Service service) {
        mService = service;
    }

    @Override
    public void getProductsFromAPI(NetworkResponseListener listener, String type, String id, String pageNum) {

        mService.getNews(type,id,pageNum).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(news -> new InteractorData(news))
                .subscribe(getObserver(listener));
        }
}
