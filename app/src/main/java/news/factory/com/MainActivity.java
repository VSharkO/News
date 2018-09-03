package news.factory.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.NetworkingHelper;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkingHelper helper = App.getInstance().getHelper();
        helper.getProductsFromAPI(new NetworkResponseListener<News>() {
            @Override
            public void onSuccess(News callback) {
                Timber.i(callback.getAuthor());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Timber.e(throwable);
            }

        }, Constants.TYPE,Constants.ID,Constants.PAGE_NUMBER);
    }
}
