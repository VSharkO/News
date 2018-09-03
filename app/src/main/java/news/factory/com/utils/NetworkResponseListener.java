package news.factory.com.utils;

public interface NetworkResponseListener<T> {
    void onSuccess(T callback);
    void onFailure(Throwable throwable);
}
