package news.factory.com.base.view_holders.inner_view_holder;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.ViewPagerInnerAdapterImpl;

public class InnerHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.innerPager)
    ViewPager pager;

    private List<RecyclerWrapper> dataList;
    private ViewPagerInnerAdapterImpl adapter;

    public InnerHolder(View itemView, List<RecyclerWrapper> dataList, ViewPagerInnerAdapterImpl innerAdapter) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.dataList = dataList;
        this.adapter = innerAdapter;
        pager.setAdapter(adapter);
        adapter.setDataCount(3);

    }

    public void onBind(int position){

//        InnerHolderData data =(InnerHolderData) dataList.get(position).getData();
    }

}
