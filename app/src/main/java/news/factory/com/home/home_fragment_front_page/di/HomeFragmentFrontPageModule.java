package news.factory.com.home.home_fragment_front_page.di;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapter;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractor;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractorImpl;
import news.factory.com.home.home_fragment_front_page.presenter.HomeFragmentFrontPagePresenter;
import news.factory.com.home.home_fragment_front_page.presenter.HomeFragmentFrontPagePresenterImpl;
import news.factory.com.home.home_fragment_front_page.view.HomeFragmentFrontPage;
import news.factory.com.home.home_fragment_front_page.view.HomeFragmentFrontPageImpl;
import news.factory.com.home.main.presenter.HomeMainPresenter;
import news.factory.com.scopes.PerFragment;
import news.factory.com.utils.Constants;

@Module
public class HomeFragmentFrontPageModule {

    @PerFragment
    @Provides
    HomeFragmentFrontPage provideFragmentView(HomeFragmentFrontPageImpl fragment){
        return fragment;
    }

    @PerFragment
    @Provides
    HomeFragmentFrontPagePresenter provideHomeFragmentPresenter(HomeFragmentFrontPagePresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    ViewPagerAdapterImpl provideHomePagerAdapterImpl(FragmentManager fragmentManager, HomeMainPresenter presenter){
        return new ViewPagerAdapterImpl(fragmentManager, Constants.HOME_TYPE, presenter);
    }

    @PerFragment
    @Provides
    ViewPagerAdapter provideHomePagerAdapter(ViewPagerAdapterImpl adapter){
        return adapter;
    }

    @PerFragment
    @Provides
    HomeFrontPageInteractor provideHomeFragmentInteractor(HomeFrontPageInteractorImpl interactor, HomeFragmentFrontPageImpl fragment){
        fragment.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerFragment
    @Provides
    RecyclerAdapterImpl provideHomeRecyclerAdapterImpl(HomeFragmentFrontPagePresenter presenter){
        return new RecyclerAdapterImpl(presenter);
    }

    @PerFragment
    @Provides
    FragmentManager provideFragmentManager(HomeFragmentFrontPageImpl fragment){
        return fragment.getChildFragmentManager();
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl adapter){
        return adapter;
    }
}
