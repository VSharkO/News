package news.factory.com.home.main.presenter;
import java.util.List;

import javax.inject.Inject;
import news.factory.com.home.main.interactor.HomeMainInteractor;
import news.factory.com.home.main.view.HomeMainActivity;
import news.factory.com.model.home.BottomMenuData;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class HomeMainPresenterImpl implements HomeMainPresenter, NetworkResponseListener {

    private HomeMainActivity view;
    private HomeMainInteractor homeMainInteractor;
    List<BottomMenuData> data;

    @Inject
    public HomeMainPresenterImpl(HomeMainActivity view, HomeMainInteractor mainInteractor) {
        this.view = view;
        this.homeMainInteractor = mainInteractor;

    }

    @Override
    public void getData() {
        homeMainInteractor.getBottomMenuData(this,Constants.MENU_BOTTOM);
    }

    @Override
    public void onSuccess(InteractorData dataReceive) {
        this.data = (List<BottomMenuData>) dataReceive.getData();
        view.setAdapterCount(data.size());
        view.setNavigationViewItems((List<BottomMenuData>)dataReceive.getData());
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public List<BottomMenuData> getBottomMenuData(){
        return data;
    }
}
