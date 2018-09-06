package news.factory.com.base.view_holders.article_header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWreper;
import news.factory.com.utils.Constants;

public class ArticleHeaderHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.featuredImage)
    private ImageView image;
    @BindView(R.id.headerCategoryText)
    private TextView category;
    @BindView(R.id.source)
    private TextView source;
    @BindView(R.id.caption)
    private TextView caption;

    List<RecyclerWreper> dataList;
    public ArticleHeaderHolder(View itemView, List<RecyclerWreper> data) {
        super(itemView);
        dataList = data;
        ButterKnife.bind(this,itemView);
    }

    public void onBind(int position){
        if(position != RecyclerView.NO_POSITION){
            ArticleHeaderData data =(ArticleHeaderData) dataList.get(position).getData();
            if(image != null){
                Glide.with(image.getContext())
                        .load(Constants.NEWS_PICTURE_BASE_URL + data.getImage())
                        .into(image);
            }
            category.setText(data.getCategory());
            source.setText(data.getSource());
            caption.setText(data.getCaption());
        }
    }
}


