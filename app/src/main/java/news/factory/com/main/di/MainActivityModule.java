package news.factory.com.main.di;

import android.arch.lifecycle.Lifecycle;

import dagger.Module;
import dagger.Provides;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;
import news.factory.com.main.view.MainActivity;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.networking.helpers.ArticleInteractorImpl;
import news.factory.com.scopes.PerActivity;

@Module
public class MainActivityModule {

    @PerActivity
    @Provides
    MainActivityView provideMainView(MainActivity mainView){
        return mainView;
    }

    @PerActivity
    @Provides
    MainActivityPresenter provideMainPresenter(MainActivityPresenterImpl presenter){
        return presenter;
    }

    @PerActivity
    @Provides
    ArticleInteractor provideInteractor(ArticleInteractorImpl interactor){
        return interactor;
    }

    @PerActivity
    @Provides
    Lifecycle provideLifecycle(MainActivity activity){
        return activity.getLifecycle();
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapter providePagerAdapter(MainActivity mainActivity){
        return new ViewPagerFragmentAdapter(mainActivity.getSupportFragmentManager());
    }
}
