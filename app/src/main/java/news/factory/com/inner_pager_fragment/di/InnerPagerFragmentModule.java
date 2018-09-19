package news.factory.com.inner_pager_fragment.di;
import android.support.v4.app.FragmentManager;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.RecyclerAdapter;
import news.factory.com.base.adapters.RecyclerAdapterImpl;
import news.factory.com.base.adapters.ViewPagerInnerAdapterImpl;
import news.factory.com.inner_pager_fragment.presenter.InnerPagerFragmentPresenterImpl;
import news.factory.com.inner_pager_fragment.view.InnerPagerFragment;
import news.factory.com.inner_pager_fragment.presenter.InnerPagerFragmentPresenter;
import news.factory.com.inner_pager_fragment.view.InnerPagerFragmentView;
import news.factory.com.networking.interactor.inner_pager_interactor.InnerArticlesInteractor;
import news.factory.com.networking.interactor.inner_pager_interactor.InnerArticlesInteractorImpl;
import news.factory.com.scopes.PerFragment;

@Module
public class InnerPagerFragmentModule {
    @PerFragment
    @Provides
    InnerPagerFragmentView provideFragmentView(InnerPagerFragment fragment){
        return fragment;
    }

    @PerFragment
    @Provides
    InnerPagerFragmentPresenter providePresenter(InnerPagerFragmentPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    InnerArticlesInteractor provideInteractor(InnerArticlesInteractorImpl interactor, InnerPagerFragment fragment){
        fragment.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerFragment
    @Provides
    RecyclerAdapterImpl provideAdapterImpl(InnerPagerFragmentPresenter presenter, Lazy<ViewPagerInnerAdapterImpl> adapter){
        return new RecyclerAdapterImpl(presenter,adapter);
    }

    @PerFragment
    @Provides
    FragmentManager provideFragmentManager(InnerPagerFragment fragment){
        return fragment.getFragmentManager();
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideAdapter(RecyclerAdapterImpl adapter){
        return adapter;
    }

}
