package news.factory.com.article_fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import news.factory.com.R;
import news.factory.com.model.Content;
import news.factory.com.model.News;
import news.factory.com.utils.Constants;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private News news;
    private List<Object> elements = new ArrayList<>();

    public void fillData(News news){
        this.news = news;
        elements.clear();
        elements.add(news);
        elements.addAll(news.getContent());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constants.IMAGE_VIEW_TYPE){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_layout,parent,false);
            return new ArticlesViewHolderImage(itemView);
        }else if(viewType == Constants.TEXT_VIEW_TYPE){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_layout,parent,false);
            return new ArticlesViewHolderText(itemView);
        }else{
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_item_header_layout,parent,false);
            return new ArticlesViewHolderHeader(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(elements.get(position).getClass() == News.class){
            return Constants.NEWS_VIEW_TYPE;
        }else {
            Content content = (Content)elements.get(position);
            if (content.getType().equals(Constants.IMAGE)) {
                return Constants.IMAGE_VIEW_TYPE;
            } else {
                return Constants.TEXT_VIEW_TYPE;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType() == Constants.IMAGE_VIEW_TYPE){
            Content content = (Content)elements.get(position);
            ArticlesViewHolderImage holderImage = (ArticlesViewHolderImage) holder;
            Glide.with(holderImage.itemView.getContext())
                .load(Constants.NEWS_PICTURE_BASE_URL + content.getImage().getOriginal())
                .into(holderImage.image);
        }else if(holder.getItemViewType() == Constants.TEXT_VIEW_TYPE){
            Content content = (Content)elements.get(position);
            ArticlesViewHolderText holderText = (ArticlesViewHolderText) holder;
            holderText.text.setText(Html.fromHtml(content.getData()));
        }
        else{
            ArticlesViewHolderHeader holderHeader = (ArticlesViewHolderHeader) holder;
            if(news.getNo_featured_image().equals(Constants.FALSE)){
                Glide.with(holderHeader.itemView.getContext())
                        .load(Constants.NEWS_PICTURE_BASE_URL + news.getFeatured_image().getOriginal())
                        .into(holderHeader.image);
            }
            holderHeader.text.setText(news.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    class ArticlesViewHolderImage extends RecyclerView.ViewHolder {

        ImageView image;

        ArticlesViewHolderImage(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
        }
    }

    class ArticlesViewHolderText extends RecyclerView.ViewHolder {

        TextView text;

        ArticlesViewHolderText(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);
        }
    }

    class ArticlesViewHolderHeader extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;

        ArticlesViewHolderHeader(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.titleText);
            image = itemView.findViewById(R.id.featuredImage);
        }
    }
}