package news.factory.com.home.home_pages_holder.presenter;
import javax.inject.Inject;

import news.factory.com.home.home_pages_holder.interactor.HomePagesHolderInteractor;
import news.factory.com.model.single.TopNews;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class HomePagesHolderPresenterImpl implements HomePagesHolderPresenter, NetworkResponseListener {

    private HomePagesHolderInteractor interactor;
    TopNews[] data;

    @Inject
    public HomePagesHolderPresenterImpl(HomePagesHolderInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void onSuccess(InteractorData callback) {
        data = (TopNews[])callback.getData(); //todo
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }

    @Override
    public void setData(int index) {
        interactor.getHomeOtherPagesData(this, index);
    }

    @Override
    public TopNews[] getData(){
        return data;
    }
}
