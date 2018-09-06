package news.factory.com.base.view_holders.article_image;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWreper;
import news.factory.com.utils.Constants;

public class ArticleImageHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.imageView)
    private ImageView image;

    private List<RecyclerWreper> dataList;

    public ArticleImageHolder(View itemView, List<RecyclerWreper> data) {
        super(itemView);
        dataList.addAll(data);
        ButterKnife.bind(this,itemView);
    }

    public void onBind(int position){
        if(position != RecyclerView.NO_POSITION){
            ArticleImageData data = (ArticleImageData) dataList.get(position).getData();
            Glide.with(image.getContext())
                    .load(Constants.NEWS_PICTURE_BASE_URL + data.getImageLink())
                    .into(image);
        }
    }
}
