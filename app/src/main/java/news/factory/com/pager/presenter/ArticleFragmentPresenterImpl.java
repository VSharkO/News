package news.factory.com.pager.presenter;

import news.factory.com.pager.view.ArticleFragment;
import news.factory.com.pager.view.ArticleFragmentView;

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

}