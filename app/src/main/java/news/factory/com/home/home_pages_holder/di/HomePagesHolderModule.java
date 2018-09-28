package news.factory.com.home.home_pages_holder.di;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.home.home_item_fragment.interactor.HomePagesHolderInteractor;
import news.factory.com.home.home_item_fragment.interactor.HomePagesHolderInteractorImpl;
import news.factory.com.home.home_pages_holder.presenter.HomePagesHolderPresenterImpl;
import news.factory.com.home.home_pages_holder.presenter.HomePagesHolderPresenter;
import news.factory.com.home.home_pages_holder.view.HomePagesFragmentImpl;
import news.factory.com.scopes.PerFragment;
import news.factory.com.utils.Constants;

@Module
public class HomePagesHolderModule {

    @PerFragment
    @Provides
    ViewPagerAdapterImpl provideHomeItemAdapterImplementation(FragmentManager fragmentManager, HomePagesHolderPresenter presenter){
        return new ViewPagerAdapterImpl(fragmentManager, Constants.TYPE_HOME_ITEMS,presenter);
    }

    @PerFragment
    @Provides
    HomePagesHolderInteractor provideHomePagesInteractor(HomePagesFragmentImpl fragment, HomePagesHolderInteractorImpl interactor){
        fragment.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerFragment
    @Provides
    HomePagesHolderPresenter provideOtherPagesPresenter(HomePagesHolderPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    FragmentManager provideFragmentManager(HomePagesFragmentImpl fragment){
        return fragment.getChildFragmentManager();
    }
}
