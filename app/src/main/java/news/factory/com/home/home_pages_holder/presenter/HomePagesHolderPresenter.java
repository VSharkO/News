package news.factory.com.home.home_pages_holder.presenter;


import news.factory.com.model.single.TopNews;

public interface HomePagesHolderPresenter {
    void setData(int index);

    TopNews[] getData();
}
