package news.factory.com.base;

import java.util.List;

public interface RecyclerAdapter {
    void fillData(List<RecyclerWrapper> data);
    int getItemCount();
}
