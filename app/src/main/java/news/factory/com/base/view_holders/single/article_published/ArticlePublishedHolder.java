package news.factory.com.base.view_holders.single.article_published;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class ArticlePublishedHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.publishedText)
    TextView published;

    private List<RecyclerWrapper> dataList;

    public ArticlePublishedHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.dataList = dataList;
    }

    public void onBind(int position){
        ArticlePublishedData data = (ArticlePublishedData)dataList.get(position).getData();
        published.setText(data.getPublished());
    }
}
