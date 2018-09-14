package news.factory.com.main.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import news.factory.com.componentBuilders.ArticleFragmentBuilder;
import news.factory.com.main.view.MainActivity;
import news.factory.com.scopes.PerActivity;

@PerActivity
@Subcomponent(modules = {MainActivityModule.class,ArticleFragmentBuilder.class})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
