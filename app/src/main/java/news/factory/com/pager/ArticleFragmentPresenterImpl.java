package news.factory.com.pager;


import java.util.List;

import news.factory.com.model.News;

public class ArticleFragmentPresenterImpl implements ArticleFragmentPresenter {

    private ArticleFragmentView view;
    private int mIndex;

    public ArticleFragmentPresenterImpl(ArticleFragment view) {
        setView(view);
    }

    @Override
    public void setView(ArticleFragmentView view) {
        this.view = view;
    }

    @Override
    public void setData(int index) {
        mIndex = index;
    }

    public void setView(News news){

    }

}