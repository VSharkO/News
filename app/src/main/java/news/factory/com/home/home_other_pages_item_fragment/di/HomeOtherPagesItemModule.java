package news.factory.com.home.home_other_pages_item_fragment.di;

import android.support.v4.app.FragmentManager;

import dagger.Provides;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.home.home_other_pages_holder.presenter.HomeFragmentOtherPagesPresenter;
import news.factory.com.home.home_other_pages_holder.presenter.HomeFragmentOtherPagesPresenterImpl;
import news.factory.com.home.home_other_pages_item_fragment.view.HomeOtherPagesFragmentItem;
import news.factory.com.home.home_other_pages_item_fragment.HomeOtherPagesFragmentItemPresenter;
import news.factory.com.home.home_other_pages_item_fragment.HomeOtherPagesFragmentItemPresenterImpl;
import news.factory.com.home.home_other_pages_item_fragment.interactor.HomeFragmentOtherPagesInteractor;
import news.factory.com.home.home_other_pages_item_fragment.interactor.HomeFragmentOtherPagesInteractorImpl;
import news.factory.com.scopes.PerFragment;

public class HomeOtherPagesItemModule {

    @PerFragment
    @Provides
    HomeFragmentOtherPagesPresenter provideHomeOtherItemsFragment(HomeFragmentOtherPagesPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    HomeOtherPagesFragmentItemPresenter provideHomeOtherPagesFragmentItemPresenter(HomeOtherPagesFragmentItemPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    HomeFragmentOtherPagesInteractor provideInteractor(HomeFragmentOtherPagesInteractorImpl interactor){
        return interactor;
    }

    @PerFragment
    @Provides
    RecyclerAdapterImpl provideInnerRecyclerAdapterImpl(HomeOtherPagesFragmentItemPresenter presenter){
        return new RecyclerAdapterImpl(presenter);
    }

    @PerFragment
    @Provides
    FragmentManager provideFragmentManager(HomeOtherPagesFragmentItem fragment){
        return fragment.getChildFragmentManager();
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl adapter) {
        return adapter;
    }
}
