package news.factory.com.base.view_holders.home.HomeFrontPageCellTitle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class HomeFrontPageTitleHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.homeCategoryTitle)
    TextView textView;

    private List<RecyclerWrapper> dataList;

    public HomeFrontPageTitleHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.dataList = dataList;
    }

    public void onBind(int position) {
        HomeFrontPageTitleData data = (HomeFrontPageTitleData) dataList.get(position).getData();
        textView.setText(data.getTitle());
    }
}
