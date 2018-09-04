package news.factory.com.pager.presenter;

import news.factory.com.pager.view.ArticleFragmentView;

public interface ArticleFragmentPresenter {
    void setView(ArticleFragmentView view);

    void setData(int index);
}
