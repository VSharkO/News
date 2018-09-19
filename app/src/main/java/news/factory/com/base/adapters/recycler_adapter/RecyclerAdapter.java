package news.factory.com.base.adapters.recycler_adapter;

import java.util.List;

import news.factory.com.base.RecyclerWrapper;

public interface RecyclerAdapter {
    void fillData(List<RecyclerWrapper> data);
    int getItemCount();
}
