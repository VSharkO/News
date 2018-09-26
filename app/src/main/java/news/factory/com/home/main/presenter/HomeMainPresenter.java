package news.factory.com.home.main.presenter;
import java.util.List;

import news.factory.com.model.home.BottomMenuData;

public interface HomeMainPresenter {
    void getData();

    List<BottomMenuData> getBottomMenuData();
}
