package news.factory.com.base.view_holders.inner_view_holder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.adapters.ViewPagerInnerAdapter;
import news.factory.com.base.adapters.ViewPagerInnerAdapterImpl;

public class InnerHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.innerPager)
    ViewPager pager;

//    private List<RecyclerWrapper> dataList;

    public InnerHolder(View itemView, ViewPagerInnerAdapterImpl adapter) {
        super(itemView);
        ButterKnife.bind(this,itemView);
//        this.dataList = dataList;
//        ViewPagerInnerAdapterImpl adapter = new ViewPagerInnerAdapterImpl(context.getSupportFragmentManager());
        pager.setAdapter(adapter);
        adapter.setDataCount(3);
    }

    public void onBind(int position){

//        InnerHolderData data =(InnerHolderData) dataList.get(position).getData();
    }

}