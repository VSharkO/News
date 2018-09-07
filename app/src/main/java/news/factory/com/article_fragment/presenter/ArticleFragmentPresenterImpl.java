package news.factory.com.article_fragment.presenter;

import java.util.ArrayList;
import java.util.List;

import news.factory.com.App;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.base.view_holders.article_content.ArticleContentData;
import news.factory.com.base.view_holders.article_header.ArticleHeaderData;
import news.factory.com.base.view_holders.article_image.ArticleImageData;
import news.factory.com.base.view_holders.article_indicator.ArticleIndicatorData;
import news.factory.com.base.view_holders.article_published.ArticlePublishedData;
import news.factory.com.base.view_holders.article_title.ArticleTitleData;
import news.factory.com.base.view_holders.article_upper_header.ArticleUpperTitleData;
import news.factory.com.base.view_holders.article_author_shares.ArticleAuthorSharesData;
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
    private int index=0;

    public ArticleFragmentPresenterImpl(ArticleFragment view) {
        this.view = view;
        mNetworkingHelper = new NetworkingHelperImpl(App.getInstance().getService());
    }

    @Override
    public void setData(int index) {
        mNetworkingHelper.getProductsFromAPI(this,Constants.TYPE,Constants.ID,String.valueOf(index));
        this.index = index;
    }

    @Override
    public void onSuccess(News news) {
        view.fillAdapterDataNews(getSortedItems(news,index));
        view.setSwipeButtons(index,Integer.parseInt(news.getPages_no()));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }


    private List<RecyclerWrapper> getSortedItems(News news,int index) {

        String category = news.getCategory();
        String featuredImageSource = App.getInstance().getString(R.string.source_string,news.getFeaturedImageSource());
        String featuredImageCaption = news.getFeatured_image_caption();
        String title = news.getTitle();
        String upperTitle = news.getUppertitle();
        String published = news.getPublished_at_humans();
        String numOfPages = news.getPages_no();

        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        //add header
        if(news.getNo_featured_image().equals(Constants.FALSE))
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(news.getFeatured_image().getOriginal(),
                    category,featuredImageSource,featuredImageCaption),
                    RecyclerWrapper.TYPE_ARTICLE_HEADER));
        else{
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(category,featuredImageSource
                    ,featuredImageCaption),
                    RecyclerWrapper.TYPE_ARTICLE_HEADER));
        }

        //add upperTitle
        recyclerWrappers.add(new RecyclerWrapper(new ArticleUpperTitleData(upperTitle),
                RecyclerWrapper.TYPE_ARTICLE_UPPER_TITLE));

        //add Author and shares
        recyclerWrappers.add(new RecyclerWrapper(new ArticleAuthorSharesData(news.getAuthor(),news.getShares()),
                        RecyclerWrapper.TYPE_ARTICLE_AUTHOR_SHARES));

        //add Title
        recyclerWrappers.add(new RecyclerWrapper(new ArticleTitleData(title),RecyclerWrapper.TYPE_ARTICLE_TITLE));

        //add contents
        for (Content content : news.getContent()) {
            if(content.getType().equals(Constants.IMAGE))
                recyclerWrappers.add(new RecyclerWrapper(new ArticleImageData(content.getImage().getOriginal()),
                        RecyclerWrapper.TYPE_ARTICLE_IMAGE));
            else{
                recyclerWrappers.add(new RecyclerWrapper(new ArticleContentData(content.getData()),
                        RecyclerWrapper.TYPE_ARTICLE_TEXT));
            }
        }

        //add published
        recyclerWrappers.add(new RecyclerWrapper(new ArticlePublishedData(published),
                RecyclerWrapper.TYPE_ARTICLE_PUBLISHED));

        //add indicator
        recyclerWrappers.add(new RecyclerWrapper(new ArticleIndicatorData(numOfPages,String.valueOf(index)),
                RecyclerWrapper.TYPE_ARTICLE_INDICATOR));

        return recyclerWrappers;
    }

}