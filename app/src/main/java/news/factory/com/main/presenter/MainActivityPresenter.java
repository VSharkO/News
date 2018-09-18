package news.factory.com.main.presenter;

public interface MainActivityPresenter {
    void getArticlesFromAPI();

    void setSwipeButtons(int index, int numOfItems);
}
