package news.factory.com.main.di;

import dagger.Module;
import dagger.Provides;
import news.factory.com.article_fragment.di.ArticleFragmentComponent;
import news.factory.com.main.presenter.MainActivityPresenter;
import news.factory.com.main.presenter.MainActivityPresenterImpl;
import news.factory.com.main.view.MainActivity;
import news.factory.com.main.view.MainActivityView;
import news.factory.com.networking.Service;
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.networking.helpers.ArticleInteractorImpl;

@Module(subcomponents = ArticleFragmentComponent.class)
public class MainActivityModule {

    @Provides
    MainActivityView provideMainView(){
        return new MainActivity();
    }

    @Provides
    MainActivityPresenter provideMainPresenter(MainActivity mainView, ArticleInteractor interactor){
        return new MainActivityPresenterImpl(mainView,interactor){
        };
    }

    @Provides
    ArticleInteractor provideInteractor(Service service){
        return new ArticleInteractorImpl(service);
    }

}
