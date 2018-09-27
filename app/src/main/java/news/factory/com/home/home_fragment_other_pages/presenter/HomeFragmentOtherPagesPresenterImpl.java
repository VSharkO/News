package news.factory.com.home.home_fragment_other_pages.presenter;

public class HomeFragmentOtherPagesPresenterImpl implements HomeFragmentOtherPagesPresenter {

    private int holderItemNumber;

    @Override
    public void setHolderItemNumber(int number){
        holderItemNumber = number;
    }

    @Override
    public int getHolderItemNumber() {
        return holderItemNumber;
    }

}
