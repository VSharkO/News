package news.factory.com.home.home_other_pages_holder.di;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.home.home_other_pages_holder.presenter.HomeFragmentOtherPagesPresenterImpl;
import news.factory.com.home.home_other_pages_holder.view.HomeFragmentOtherPagesImpl;
import news.factory.com.home.home_other_pages_holder.presenter.HomeFragmentOtherPagesPresenter;
import news.factory.com.scopes.PerFragment;
import news.factory.com.utils.Constants;

@Module
public class HomeFragmentOtherPagesModule {

    @PerFragment
    @Provides
    ViewPagerAdapterImpl provideHomeItemAdapterImplementation(FragmentManager fragmentManager, HomeFragmentOtherPagesPresenter presenter){
        return new ViewPagerAdapterImpl(fragmentManager, Constants.TYPE_HOME_OTHER_ITEMS_TOP,presenter);
    }

    @PerFragment
    @Provides
    HomeFragmentOtherPagesPresenter provideHomeOtherItemsFragment(HomeFragmentOtherPagesPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    FragmentManager provideFragmentManager(HomeFragmentOtherPagesImpl fragment){
        return fragment.getChildFragmentManager();
    }
}
