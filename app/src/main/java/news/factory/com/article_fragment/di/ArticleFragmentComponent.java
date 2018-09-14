package news.factory.com.article_fragment.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import news.factory.com.AppModuleRepository;
import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.networking.di.NetworkingModule;
import news.factory.com.scopes.PerFragment;

@PerFragment
@Subcomponent(modules = {ArticleFragmentModule.class, NetworkingModule.class, AppModuleRepository.class})
public interface ArticleFragmentComponent extends AndroidInjector<ArticleFragment>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<ArticleFragment>{}
}
