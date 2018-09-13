package news.factory.com.componentBuilders;
import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import news.factory.com.article_fragment.di.ArticleFragmentComponent;
import news.factory.com.article_fragment.di.ArticleFragmentModule;
import news.factory.com.article_fragment.view.ArticleFragment;

@Module(includes = {ArticleFragmentModule.class})
public abstract class FragmentBuilder {

    @Binds
    @IntoMap
    @FragmentKey(ArticleFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMainActivity(ArticleFragmentComponent.Builder builder);
}
