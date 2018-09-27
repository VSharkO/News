package news.factory.com.networking;
import java.util.List;
import io.reactivex.Observable;
import news.factory.com.model.home.BottomMenuData;
import news.factory.com.model.single.News;
import news.factory.com.model.single.TopNews;
import news.factory.com.utils.Constants;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface Service {

    @GET("{type}/{id}")
    Observable<News> getNews(@Path("type") String type, @Path("id") String id,
                             @Query(Constants.PAGE) String pageNumber);

    @GET("{type}/{id}")
    Observable<TopNews> getInnerArticles(@Path("type") String type, @Path("id") String id,
                                         @Query(Constants.PAGE) String pageNumber);
    @GET("{type}")
    Observable<List<TopNews>> getHomeData(@Path("type") String type);

    @GET("{type}")
    Observable<List<BottomMenuData>> getBottomMenuData(@Path("type") String type);

    @GET("{type}/{id}")
    Observable<List<BottomMenuData>> getHomeItemData(@Path("type") String type, @Path("id") String index,
                                                     @Query(Constants.PAGE) String pageNumber);
}
