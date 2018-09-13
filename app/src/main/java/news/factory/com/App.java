package news.factory.com;

import android.app.Activity;
import android.app.Application;

import com.maradroid.dummyresponsegenerator.base.interactor.InteractorImpl;
import com.maradroid.dummyresponsegenerator.utils.SharedPerfRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        Timber.plant(new Timber.DebugTree());
        new InteractorImpl(this).generateResponses(true);
        new SharedPerfRepo(this).setDummyResponse(true);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

}
