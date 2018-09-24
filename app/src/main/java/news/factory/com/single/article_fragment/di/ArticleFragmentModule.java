package news.factory.com.single.article_fragment.di;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.single.article_fragment.article_interactor.ArticleInteractor;
import news.factory.com.single.article_fragment.article_interactor.ArticleInteractorImpl;
import news.factory.com.scopes.PerFragment;
import news.factory.com.single.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.single.article_fragment.presenter.ArticleFragmentPresenterImpl;
import news.factory.com.single.article_fragment.view.ArticleFragment;
import news.factory.com.single.article_fragment.view.ArticleFragmentView;
import news.factory.com.utils.PresenterWithFragmentChildManager;

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
    RecyclerAdapterImpl provideRecyclerAdapterImpl(ArticleFragmentPresenter presenter,ArticleFragment view){
        Object presenterWithFragmentChildManager = new PresenterWithFragmentChildManager(presenter,view.getChildFragmentManager());
        return new RecyclerAdapterImpl(presenterWithFragmentChildManager);
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideRecyclerAdapter(RecyclerAdapterImpl adapter){
        return adapter;
    }
}


