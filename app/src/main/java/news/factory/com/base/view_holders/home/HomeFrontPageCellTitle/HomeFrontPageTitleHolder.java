package news.factory.com.base.view_holders.home.HomeFrontPageCellTitle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class HomeFrontPageTitleHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.titleText)
    TextView textView;


    List<RecyclerWrapper> dataList = new ArrayList<>();

    public HomeFrontPageTitleHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        this.dataList = dataList;
    }

    public void onBind(int position) {
        HomeFrontPageTitleData data = (HomeFrontPageTitleData) dataList.get(position).getData();
        textView.setText(data.getTitle());
        //TODO crta boja
    }
}
