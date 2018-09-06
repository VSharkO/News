package news.factory.com.base.view_holders.article_content;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class ArticleContentHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textView)
    TextView text;

    private List<RecyclerWrapper> dataList;

    public ArticleContentHolder(View itemView, List<RecyclerWrapper> data) {
        super(itemView);
        dataList = data;
        ButterKnife.bind(this,itemView);
    }

    public void onBind(int position){
        if(position != RecyclerView.NO_POSITION){
            ArticleContentData data = (ArticleContentData) dataList.get(position).getData();
            text.setText(data.getText());
        }
    }
}

