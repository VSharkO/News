package news.factory.com.networking.helpers;

import com.maradroid.dummyresponsegenerator.base.DRGInterceptor;
import java.io.IOException;
import news.factory.com.App;
import news.factory.com.BuildConfig;
import news.factory.com.utils.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;
import com.maradroid.dummyresponsegenerator.utils.ConstKt;

public class OkHttpHelper {

    public OkHttpClient provideOkHttpClient(){
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().addInterceptor(provideInterceptor());
        if(BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(provideLogging());
        }
        okHttpClientBuilder.addInterceptor(new DRGInterceptor(App.getInstance(), ConstKt.MEDIATYPE_JSON));
        return okHttpClientBuilder.build();
    }

    private Interceptor provideInterceptor() {
        Interceptor interceptor = new Interceptor(){
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();

                HttpUrl newHttpUrl = request.url()
                        .newBuilder()
                        .addQueryParameter(Constants.API_TOKEN,Constants.TOKEN)
                        .build();

                request = request.newBuilder().url(newHttpUrl).build();
                return chain.proceed(request);
            }
        };

        return interceptor;
    }

    private HttpLoggingInterceptor provideLogging() {
        HttpLoggingInterceptor.Logger logger = message -> Timber.d(message);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(logger);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }
}
