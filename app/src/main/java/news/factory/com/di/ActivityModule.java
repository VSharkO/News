package news.factory.com.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.main.di.MainActivityModule;
import news.factory.com.main.view.MainActivity;
import news.factory.com.scopes.PerActivity;

@Module
public abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    public abstract MainActivity provideMainActivity();
}