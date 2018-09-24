package news.factory.com.home.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerFragmentAdapter;
import news.factory.com.base.adapters.pager_adapter.ViewPagerFragmentAdapterImpl;
import news.factory.com.home.presenter.HomeMainPresenter;
import news.factory.com.home.view.HomeMainActivity;
import news.factory.com.home.view.HomeMainActivityImpl;
import news.factory.com.home.presenter.HomeMainPresenterImpl;
import news.factory.com.networking.interactor.article_interactor.ArticleInteractor;
import news.factory.com.networking.interactor.article_interactor.ArticleInteractorImpl;
import news.factory.com.scopes.PerActivity;

@Module
public class HomeMainActivityModule {

    @PerActivity
    @Provides
    HomeMainActivity provideMainView(HomeMainActivityImpl homeMain){
        return homeMain;
    }

    @PerActivity
    @Provides
    HomeMainPresenter provideHomeMainPresenter(HomeMainPresenterImpl presenter){
        return presenter;
    }

    @PerActivity
    @Provides
    ArticleInteractor provideInteractor(ArticleInteractorImpl interactor, HomeMainActivityImpl homeMainActivity){
        homeMainActivity.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapterImpl providePagerAdapterImpl(HomeMainActivityImpl homeMainActivity){
        return new ViewPagerFragmentAdapterImpl(homeMainActivity.getSupportFragmentManager());
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapter providePagerAdapter(ViewPagerFragmentAdapterImpl adapter){
        return adapter;
    }
}
