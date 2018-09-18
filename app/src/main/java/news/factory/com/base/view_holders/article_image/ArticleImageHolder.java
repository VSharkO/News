package news.factory.com.base.view_holders.article_image;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import news.factory.com.R;
import news.factory.com.article_fragment.presenter.ArticleFragmentPresenter;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.utils.Constants;
import timber.log.Timber;

public class ArticleImageHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.imageView)
    ImageView image;
    private List<RecyclerWrapper> dataList;
    private ArticleFragmentPresenter presenter;


    public ArticleImageHolder(View itemView, List<RecyclerWrapper> data,ArticleFragmentPresenter presenter) {
        super(itemView);
        dataList = data;
        this.presenter = presenter;
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
        Timber.e("RADI!");
        presenter.setData(presenter.getIndex());
    }
}
