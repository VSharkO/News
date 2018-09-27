package news.factory.com.home.home_pages_holder.presenter;

import javax.inject.Inject;

public class HomePagesHolderPresenterImpl implements HomePagesHolderPresenter {

    int index;

    @Inject
    public HomePagesHolderPresenterImpl() {

    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }
}
