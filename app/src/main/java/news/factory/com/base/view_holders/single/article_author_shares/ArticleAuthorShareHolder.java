package news.factory.com.base.view_holders.single.article_author_shares;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;

public class ArticleAuthorShareHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.authorText)
    TextView author;
    @BindView(R.id.sharesText)
    TextView shares;

    private List<RecyclerWrapper> dataList;

    public ArticleAuthorShareHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.dataList = dataList;
    }

    public void onBind(int position){
        ArticleAuthorSharesData data = (ArticleAuthorSharesData) dataList.get(position).getData();
        author.setText(data.getAuthor());
        shares.setText(data.getShares());
    }
}
