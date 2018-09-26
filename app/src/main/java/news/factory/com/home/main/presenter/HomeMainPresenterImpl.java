package news.factory.com.home.main.presenter;
import java.util.List;

import javax.inject.Inject;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractor;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractorImpl;
import news.factory.com.home.main.view.HomeMainActivity;
import news.factory.com.model.single.TopNews;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class HomeMainPresenterImpl implements HomeMainPresenter, NetworkResponseListener {

    private HomeMainActivity view;
    private HomeFrontPageInteractor homeFrontPageInteractor;
    List<TopNews> data;

    @Inject
    public HomeMainPresenterImpl(HomeMainActivity view, HomeFrontPageInteractorImpl homeFrontPageInteractor) {
        this.view = view;
        this.homeFrontPageInteractor = homeFrontPageInteractor;

    }

    @Override
    public void getData() {
        homeFrontPageInteractor.getHomeData(this,Constants.MENU_BOTTOM);
    }

    @Override
    public void onSuccess(InteractorData dataReceive) {
        this.data = (List<TopNews>) dataReceive.getData();
        view.setAdapterCount(data.size());
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public List<TopNews> getBottomMenuData(){
        return data;
    }
}
