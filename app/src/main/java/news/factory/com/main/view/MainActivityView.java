package news.factory.com.main.view;

import java.util.List;

import news.factory.com.model.News;

public interface MainActivityView {
    void setupPager(List<News> news);
}
