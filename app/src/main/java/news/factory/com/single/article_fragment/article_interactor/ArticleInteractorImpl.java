package news.factory.com.single.article_fragment.article_interactor;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.base.BaseInteractor;
import news.factory.com.networking.Service;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
public class ArticleInteractorImpl extends BaseInteractor implements ArticleInteractor{

    private Service service;

    @Inject
    public ArticleInteractorImpl(Service service) {
        this.service = service;
    }

    @Override
    public void getProductsFromAPI(NetworkResponseListener listener, String type, String id, String pageNum) {

        service.getNews(type,id,pageNum).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(news -> new InteractorData(news))
                .subscribe(getObserver(listener));
    }

}
