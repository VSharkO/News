package news.factory.com.main.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.article_fragment.di.ArticleFragmentComponent;
import news.factory.com.main.adapter.ViewPagerFragmentAdapter;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;
import news.factory.com.main.view.MainActivity;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.networking.Service;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.networking.helpers.ArticleInteractorImpl;
import news.factory.com.scopes.PerActivity;

@Module(subcomponents = ArticleFragmentComponent.class)
public class MainActivityModule {

    @PerActivity
    @Provides
    MainActivityView provideMainView(){
        return new MainActivity();
    }

    @PerActivity
    @Provides
    MainActivityPresenter provideMainPresenter(MainActivity mainView, ArticleInteractor interactor){
        return new MainActivityPresenterImpl(mainView,interactor){
        };
    }

    @PerActivity
    @Provides
    ArticleInteractor provideInteractor(Service service){
        return new ArticleInteractorImpl(service);
    }

    @PerActivity
    @Provides
    ViewPagerFragmentAdapter providePagerAdapter(MainActivity mainActivity){
        return new ViewPagerFragmentAdapter(mainActivity.getSupportFragmentManager());
    }

}
