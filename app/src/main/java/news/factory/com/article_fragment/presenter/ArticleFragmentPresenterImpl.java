package news.factory.com.article_fragment.presenter;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.App;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.view_holders.article_content.ArticleContentData;
import news.factory.com.base.view_holders.article_content.ArticleContentHolder;
import news.factory.com.base.view_holders.article_header.ArticleHeaderData;
import news.factory.com.base.view_holders.article_image.ArticleImageData;
import news.factory.com.base.view_holders.article_title.ArticleTitleData;
import news.factory.com.model.Content;
import news.factory.com.model.News;
import news.factory.com.networking.helpers.NetworkingHelper;
import news.factory.com.article_fragment.view.ArticleFragment;
import news.factory.com.article_fragment.view.ArticleFragmentView;
import news.factory.com.networking.helpers.NetworkingHelperImpl;
import news.factory.com.utils.Constants;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class ArticleFragmentPresenterImpl implements ArticleFragmentPresenter, NetworkResponseListener<News>{

    private ArticleFragmentView view;
    private NetworkingHelper mNetworkingHelper;

    public ArticleFragmentPresenterImpl(ArticleFragment view) {
        this.view = view;
        mNetworkingHelper = new NetworkingHelperImpl(App.getInstance().getService());
    }

    @Override
    public void setData(int index) {
        mNetworkingHelper.getProductsFromAPI(this,Constants.TYPE,Constants.ID,String.valueOf(index));
    }

    @Override
    public void onSuccess(News news) {
        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        //add header
        if(news.getNo_featured_image().equals(Constants.FALSE))
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(news.getFeatured_image().getOriginal(),
                    news.getCategory(),news.getFeaturedImageSource(),news.getFeatured_image_caption()),RecyclerWrapper.TYPE_ARTICLE_HEADER));
        else{
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(news.getCategory(),
                    news.getFeaturedImageSource(),news.getFeatured_image_caption()),RecyclerWrapper.TYPE_ARTICLE_HEADER));
        }
        //add Title
        recyclerWrappers.add(new RecyclerWrapper(new ArticleTitleData(news.getTitle()),RecyclerWrapper.TYPE_ARTICLE_TITLE));
        //add contents
        for (Content content : news.getContent()) {
            if(content.getType().equals(Constants.IMAGE))
                recyclerWrappers.add(new RecyclerWrapper(new ArticleImageData(content.getImage().getOriginal()),RecyclerWrapper.TYPE_ARTICLE_IMAGE));
            else{
                recyclerWrappers.add(new RecyclerWrapper(new ArticleContentData(content.getData()),RecyclerWrapper.TYPE_ARTICLE_TEXT));
            }
        }

        view.fillAdapterDataNews(recyclerWrappers);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }


    private List<RecyclerWrapper> getSortedItems(News news) {

        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        if(news.getNo_featured_image().equals(Constants.FALSE))
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(news.getFeatured_image().getOriginal(),
                    news.getCategory(),news.getFeaturedImageSource(),news.getFeatured_image_caption()),RecyclerWrapper.TYPE_ARTICLE_HEADER));
        else{
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(news.getCategory(),
                    news.getFeaturedImageSource(),news.getFeatured_image_caption()),RecyclerWrapper.TYPE_ARTICLE_HEADER));
        }
        recyclerWrappers.add(new RecyclerWrapper(new ArticleContentData(news.getTitle()),RecyclerWrapper.TYPE_ARTICLE_TITLE));
        for (Content content : news.getContent()) {
            if(content.getType().equals(Constants.IMAGE))
                recyclerWrappers.add(new RecyclerWrapper(new ArticleImageData(content.getImage().getOriginal()),RecyclerWrapper.TYPE_ARTICLE_IMAGE));
            else{
                recyclerWrappers.add(new RecyclerWrapper(new ArticleContentData(content.getData()),RecyclerWrapper.TYPE_ARTICLE_TEXT));
            }
        }
        return recyclerWrappers;
    }
}