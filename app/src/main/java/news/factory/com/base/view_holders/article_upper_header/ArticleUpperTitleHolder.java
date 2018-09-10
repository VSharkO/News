package news.factory.com.base.view_holders.article_upper_header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class ArticleUpperTitleHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.upperTitle)
    TextView upperTitle;

    private List<RecyclerWrapper> dataList;


    public ArticleUpperTitleHolder(View itemView, List<RecyclerWrapper> data) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        dataList = data;
    }

    public void onBind(int position){
        ArticleUpperTitleData data =(ArticleUpperTitleData) dataList.get(position).getData();
        upperTitle.setText(data.getUpperTitle());
    }
}
