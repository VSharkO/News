package news.factory.com.article_fragment.di;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.base.adapters.pager_adapter.ViewPagerInnerAdapterImpl;
import news.factory.com.inner_pager_fragment.presenter.InnerPagerFragmentPresenter;
import news.factory.com.inner_pager_fragment.presenter.InnerPagerFragmentPresenterImpl;
import news.factory.com.networking.interactor.article_interactor.ArticleInteractor;
import news.factory.com.networking.interactor.article_interactor.ArticleInteractorImpl;
import news.factory.com.scopes.PerFragment;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenterImpl;
import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.article_fragment.view.ArticleFragmentView;

@Module
public class ArticleFragmentModule {

    @PerFragment
    @Provides
    ArticleFragmentView provideFragmentView(ArticleFragment fragment){
        return fragment;
    }

    @PerFragment
    @Provides
    ArticleFragmentPresenter providePresenter(ArticleFragmentPresenterImpl presenter){
        return presenter;
    }

    @PerFragment
    @Provides
    ArticleInteractor provideInteractor(ArticleInteractorImpl interactor, ArticleFragment fragment){
        fragment.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerFragment
    @Provides
    RecyclerAdapterImpl provideRecyclerAdapterImpl(ArticleFragmentPresenter presenter, ViewPagerInnerAdapterImpl adapter){
        return new RecyclerAdapterImpl(presenter,adapter);
    }

    @PerFragment
    @Provides
    ViewPagerInnerAdapterImpl providePagerInnerAdapterImpl(ArticleFragment fragment){
        return new ViewPagerInnerAdapterImpl(fragment.getChildFragmentManager());
    }


    @PerFragment
    @Provides
    RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl adapter){
        return adapter;
    }

}


