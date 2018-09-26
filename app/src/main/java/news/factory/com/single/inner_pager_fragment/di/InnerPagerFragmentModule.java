package news.factory.com.single.inner_pager_fragment.di;
import android.support.v4.app.FragmentManager;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapterImpl;
import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapter;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.single.inner_pager_fragment.presenter.InnerPagerFragmentPresenterImpl;
import news.factory.com.single.inner_pager_fragment.view.InnerPagerFragment;
import news.factory.com.single.inner_pager_fragment.presenter.InnerPagerFragmentPresenter;
import news.factory.com.single.inner_pager_fragment.view.InnerPagerFragmentView;
import news.factory.com.single.inner_pager_fragment.inner_pager_interactor.InnerArticlesInteractor;
import news.factory.com.single.inner_pager_fragment.inner_pager_interactor.InnerArticlesInteractorImpl;
import news.factory.com.scopes.PerFragment;
import news.factory.com.utils.Constants;

@Module
public class InnerPagerFragmentModule {

    @PerFragment
    @Provides
    InnerPagerFragmentView provideFragmentView(InnerPagerFragment fragment){
        return fragment;
    }

    @PerFragment
    @Provides
    InnerPagerFragmentPresenter provideInnerFragmentPresenter(InnerPagerFragmentPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    ViewPagerAdapterImpl provideInnerAdapterImplementation(FragmentManager fragmentManager,InnerPagerFragmentPresenter presenter){
        return new ViewPagerAdapterImpl(fragmentManager, Constants.SINGLE_INNER_HOLDER_TYPE,presenter);
    }

    @PerFragment
    @Provides
    ViewPagerAdapter provideInnerAdapter(FragmentManager fragmentManager,InnerPagerFragmentPresenter presenter){
        return new ViewPagerAdapterImpl(fragmentManager,Constants.SINGLE_INNER_HOLDER_TYPE,presenter);
    }

    @PerFragment
    @Provides
    InnerArticlesInteractor provideInnerInteractor(InnerArticlesInteractorImpl interactor, InnerPagerFragment fragment){
        fragment.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerFragment
    @Provides
    RecyclerAdapterImpl provideInnerRecyclerAdapterImpl(InnerPagerFragmentPresenter presenter){
        return new RecyclerAdapterImpl(presenter);
    }

    @PerFragment
    @Provides
    FragmentManager provideFragmentManager(InnerPagerFragment fragment){
        return fragment.getChildFragmentManager();
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl adapter){
        return adapter;
    }
}
