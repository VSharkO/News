package news.factory.com.single.inner_pager_fragment.presenter;

import news.factory.com.model.single.TopNews;

public interface InnerPagerFragmentPresenter {
    void setData(int index);

    TopNews getData();
}
