package news.factory.com.base.view_holders.inner_cell_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.model.TopNews;

public class InnerCellHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.innerPagerFragmentTitle)
    TextView title;

    private List<RecyclerWrapper> dataList;

    public InnerCellHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.dataList = dataList;
    }

    public void onBind(int position){
        InnerCellData data = (InnerCellData) dataList.get(position).getData();
        title.setText(data.getName());
    }
}
