package news.factory.com.home.home_item_fragment.di;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.home.home_item_fragment.presenter.HomeFragmentItemPresenter;
import news.factory.com.home.home_item_fragment.presenter.HomeFragmentItemPresenterImpl;
import news.factory.com.home.home_item_fragment.view.HomeFragmentItemImpl;
import news.factory.com.home.home_item_fragment.interactor.HomePagesHolderInteractor;
import news.factory.com.home.home_item_fragment.interactor.HomePagesHolderInteractorImpl;
import news.factory.com.home.home_pages_holder.presenter.HomePagesHolderPresenter;
import news.factory.com.home.home_pages_holder.presenter.HomePagesHolderPresenterImpl;
import news.factory.com.scopes.PerFragment;

@Module
public class HomeItemModule {

    @PerFragment
    @Provides
    HomeFragmentItemPresenter provideHomeOtherPagesFragmentItemPresenter(HomeFragmentItemPresenterImpl presenter){
        return presenter;
    }


    @PerFragment
    @Provides
    HomePagesHolderInteractor provideHomePagesHolderInteractor(HomeFragmentItemImpl fragmentItem,HomePagesHolderInteractorImpl interactor){
        fragmentItem.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerFragment
    @Provides
    HomePagesHolderPresenter providePagesPresenter(HomePagesHolderPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    RecyclerAdapterImpl provideInnerRecyclerAdapterImpl(HomeFragmentItemPresenter presenter){
        return new RecyclerAdapterImpl(presenter);
    }

    @PerFragment
    @Provides
    FragmentManager provideFragmentManager(HomeFragmentItemImpl fragment){
        return fragment.getChildFragmentManager();
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl adapter) {
        return adapter;
    }
}
