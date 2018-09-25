package news.factory.com.base.view_holders.single.article_title;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class ArticleTitleHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.titleText)
    public TextView text;

    private List<RecyclerWrapper> dataList;

    public ArticleTitleHolder(View itemView, List<RecyclerWrapper> data) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        dataList = data;
    }

    public void onBind(int position){
        if(position != RecyclerView.NO_POSITION){
            ArticleTitleData data = (ArticleTitleData) dataList.get(position).getData();
            text.setText(data.getTitle());
        }
    }
}
