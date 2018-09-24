package news.factory.com.home.main.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapter;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractor;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractorImpl;
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
    HomeFrontPageInteractor provideInteractor(HomeFrontPageInteractorImpl interactor, HomeMainActivityImpl homeMainActivity){
        homeMainActivity.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerActivity
    @Provides
    ViewPagerAdapterImpl providePagerAdapterImpl(HomeMainActivityImpl homeMainActivity){
        return new ViewPagerAdapterImpl(homeMainActivity.getSupportFragmentManager(), Constants.HOME_TYPE);
    }

    @PerActivity
    @Provides
    ViewPagerAdapter providePagerAdapter(ViewPagerAdapterImpl adapter){
        return adapter;
    }
}
