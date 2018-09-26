package news.factory.com.home.main.presenter;
import java.util.List;

import news.factory.com.model.single.TopNews;

public interface HomeMainPresenter {
    void getData();

    List<TopNews> getBottomMenuData();
}
