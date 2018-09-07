package news.factory.com.base.view_holders.article_header;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.App;
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
    @BindView(R.id.headerLayout)
    RelativeLayout headerLayout;

    private List<RecyclerWrapper> dataList;
    public ArticleHeaderHolder(View itemView, List<RecyclerWrapper> data) {
        super(itemView);
        dataList = data;
        ButterKnife.bind(this,itemView);
    }

    public void onBind(int position){
        if(position != RecyclerView.NO_POSITION){
            ArticleHeaderData data = (ArticleHeaderData) dataList.get(position).getData();
            if(!data.isThereImage()){
                //if there is no featured picture,
                //change text color and set top margin to layout with textViews

                source.setTextColor(App.getInstance().getResources().getColor(R.color.defaultTextColor));
                caption.setTextColor(App.getInstance().getResources().getColor(R.color.defaultTextColor));
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)headerLayout.getLayoutParams();
                params.setMargins(0, 62, 0, 0);
                headerLayout.requestLayout();

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


