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
import news.factory.com.scopes.PerFragment;
import news.factory.com.single.inner_pager_fragment.inner_pager_interactor.InnerArticlesInteractor;
import news.factory.com.single.inner_pager_fragment.inner_pager_interactor.InnerArticlesInteractorImpl;
import news.factory.com.single.inner_pager_fragment.presenter.InnerPagerFragmentPresenter;
import news.factory.com.single.inner_pager_fragment.view.InnerPagerFragment;
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
    HomeFragmentFrontPagePresenter provideInnerFragmentPresenter(HomeFragmentFrontPagePresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    ViewPagerAdapterImpl provideHomePagerAdapterImpl(FragmentManager fragmentManager){
        return new ViewPagerAdapterImpl(fragmentManager, Constants.HOME_TYPE);
    }

    @PerFragment
    @Provides
    ViewPagerAdapter provideHomePagerAdapter(FragmentManager fragmentManager){
        return new ViewPagerAdapterImpl(fragmentManager,Constants.HOME_TYPE);
    }

    @PerFragment
    @Provides
    HomeFrontPageInteractor provideInnerInteractor(HomeFrontPageInteractorImpl interactor, HomeFragmentFrontPageImpl fragment){
        fragment.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerFragment
    @Provides
    RecyclerAdapterImpl provideInnerRecyclerAdapterImpl(HomeFragmentFrontPagePresenter presenter){
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
