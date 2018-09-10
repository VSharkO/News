package news.factory.com.networking.helpers;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import news.factory.com.model.News;
import news.factory.com.networking.Service;
import news.factory.com.utils.NetworkResponseListener;

public class ArticleInteractorImpl implements ArticleInteractor{

    private Service mService;

    public ArticleInteractorImpl(Service service) {
        mService = service;
    }

    @Override
    public void getProductsFromAPI(NetworkResponseListener<News> listener, String type, String id, String pageNum) {

        mService.getNews(type,id,pageNum).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<News>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(News news) {
                        listener.onSuccess(news);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);
                    }
                });
        }
}
