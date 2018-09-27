package news.factory.com.home.home_other_pages_holder.presenter;

import javax.inject.Inject;

public class HomeFragmentOtherPagesPresenterImpl implements HomeFragmentOtherPagesPresenter {

    private int holderItemNumber;

    @Inject
    public HomeFragmentOtherPagesPresenterImpl() {
    }

    @Override
    public void setHolderItemNumber(int number){
        holderItemNumber = number;
    }

    @Override
    public int getHolderItemNumber() {
        return holderItemNumber;
    }

}
