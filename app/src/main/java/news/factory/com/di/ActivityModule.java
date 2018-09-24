package news.factory.com.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.home.di.HomeMainActivityModule;
import news.factory.com.home.view.HomeMainActivityImpl;
import news.factory.com.single.main.di.SingleMainActivityModule;
import news.factory.com.single.main.view.SingleMainActivity;
import news.factory.com.scopes.PerActivity;

@Module
public abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector(modules = SingleMainActivityModule.class)
    public abstract SingleMainActivity provideMainActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = HomeMainActivityModule.class)
    public abstract HomeMainActivityImpl provideHomeMainActivity();
}