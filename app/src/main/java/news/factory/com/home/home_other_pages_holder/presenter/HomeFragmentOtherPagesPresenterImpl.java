package news.factory.com.home.home_other_pages_holder.presenter;

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
