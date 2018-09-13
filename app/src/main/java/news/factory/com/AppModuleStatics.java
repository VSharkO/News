package news.factory.com;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import news.factory.com.utils.AppStaticsImpl;

@Module
public class AppModuleStatics {
    @Singleton
    @Provides
    AppStaticsImpl provideContext(Context app){
            return new AppStaticsImpl(app);
    }
}
