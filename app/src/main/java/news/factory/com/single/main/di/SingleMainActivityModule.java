package news.factory.com.single.main.di;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.pager_adapter.ViewPagerFragmentAdapter;
import news.factory.com.base.adapters.pager_adapter.ViewPagerFragmentAdapterImpl;
import news.factory.com.single.main.presenter.SingleMainActivityPresenter;
import news.factory.com.single.main.presenter.SingleMainActivityPresenterImpl;
import news.factory.com.single.main.view.SingleMainActivity;
import news.factory.com.single.main.view.SingleMainActivityView;
import news.factory.com.single.article_fragment.article_interactor.ArticleInteractor;
import news.factory.com.single.article_fragment.article_interactor.ArticleInteractorImpl;
import news.factory.com.scopes.PerActivity;

@Module
public class SingleMainActivityModule {

    @PerActivity
    @Provides
    SingleMainActivityView provideMainView(SingleMainActivity mainView){
        return mainView;
    }

    @PerActivity
    @Provides
    SingleMainActivityPresenter provideMainPresenter(SingleMainActivityPresenterImpl presenter){
        return presenter;
    }

    @PerActivity
    @Provides
    ArticleInteractor provideInteractor(ArticleInteractorImpl interactor, SingleMainActivity singleMainActivity){
        singleMainActivity.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapterImpl providePagerAdapterImpl(SingleMainActivity singleMainActivity){
        return new ViewPagerFragmentAdapterImpl(singleMainActivity.getSupportFragmentManager());
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapter providePagerAdapter(ViewPagerFragmentAdapterImpl adapter){
        return adapter;
    }

}
