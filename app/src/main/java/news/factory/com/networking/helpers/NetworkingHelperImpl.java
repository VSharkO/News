package news.factory.com.networking.helpers;
import android.support.annotation.NonNull;
import news.factory.com.model.News;
import news.factory.com.networking.Service;
import news.factory.com.utils.NetworkResponseListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkingHelperImpl implements NetworkingHelper{

    private Service mService;

    public NetworkingHelperImpl(Service service) {
        mService = service;
    }

    @Override
    public void getProductsFromAPI(NetworkResponseListener<News> listener,String type, String id,String pageNumber) {
        mService.getNews(type,id,pageNumber).enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call,@NonNull Response<News> response) {
                if (response.body()!=null){
                    News data = response.body();
                    if(data!=null)
                        listener.onSuccess(data);
                }
            }

            @Override
            public void onFailure(@NonNull Call<News> call,@NonNull Throwable t) {
                listener.onFailure(t);
            }

        });
    }
}
