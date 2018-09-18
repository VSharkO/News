package news.factory.com.base.view_holders.inner_view_holder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.ViewPagerFragmentAdapter;
import news.factory.com.base.adapters.ViewPagerFragmentAdapterImpl;

public class InnerHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.innerPager)
    ViewPager viewPager;
    @Inject
    ViewPagerFragmentAdapterImpl adapter;

    private List<RecyclerWrapper> dataList;

    public InnerHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        viewPager.setAdapter(adapter);
        adapter.setDataCount(3);
        this.dataList = dataList;
    }

    public void onBind(int position){
//        InnerHolderData data =(InnerHolderData) dataList.get(position).getData();
    }

}
