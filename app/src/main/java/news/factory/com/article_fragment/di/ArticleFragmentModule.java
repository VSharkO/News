package news.factory.com.article_fragment.di;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.RecyclerAdapter;
import news.factory.com.base.adapters.RecyclerAdapterImpl;
import news.factory.com.base.adapters.ViewPagerInnerAdapter;
import news.factory.com.base.adapters.ViewPagerInnerAdapterImpl;
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
    RecyclerAdapterImpl provideAdapterImpl(ArticleFragmentPresenter presenter, ViewPagerInnerAdapterImpl adapter){
        return new RecyclerAdapterImpl(presenter,adapter);
    }

    @PerFragment
    @Provides
    ViewPagerInnerAdapterImpl provideInnerAdapterImpl(ViewPagerInnerAdapter adapter){
        return new ViewPagerInnerAdapterImpl(adapter.getFragmentManager());
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideAdapter(RecyclerAdapterImpl adapter){
        return adapter;
    }

}


