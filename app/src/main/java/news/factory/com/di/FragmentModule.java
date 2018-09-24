package news.factory.com.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.factory.com.home.home_fragment_front_page.di.HomeFragmentFrontPageModule;
import news.factory.com.home.home_fragment_front_page.view.HomeFragmentFrontPageImpl;
import news.factory.com.single.article_fragment.di.ArticleFragmentModule;
import news.factory.com.single.article_fragment.view.ArticleFragment;
import news.factory.com.single.inner_pager_fragment.di.InnerPagerFragmentModule;
import news.factory.com.single.inner_pager_fragment.view.InnerPagerFragment;
import news.factory.com.scopes.PerFragment;

@Module
public abstract class FragmentModule {

    @PerFragment
    @ContributesAndroidInjector(modules = ArticleFragmentModule.class)
    public abstract ArticleFragment provideArticleFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = InnerPagerFragmentModule.class)
    public abstract InnerPagerFragment provideInnerPagerFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = HomeFragmentFrontPageModule.class)
    public abstract HomeFragmentFrontPageImpl provideHomeFrontPageFragment();

}
