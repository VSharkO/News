package news.factory.com.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import news.factory.com.App;
import news.factory.com.main.di.MainActivityComponent;

@Module(subcomponents = MainActivityComponent.class)
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(App application){
        return application;
    }
}
