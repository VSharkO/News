package news.factory.com.utils;

public interface NetworkResponseListener {
    void onSuccess(InteractorData callback);
    void onFailure(Throwable throwable);
}
