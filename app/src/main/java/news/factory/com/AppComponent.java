package news.factory.com;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import news.factory.com.componentBuilders.ActivityBuilder;
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class
        ,AppModule.class
        ,ActivityBuilder.class
        ,AppModuleRepository.class})
public interface AppComponent {
    @Component.Builder
    interface Builder{
            @BindsInstance
            Builder application(App application);
            AppComponent build();
    }

    void inject(App app);
}