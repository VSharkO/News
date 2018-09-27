package news.factory.com.home.home_fragment_other_pages.presenter;

import javax.inject.Inject;

import dagger.Lazy;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapter;
import news.factory.com.home.home_fragment_other_pages.interactor.HomeFragmentOtherPagesInteractor;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;

public class HomeFragmentOtherPagesPresenterImpl implements HomeFragmentOtherPagesPresenter, NetworkResponseListener {

    private HomeFragmentOtherPagesInteractor interactor;
    private HomeFragmentOtherPagesPresenter presenter;
    private Lazy<RecyclerAdapter> adapter;
    private int holderItemNumber;

    @Inject
    public HomeFragmentOtherPagesPresenterImpl(HomeFragmentOtherPagesInteractor interactor, HomeFragmentOtherPagesPresenter presenter,Lazy<RecyclerAdapter> adapter) {
        this.interactor = interactor;
        this.presenter = presenter;
        this.adapter = adapter;
    }

    @Override
    public void setData(int index) {
        String type;
        switch (index){
            case 0:
                type = Constants.MOST_NEW_TYPE;
                break;
            case 1:
                type = Constants.MOST_READ_TYPE;
                break;
            default:
                type = Constants.MOST_POPULAR_TYPE;
        }
        interactor.getHomeOtherPagesData(this,type,holderItemNumber);
    }

    @Override
    public void onSuccess(InteractorData callback) {

    }

    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public void setHolderItemNumber(int number){
        holderItemNumber = number;
    }
}
