package news.factory.com.single.main.presenter;

public interface SingleMainActivityPresenter {
    void getArticlesFromAPI();

    void setSwipeButtons(int index, int numOfItems);
}
