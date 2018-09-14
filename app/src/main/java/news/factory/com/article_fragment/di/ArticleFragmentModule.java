package news.factory.com.article_fragment.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import news.factory.com.base.RecyclerAdapter;
import news.factory.com.scopes.PerActivity;
import news.factory.com.scopes.PerFragment;
import news.factory.com.utils.AppStatics;
import news.factory.com.utils.AppStaticsImpl;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenterImpl;
import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.article_fragment.view.ArticleFragmentView;
import news.factory.com.networking.helpers.ArticleInteractor;

@Module
public class ArticleFragmentModule {

    @PerFragment
    @Provides
    ArticleFragmentView provideFragmentView(ArticleFragment fragment){
        return fragment;
    }

    @PerFragment
    @Provides
    ArticleFragmentPresenter providePresenter(ArticleFragmentView fragment, ArticleInteractor interactor,AppStatics statics){
        return new ArticleFragmentPresenterImpl(fragment,interactor,statics);
    }

    @PerFragment
    @Provides
    AppStatics provideContext(Context app){
        return new AppStaticsImpl(app);
    }

    @PerFragment
    @Provides
    RecyclerAdapter provideAdapter(){
        return new RecyclerAdapter();
    }
}
