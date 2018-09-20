package news.factory.com.di;

import javax.inject.Singleton;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import news.factory.com.App;
import news.factory.com.base.view_holders.inner_view_holder.InnerHolder;
import news.factory.com.networking.di.NetworkingModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkingModule.class,
        ActivityModule.class,
        FragmentModule.class
        })

public interface AppComponent {
    void inject(App app);
    void inject(InnerHolder innerHolder);
}