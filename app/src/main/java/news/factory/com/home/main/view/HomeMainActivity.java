package news.factory.com.home.main.view;

import java.util.List;

import news.factory.com.model.home.BottomMenuData;

public interface HomeMainActivity {

    void setAdapterCount(int size);

    void setNavigationViewItems(List<BottomMenuData> data);
}
