package news.factory.com.home.main.presenter;

import javax.inject.Inject;

import news.factory.com.base.adapters.pager_adapter.ViewPagerAdapter;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractor;
import news.factory.com.home.home_fragment_front_page.interactor.HomeFrontPageInteractorImpl;
import news.factory.com.home.main.view.HomeMainActivity;
public class HomeMainPresenterImpl implements HomeMainPresenter{

    private HomeMainActivity view;
    private HomeFrontPageInteractor articleInteractor;
    private ViewPagerAdapter adapter;

    @Inject
    public HomeMainPresenterImpl(HomeMainActivity view, HomeFrontPageInteractorImpl homeFrontPageInteractor, ViewPagerAdapter adapter) {
        this.view = view;
        this.articleInteractor = homeFrontPageInteractor;
        this.adapter = adapter;
    }

}
