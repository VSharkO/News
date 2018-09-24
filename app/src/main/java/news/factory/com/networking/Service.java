package news.factory.com.networking;

import io.reactivex.Observable;
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
}
