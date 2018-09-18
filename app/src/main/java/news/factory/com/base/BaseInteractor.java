package news.factory.com.base;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import io.reactivex.observers.DisposableObserver;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;


public abstract class BaseInteractor implements LifecycleObserver{

    private DisposableObserver<InteractorData> observer;

    protected DisposableObserver<InteractorData> getObserver(NetworkResponseListener listener){
        observer = new DisposableObserver<InteractorData>() {
            @Override
            public void onNext(InteractorData interactorData) {
                listener.onSuccess(interactorData);
            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onComplete() {

            }
        };

        return observer;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void dispose(){
        observer.dispose();
    }
}
