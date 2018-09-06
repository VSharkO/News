package news.factory.com.base.view_holders.article_title;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWreper;

public class ArticleTitleHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.titleText)
    private TextView text;
    private List<RecyclerWreper> dataList;

    public ArticleTitleHolder(View itemView, List<RecyclerWreper> data) {
        super(itemView);
        dataList = data;
        ButterKnife.bind(this,itemView);
    }

    public void onBind(int position){
        if(position != RecyclerView.NO_POSITION){
            ArticleTitleData data = (ArticleTitleData) dataList.get(position).getData();
            text.setText(data.getTitle());
        }
    }
}
