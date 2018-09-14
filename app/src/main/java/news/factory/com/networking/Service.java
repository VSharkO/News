package news.factory.com.networking;

import io.reactivex.Observable;
import io.reactivex.Single;
import news.factory.com.model.News;
import news.factory.com.utils.Constants;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface Service {

    @GET("{type}/{id}")
    Observable<News> getNews(@Path("type") String type, @Path("id") String id,
                             @Query(Constants.PAGE) String pageNumber);

}
