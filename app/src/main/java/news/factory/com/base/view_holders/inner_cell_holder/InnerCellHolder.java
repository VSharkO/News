package news.factory.com.base.view_holders.inner_cell_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.model.Articles;
import news.factory.com.model.TopNews;
import news.factory.com.utils.Constants;

public class InnerCellHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.innerPagerFragmentTitle)
    TextView title;
    @BindView(R.id.innerPagerFragmentpublished)
    TextView published;
    @BindView(R.id.innerPagerFragmentshares)
    TextView shares;
    @BindView(R.id.innerPagerFragmentImage)
    ImageView image;

    private List<RecyclerWrapper> dataList;

    public InnerCellHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.dataList = dataList;
    }

    public void onBind(int position){
        InnerCellData data = (InnerCellData) dataList.get(position).getData();
        Articles articles = data.getArticles();

        title.setText(articles.getTitle());
        published.setText(articles.getPublishedAtHumans()); //logika za ovo
        shares.setText(articles.getShares());
        Glide.with(image.getContext())
                .load(Constants.NEWS_PICTURE_BASE_URL + articles.getFeaturedImage().getL())
                .into(image);
    }
}
