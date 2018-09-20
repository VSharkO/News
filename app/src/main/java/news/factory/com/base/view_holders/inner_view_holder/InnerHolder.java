package news.factory.com.base.view_holders.inner_view_holder;
import android.support.v4.app.FragmentManager;
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

    ViewPagerInnerAdapterImpl adapter;

    public InnerHolder(View itemView, FragmentManager fragmentManager) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        adapter = new ViewPagerInnerAdapterImpl(fragmentManager);
        pager.setAdapter(adapter);
        adapter.setDataCount(3);
    }

    public void onBind(int position){

    }

}
