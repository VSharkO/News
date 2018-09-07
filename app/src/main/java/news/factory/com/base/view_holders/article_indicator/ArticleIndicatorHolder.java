package news.factory.com.base.view_holders.article_indicator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class ArticleIndicatorHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.indicator)
    TextView indicator;

    private List<RecyclerWrapper> dataList;

    public ArticleIndicatorHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        this.dataList = dataList;
        ButterKnife.bind(this,itemView);

    }

    public void onBind(int position){
        ArticleIndicatorData data = (ArticleIndicatorData) dataList.get(position).getData();
        indicator.setText(data.getIndicatorString());
    }
}
