package news.factory.com.base.view_holders.article_user_shares;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.App;
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

    public void onBind(int positin){
        ArticleAuthorSharesData data = (ArticleAuthorSharesData) dataList.get(positin).getData();
        author.setText(App.getInstance().getString(R.string.single_author,data.getAuthor()));
        shares.setText(data.getShares());
    }
}
