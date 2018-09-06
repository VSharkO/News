package news.factory.com;

import android.app.Application;

import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl;
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo;

import news.factory.com.networking.Service;
import news.factory.com.networking.helpers.OkHttpHelper;
import news.factory.com.networking.helpers.RetrofitHelper;
import news.factory.com.networking.helpers.NetworkingHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import timber.log.Timber;

public class App extends Application {

    private static App instance;
    Service mService;
    OkHttpClient mClient;
    Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        mClient = new OkHttpHelper().provideOkHttpClient();
        mRetrofit = new RetrofitHelper().provideRestClient(mClient);
        mService = new RetrofitHelper().provideNewsAPIService(mRetrofit);

        new InteractorImpl(this).generateResponses(true);
        new SharedPerfRepo(this).setDummyResponse(true);
    }

    public static App getInstance() {
        return instance;
    }

    public Service getService() {
        return mService;
    }

}
