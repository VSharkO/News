package news.factory.com.main.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;
import news.factory.com.main.view.MainActivity;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.networking.helpers.ArticleInteractorImpl;
import news.factory.com.scopes.PerActivity;

@Module
public class MainActivityModule {

    @PerActivity
    @Provides
    MainActivityView provideMainView(){
        return new MainActivity();
    }

    @PerActivity
    @Provides
    MainActivityPresenter provideMainPresenter(MainActivity mainView, ArticleInteractor interactor){
        return new MainActivityPresenterImpl(mainView,interactor);
    }

    @PerActivity
    @Provides
    ArticleInteractor provideInteractor(ArticleInteractorImpl interactor){
        return interactor;
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapter providePagerAdapter(MainActivity mainActivity){
        return new ViewPagerFragmentAdapter(mainActivity.getSupportFragmentManager());
    }

}
