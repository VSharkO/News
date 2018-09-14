package news.factory.com.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import news.factory.com.App;
import news.factory.com.componentBuilders.MainActivityBuilder;
import news.factory.com.networking.di.NetworkingModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class
        ,AppModule.class
        ,MainActivityBuilder.class
        ,AppModuleRepository.class
        ,NetworkingModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder{
            @BindsInstance
            Builder application(App application);
            AppComponent build();
    }

    void inject(App app);
}