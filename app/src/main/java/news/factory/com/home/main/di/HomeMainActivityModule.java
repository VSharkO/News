package news.factory.com.home.main.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapter;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.home.main.interactor.HomeMainInteractor;
import news.factory.com.home.main.interactor.HomeMainInteractorImpl;
import news.factory.com.home.main.presenter.HomeMainPresenter;
import news.factory.com.home.main.view.HomeMainActivity;
import news.factory.com.home.main.view.HomeMainActivityImpl;
import news.factory.com.home.main.presenter.HomeMainPresenterImpl;
import news.factory.com.scopes.PerActivity;
import news.factory.com.utils.Constants;

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
    HomeMainInteractor provideInteractor(HomeMainInteractorImpl interactor, HomeMainActivityImpl homeMainActivity){
        homeMainActivity.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerActivity
    @Provides
    ViewPagerAdapterImpl providePagerAdapterImpl(HomeMainActivityImpl homeMainActivity,HomeMainPresenter presenter){
        return new ViewPagerAdapterImpl(homeMainActivity.getSupportFragmentManager(), Constants.HOME_TYPE,presenter);
    }

    @PerActivity
    @Provides
    ViewPagerAdapter providePagerAdapter(ViewPagerAdapterImpl adapter){
        return adapter;
    }
}
