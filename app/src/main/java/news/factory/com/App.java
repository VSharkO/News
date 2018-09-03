package news.factory.com;

import android.app.Application;

import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl;
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo;

import news.factory.com.networking.helpers.OkHttpHelper;
import news.factory.com.networking.helpers.RetrofitHelper;
import news.factory.com.networking.helpers.NetworkingHelper;
import okhttp3.OkHttpClient;
import timber.log.Timber;

public class App extends Application {

    private static App instance;
    NetworkingHelper mHelper;
    OkHttpClient mClient;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        mClient = new OkHttpHelper().provideOkHttpClient();
        mHelper = new RetrofitHelper().provideNetworkingHelper(mClient);

        new InteractorImpl(this).generateResponses(true);
        new SharedPerfRepo(this).setDummyResponse(true);
    }

    public static App getInstance() {
        return instance;
    }

    public NetworkingHelper getHelper() {
        return mHelper;
    }
}
