package news.factory.com.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.article_fragment.di.ArticleFragmentModule;
import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.inner_pager_fragment.di.InnerPagerFragmentModule;
import news.factory.com.inner_pager_fragment.view.InnerPagerFragment;
import news.factory.com.scopes.PerFragment;

@Module
public abstract class FragmentModule {
    @PerFragment
    @ContributesAndroidInjector(modules = ArticleFragmentModule.class)
    public abstract ArticleFragment provideArticleFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = InnerPagerFragmentModule.class)
    public abstract InnerPagerFragment provideInnerPagerFragment();
}
