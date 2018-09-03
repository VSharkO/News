package news.factory.com.networking.helpers;

import news.factory.com.networking.Service;
import news.factory.com.utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public NetworkingHelper provideNetworkingHelper(OkHttpClient client){
        return new NetworkingHelperImpl(provideNewsAPIService(provideRestClient(client)));
    }

    private Retrofit provideRestClient(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.NEWS_API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private Service provideNewsAPIService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }
}
