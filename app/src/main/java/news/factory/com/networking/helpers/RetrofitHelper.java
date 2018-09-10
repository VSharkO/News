package news.factory.com.networking.helpers;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import news.factory.com.networking.Service;
import news.factory.com.utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public Retrofit provideRestClient(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.NEWS_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Service provideNewsAPIService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }
}
