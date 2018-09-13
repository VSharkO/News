package news.factory.com.networking.di;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.maradroid.dummyresponsegenerator.base.DRGInterceptor;
import com.maradroid.dummyresponsegenerator.utils.ConstKt;
import dagger.Module;
import dagger.Provides;
import news.factory.com.BuildConfig;
import news.factory.com.networking.Service;
import news.factory.com.utils.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkingModule {

    @Provides
    public Retrofit provideRestClient(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.NEWS_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public Service provideNewsAPIService(Retrofit retrofit) {
        return retrofit.create(Service.class);
    }

    @Provides
    public OkHttpClient provideOkHttpClient(Context appContext, Interceptor interceptor, HttpLoggingInterceptor loggingInterceptor){
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().addInterceptor(interceptor);
        if(BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
        okHttpClientBuilder.addInterceptor(new DRGInterceptor(appContext, ConstKt.MEDIATYPE_JSON));
        return okHttpClientBuilder.build();
    }

    @Provides
    public Interceptor provideInterceptor() {
        Interceptor interceptor = chain -> {
            Request request = chain.request();
            HttpUrl newHttpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter(Constants.API_TOKEN,Constants.TOKEN)
                    .build();
            request = request.newBuilder().url(newHttpUrl).build();
            return chain.proceed(request);
        };

        return interceptor;
    }

    @Provides
    public HttpLoggingInterceptor provideLogging() {
        HttpLoggingInterceptor.Logger logger = message -> Timber.d(message);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(logger);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}
