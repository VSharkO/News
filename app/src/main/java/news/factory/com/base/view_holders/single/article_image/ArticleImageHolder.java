package news.factory.com.base.view_holders.single.article_image;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import news.factory.com.R;
import news.factory.com.single.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.utils.Constants;

public class ArticleImageHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.imageView)
    ImageView image;
    private List<RecyclerWrapper> dataList;
    private Object presenter;

    public ArticleImageHolder(View itemView, List<RecyclerWrapper> data,Object presenterObject) {
        super(itemView);
        dataList = data;
        this.presenter = presenterObject;
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

    @OnClick
    public void onClick(View v) {
        if (presenter instanceof ArticleFragmentPresenter) {
            ArticleFragmentPresenter presenterTemp = (ArticleFragmentPresenter) presenter;
            presenterTemp.setData(presenterTemp.getIndex());
        }
    }
}
