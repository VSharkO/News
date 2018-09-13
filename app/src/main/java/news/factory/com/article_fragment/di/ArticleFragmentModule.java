package news.factory.com.article_fragment.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenterImpl;
import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.article_fragment.view.ArticleFragmentView;
import news.factory.com.networking.Service;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.networking.helpers.ArticleInteractorImpl;

@Module
public class ArticleFragmentModule {

    @Provides
    ArticleFragmentView provideFragmentView(ArticleFragment fragment){
        return fragment;
    }

    @Provides
    ArticleFragmentPresenter providePresenter(ArticleFragmentView fragment, ArticleInteractor interactor){
        return new ArticleFragmentPresenterImpl(fragment,interactor);
    }

}
