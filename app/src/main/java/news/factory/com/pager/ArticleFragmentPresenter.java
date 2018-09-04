package news.factory.com.pager;

interface ArticleFragmentPresenter {
    void setView(ArticleFragmentView view);

    void setData(int index);

    void getArticles();
}
