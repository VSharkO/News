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
import news.factory.com.utils.Constants;
import timber.log.Timber;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Content> mContents = new ArrayList<>();

    public void fillData(List<Content> contents){
        mContents.clear();
        mContents.addAll(contents);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == Constants.IMAGE_VIEW_TYPE){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image_layout,parent,false);
            return new ArticlesViewHolderImage(itemView);
        }else{
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_layout,parent,false);
            return new ArticlesViewHolderText(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mContents.get(position).getType().equals(Constants.IMAGE)){
            return Constants.IMAGE_VIEW_TYPE;
        }
        else{
            return Constants.TEXT_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Content content = mContents.get(position);
        if(holder.getItemViewType() == Constants.IMAGE_VIEW_TYPE){
            ArticlesViewHolderImage holderImage = (ArticlesViewHolderImage) holder;
            Glide.with(holderImage.itemView.getContext())
                .load(Constants.NEWS_PICTURE_BASE_URL + content.getImage().getOriginal())
                .into(holderImage.image);
        }else{
            ArticlesViewHolderText holderText = (ArticlesViewHolderText) holder;
            holderText.text.setText(Html.fromHtml(content.getData()));
        }
    }

    @Override
    public int getItemCount() {
        return mContents.size();
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
}