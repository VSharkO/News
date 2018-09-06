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
import news.factory.com.model.HeaderForSingle;
import news.factory.com.model.News;
import news.factory.com.utils.Constants;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<RecyclerWrapper> dataList = new ArrayList<>();

    public void fillData(List<RecyclerWrapper<Object>> data){
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

//    private void addHeader(News news) {
//        HeaderForSingle header;
//        if(news.getNo_featured_image().equals(Constants.FALSE))
//            header = new HeaderForSingle(news.getFeatured_image().getOriginal()
//                ,news.getFeaturedImageSource(),news.getCategory(),news.getFeatured_image_caption());
//        else{
//            header = new HeaderForSingle(news.getFeaturedImageSource(),news.getCategory(),news.getFeatured_image_caption());
//        }
//        items.add(header);
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        switch (viewType){
            case Constants.IMAGE_VIEW_TYPE:
                return new ArticleImageHolder(itemView,dataList);

            case Constants.TEXT_VIEW_TYPE:
                return new ArticleContentHolder(itemView,dataList);

            case Constants.TITLE_VIEW_TYPE:
                return new ArticleTitleHolder(itemView,dataList);

            case Constants.HEADER_VIEW_TYPE:
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
            case Constants.IMAGE_VIEW_TYPE:
                ArticleImageHolder holderImage = (ArticleImageHolder) holder;
                holderImage.onBind(position);
                break;

            case Constants.TEXT_VIEW_TYPE:
                ArticleContentHolder holderText = (ArticleContentHolder) holder;
                holderText.onBind(position);
                break;

            case Constants.TITLE_VIEW_TYPE:
                ArticleTitleHolder holderTitle = (ArticleTitleHolder) holder;
                holderTitle.onBind(position);
                break;

            case  Constants.HEADER_VIEW_TYPE:
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