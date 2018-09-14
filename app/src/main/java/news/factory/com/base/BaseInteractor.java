package news.factory.com.base;

import io.reactivex.observers.DisposableObserver;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;

public abstract class BaseInteractor {

    DisposableObserver<InteractorData> observer;

    public DisposableObserver<InteractorData> getObserver(NetworkResponseListener listener){
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

    public void disspose(){
        observer.dispose();
    }
}
