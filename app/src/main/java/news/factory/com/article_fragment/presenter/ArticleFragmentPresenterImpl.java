package news.factory.com.article_fragment.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import news.factory.com.utils.ResourceRepo;
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
import news.factory.com.networking.helpers.ArticleInteractor;
import news.factory.com.article_fragment.view.ArticleFragmentView;
import news.factory.com.utils.Constants;
import news.factory.com.utils.InteractorData;
import news.factory.com.utils.NetworkResponseListener;
import timber.log.Timber;

public class ArticleFragmentPresenterImpl implements ArticleFragmentPresenter, NetworkResponseListener{

    private ArticleFragmentView view;
    private ArticleInteractor articleInteractor;
    private ResourceRepo resourceRepo;
    private int index=0;

    @Inject
    public ArticleFragmentPresenterImpl(ArticleFragmentView view, ArticleInteractor interactor, ResourceRepo statics) {
        this.view = view;
        articleInteractor = interactor;
        resourceRepo = statics;
    }

    @Override
    public void setData(int index) {
        articleInteractor.getProductsFromAPI(this,Constants.TYPE,Constants.ID,String.valueOf(index));
        this.index = index;
    }

    @Override
    public void killDesposables() {
        articleInteractor.killDisposable();
    }

    @Override
    public void onSuccess(InteractorData callback) {
        News data = (News)callback.getData();
        view.fillAdapterDataNews(getSortedItemsForRecycler(data));
    }

    @Override
    public void onFailure(Throwable throwable) {
        Timber.e(throwable);
    }


    private List<RecyclerWrapper> getSortedItemsForRecycler(News news) {

        List<RecyclerWrapper> recyclerWrappers = new ArrayList<>();

        addHeader(news,recyclerWrappers);
        if(!news.getUpperTitle().isEmpty())
        addUpperTitle(news,recyclerWrappers);
        addAuthorShares(news,recyclerWrappers);
        addTitle(news,recyclerWrappers);
        addContents(news,recyclerWrappers);
        addPublished(news,recyclerWrappers);
        addIndicator(news,recyclerWrappers);
        return recyclerWrappers;
    }

    private void addHeader(News news, List<RecyclerWrapper> recyclerWrappers){

        String category = news.getCategory();
        String featuredImageSource;
        String featuredImageCaption;

        if(news.getFeaturedImageSource().isEmpty())
            featuredImageSource = "";
        else
            featuredImageSource = resourceRepo.provideString(R.string.source_string,news.getFeaturedImageSource());
        if(news.getFeaturedImageCaption().isEmpty())
            featuredImageCaption = "";
        else
            featuredImageCaption = news.getFeaturedImageCaption();
        if(news.getNoFeaturedImage().equals(Constants.FALSE))
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(news.getFeatured_image().getOriginal(),
                    category,featuredImageSource,featuredImageCaption),
                    RecyclerWrapper.TYPE_ARTICLE_HEADER));
        else{
            recyclerWrappers.add(new RecyclerWrapper(new ArticleHeaderData(category,featuredImageSource
                    ,featuredImageCaption),
                    RecyclerWrapper.TYPE_ARTICLE_HEADER));
        }
    }

    private void addUpperTitle(News news, List<RecyclerWrapper> recyclerWrappers){

        String upperTitle = news.getUpperTitle();
        recyclerWrappers.add(new RecyclerWrapper(new ArticleUpperTitleData(upperTitle),
                RecyclerWrapper.TYPE_ARTICLE_UPPER_TITLE));

    }

    private void addAuthorShares(News news, List<RecyclerWrapper> recyclerWrappers){
        //add author and shares
        recyclerWrappers.add(new RecyclerWrapper(new ArticleAuthorSharesData(news.getAuthor(),news.getShares()),
                RecyclerWrapper.TYPE_ARTICLE_AUTHOR_SHARES));

    }

    private void addTitle(News news, List<RecyclerWrapper> recyclerWrappers){
        String title = news.getTitle();
        recyclerWrappers.add(new RecyclerWrapper(new ArticleTitleData(title),RecyclerWrapper.TYPE_ARTICLE_TITLE));
    }

    private void addContents(News news, List<RecyclerWrapper> recyclerWrappers){

        for (Content content : news.getContent()) {
            if(content.getType().equals(Constants.IMAGE))
                recyclerWrappers.add(new RecyclerWrapper(new ArticleImageData(content.getImage().getOriginal()),
                        RecyclerWrapper.TYPE_ARTICLE_IMAGE));
            else{
                recyclerWrappers.add(new RecyclerWrapper(new ArticleContentData(content.getData()),
                        RecyclerWrapper.TYPE_ARTICLE_TEXT));
            }
        }
    }

    private void addPublished(News news, List<RecyclerWrapper> recyclerWrappers){
        String published = news.getPublishedAtHumans().split(" ")[0];
        recyclerWrappers.add(new RecyclerWrapper(new ArticlePublishedData(resourceRepo.provideString(R.string.published,published)),
                RecyclerWrapper.TYPE_ARTICLE_PUBLISHED));

    }

    private void addIndicator(News news, List<RecyclerWrapper> recyclerWrappers){
        String numOfPages = news.getPagesNo();
        recyclerWrappers.add(new RecyclerWrapper(new ArticleIndicatorData(numOfPages,String.valueOf(index)),
                RecyclerWrapper.TYPE_ARTICLE_INDICATOR));
    }
}