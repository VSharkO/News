package news.factory.com.article_fragment.view;

import java.util.List;

import news.factory.com.base.RecyclerWrapper;

public interface ArticleFragmentView {
    void fillAdapterDataNews(List<RecyclerWrapper> list);

    void setSwipeButtons(int index, int numOfItems);
}
