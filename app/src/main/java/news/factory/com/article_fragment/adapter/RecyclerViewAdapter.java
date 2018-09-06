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
import news.factory.com.model.FeaturedImage;
import news.factory.com.model.News;
import news.factory.com.utils.Constants;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Object> items = new ArrayList<>();

    public void fillData(News oneNews){
        items.clear();

        if(oneNews.getNo_featured_image().equals(Constants.FALSE)) {
            items.add(oneNews.getFeatured_image());
        }
        items.add(oneNews.getTitle());
        items.addAll(oneNews.getContent());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        switch (viewType){
            case Constants.IMAGE_VIEW_TYPE:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.image_layout,parent,false);
                return new ArticlesViewHolderImage(itemView);

            case Constants.TEXT_VIEW_TYPE:
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.text_layout,parent,false);
                return new ArticlesViewHolderText(itemView);

            case Constants.TITLE_VIEW_TYPE:
                itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_title_layout,parent,false);
                return new ArticlesViewHolderTitle(itemView);

            case Constants.FEATURED_IMAGE_VIEW_TYPE:
                itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_featured_image, parent, false);
                return new ArticlesViewHolderFeaturedImages(itemView);

            default: return null;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).getClass() == FeaturedImage.class){
            return Constants.FEATURED_IMAGE_VIEW_TYPE;
        }
        else if(items.get(position).getClass() == String.class){
            return Constants.TITLE_VIEW_TYPE;
        }
        else{
            Content content = (Content)items.get(position);
            if (content.getType().equals(Constants.IMAGE)) {
                return Constants.IMAGE_VIEW_TYPE;
            }
            else {
                return Constants.TEXT_VIEW_TYPE;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Content content;

        switch (holder.getItemViewType()){
            case Constants.IMAGE_VIEW_TYPE:
                content = (Content) items.get(position);
                ArticlesViewHolderImage holderImage = (ArticlesViewHolderImage) holder;
                Glide.with(holderImage.itemView.getContext())
                    .load(Constants.NEWS_PICTURE_BASE_URL + content.getImage().getOriginal())
                    .into(holderImage.image);
                break;

            case Constants.TEXT_VIEW_TYPE:
                content = (Content) items.get(position);
                ArticlesViewHolderText holderText = (ArticlesViewHolderText) holder;
                holderText.text.setText(Html.fromHtml(content.getData()));
                break;

            case Constants.TITLE_VIEW_TYPE:
                ArticlesViewHolderTitle holderTitle = (ArticlesViewHolderTitle) holder;
                holderTitle.text.setText(items.get(position).toString());
                break;

            case  Constants.FEATURED_IMAGE_VIEW_TYPE:
                FeaturedImage image = (FeaturedImage) items.get(position);
                ArticlesViewHolderFeaturedImages holderFeaturedImages = (ArticlesViewHolderFeaturedImages) holder;
                Glide.with(holderFeaturedImages.itemView.getContext())
                        .load(Constants.NEWS_PICTURE_BASE_URL + image.getOriginal())
                        .into(holderFeaturedImages.image);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
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

    class ArticlesViewHolderTitle extends RecyclerView.ViewHolder {

        TextView text;

        ArticlesViewHolderTitle(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.titleText);
        }
    }

    class ArticlesViewHolderFeaturedImages extends RecyclerView.ViewHolder {

        ImageView image;

        ArticlesViewHolderFeaturedImages(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.featuredImage);
        }
    }
}