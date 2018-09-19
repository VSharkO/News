package news.factory.com.main.di;
import dagger.Module;
import dagger.Provides;
import news.factory.com.base.adapters.ViewPagerFragmentAdapter;
import news.factory.com.base.adapters.ViewPagerFragmentAdapterImpl;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;
import news.factory.com.main.view.MainActivity;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.networking.interactor.article_interactor.ArticleInteractor;
import news.factory.com.networking.interactor.article_interactor.ArticleInteractorImpl;
import news.factory.com.scopes.PerActivity;

@Module
public class MainActivityModule {

    @PerActivity
    @Provides
    MainActivityView provideMainView(MainActivity mainView){
        return mainView;
    }

    @PerActivity
    @Provides
    MainActivityPresenter provideMainPresenter(MainActivityPresenterImpl presenter){
        return presenter;
    }

    @PerActivity
    @Provides
    ArticleInteractor provideInteractor(ArticleInteractorImpl interactor, MainActivity mainActivity){
        mainActivity.getLifecycle().addObserver(interactor);
        return interactor;
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapterImpl providePagerAdapterImpl(MainActivity mainActivity){
        return new ViewPagerFragmentAdapterImpl(mainActivity.getSupportFragmentManager());
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapter providePagerAdapter(ViewPagerFragmentAdapterImpl adapter){
        return adapter;
    }

}
