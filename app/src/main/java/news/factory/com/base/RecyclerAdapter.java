package news.factory.com.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.base.view_holders.article_content.ArticleContentHolder;
import news.factory.com.base.view_holders.article_header.ArticleHeaderHolder;
import news.factory.com.base.view_holders.article_image.ArticleImageHolder;
import news.factory.com.base.view_holders.article_title.ArticleTitleHolder;
import news.factory.com.utils.Constants;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<RecyclerWrapper> dataList = new ArrayList<>();

    public void fillData(List<RecyclerWrapper> data){
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        switch (viewType){
            case RecyclerWrapper.TYPE_ARTICLE_IMAGE:
                return new ArticleImageHolder(itemView,dataList);

            case RecyclerWrapper.TYPE_ARTICLE_TEXT:
                return new ArticleContentHolder(itemView,dataList);

            case RecyclerWrapper.TYPE_ARTICLE_TITLE:
                return new ArticleTitleHolder(itemView,dataList);

            case RecyclerWrapper.TYPE_ARTICLE_HEADER:
                return new ArticleHeaderHolder(itemView,dataList);

            default: return new DummyHolder(new View(parent.getContext()));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case RecyclerWrapper.TYPE_ARTICLE_IMAGE:
                ArticleImageHolder holderImage = (ArticleImageHolder) holder;
                holderImage.onBind(position);
                break;

            case RecyclerWrapper.TYPE_ARTICLE_TEXT:
                ArticleContentHolder holderText = (ArticleContentHolder) holder;
                holderText.onBind(position);
                break;

            case RecyclerWrapper.TYPE_ARTICLE_TITLE:
                ArticleTitleHolder holderTitle = (ArticleTitleHolder) holder;
                holderTitle.onBind(position);
                break;

            case  RecyclerWrapper.TYPE_ARTICLE_HEADER:
                ArticleHeaderHolder holderHeader = (ArticleHeaderHolder) holder;
                holderHeader.onBind(position);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class DummyHolder extends RecyclerView.ViewHolder {
        public DummyHolder(View itemView) {
            super(itemView);
        }
    }
}