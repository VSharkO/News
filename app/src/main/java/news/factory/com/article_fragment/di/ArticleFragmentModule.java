package news.factory.com.article_fragment.di;
import android.arch.lifecycle.Lifecycle;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.RecyclerAdapter;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.networking.helpers.ArticleInteractorImpl;
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
    RecyclerAdapter provideAdapter(){
        return new RecyclerAdapter();
    }
}
