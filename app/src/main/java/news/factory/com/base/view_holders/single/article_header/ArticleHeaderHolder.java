package news.factory.com.base.view_holders.single.article_header;

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
import news.factory.com.utils.Constants;

public class ArticleHeaderHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.featuredImage)
    ImageView image;
    @BindView(R.id.headerCategoryText)
    TextView category;
    @BindView(R.id.source)
    TextView source;
    @BindView(R.id.caption)
    TextView caption;

    private List<RecyclerWrapper> dataList;
    public ArticleHeaderHolder(View itemView, List<RecyclerWrapper> data) {
        super(itemView);
        dataList = data;
        ButterKnife.bind(this,itemView);
    }

    public void onBind(int position){
        if(position != RecyclerView.NO_POSITION){
            ArticleHeaderData data = (ArticleHeaderData) dataList.get(position).getData();

            if(data.getCategory().isEmpty()){
                category.setVisibility(View.GONE);
            }
            if(data.getSource().isEmpty()){
                source.setVisibility(View.GONE);
            }
            if(data.getCaption().isEmpty()){
                caption.setVisibility(View.GONE);
            }

            if(!data.isThereImage()){
//                if there is no featured picture
//                source.setTextColor(App.getInstance().getResources().getColor(R.color.defaultTextColor));
//                caption.setTextColor(App.getInstance().getResources().getColor(R.color.defaultTextColor));
            }else{
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


