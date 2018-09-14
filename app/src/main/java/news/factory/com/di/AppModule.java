package news.factory.com.di;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import news.factory.com.App;
import news.factory.com.utils.ResourceRepo;
import news.factory.com.utils.ResourseRepoImpl;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    App provideContext(){
        return app;
    }

    @Singleton
    @Provides
    ResourceRepo provideResourceRepo(App app){
        return new ResourseRepoImpl(app);
    }
}
