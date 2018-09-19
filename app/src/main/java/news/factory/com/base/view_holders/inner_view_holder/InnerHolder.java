package news.factory.com.base.view_holders.inner_view_holder;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.adapters.pager_adapter.ViewPagerInnerAdapterImpl;

public class InnerHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.innerPager)
    ViewPager pager;

//    private List<RecyclerWrapper> dataList;
    ViewPagerInnerAdapterImpl adapter;
    public InnerHolder(View itemView, ViewPagerInnerAdapterImpl adapterImpl) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.adapter = adapterImpl;

//        this.dataList = dataList;
        pager.setAdapter(adapter);
        adapter.setDataCount(3);
    }

    public void onBind(int position){

    }

}
