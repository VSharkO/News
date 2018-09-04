package news.factory.com.pager.view;

import java.util.List;

import news.factory.com.model.Content;

public interface ArticleFragmentView {

    void fillAdapterData(List<Content> content);
}
