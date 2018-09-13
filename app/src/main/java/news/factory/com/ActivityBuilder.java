package news.factory.com;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import news.factory.com.main.di.MainActivityComponent;
import news.factory.com.main.di.MainActivityModule;
import news.factory.com.main.view.MainActivity;

@Module(includes = {MainActivityModule.class})
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);

}
