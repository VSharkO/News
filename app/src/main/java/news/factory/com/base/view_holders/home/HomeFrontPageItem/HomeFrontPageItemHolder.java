package news.factory.com.base.view_holders.home.HomeFrontPageItem;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.recycler_adapter.RecyclerAdapterImpl;
import news.factory.com.base.view_holders.single.inner_cell_holder.InnerCellData;
import news.factory.com.model.single.TopNews;
import news.factory.com.utils.CostumeItemDecorator;

public class HomeFrontPageItemHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.homeCategoryTitle)
    TextView title;
    @BindView(R.id.homeItemsRecycler)
    RecyclerView recyclerView;
    RecyclerAdapterImpl adapter;


    private List<RecyclerWrapper> dataList;

    public HomeFrontPageItemHolder(View itemView, List<RecyclerWrapper> data) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.adapter = new RecyclerAdapterImpl(null);
        dataList = data;
        provideRecyclerViewAdapter();

    }

    public void onBind(int position) {
        HomeFrontPageItemData data = (HomeFrontPageItemData) dataList.get(position).getData();
        title.setText(data.getTitle());
        adapter.fillData(getSortedItemsForRecycler(data.getNews()));
    }

    public void provideRecyclerViewAdapter() {
        CostumeItemDecorator itemDecorator = new CostumeItemDecorator(itemView.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(itemView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(itemDecorator);
    }

    private List<RecyclerWrapper> getSortedItemsForRecycler(TopNews news) {

        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        addArticles(news, recyclerWrappers);
        return recyclerWrappers;
    }

    private void addArticles(TopNews news, List<RecyclerWrapper> recyclerWrappers) {
        for (int i = 0; i < 4; i++) {
            recyclerWrappers.add(new RecyclerWrapper(new InnerCellData(news.getArticles()[i]),
                    RecyclerWrapper.TYPE_INNER_ARTICLE_CELL));
        }

    }
}
