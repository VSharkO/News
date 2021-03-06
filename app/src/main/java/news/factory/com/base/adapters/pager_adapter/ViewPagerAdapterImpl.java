package news.factory.com.base.adapters.pager_adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import javax.inject.Inject;
import news.factory.com.home.home_fragment_front_page.view.HomeFragmentFrontPageImpl;
import news.factory.com.home.home_pages_holder.view.HomePagesFragmentImpl;
import news.factory.com.home.home_item_fragment.view.HomeFragmentItemImpl;
import news.factory.com.home.main.presenter.HomeMainPresenter;
import news.factory.com.single.article_fragment.view.ArticleFragmentImpl;
import news.factory.com.single.inner_pager_fragment.view.InnerPagerFragment;
import news.factory.com.utils.Constants;

public class ViewPagerAdapterImpl extends FragmentStatePagerAdapter implements ViewPagerAdapter {
    private int countNumber;
    private String type;
    private Object presenter;
    private int parentIndex;
    private String itemId;

    @Inject
    public ViewPagerAdapterImpl(FragmentManager fm, String type, Object presenter) {
        super(fm);
        this.type = type;
        this.presenter = presenter;
    }

    @Override
    public Fragment getItem(int position) {
        if(type.equals(Constants.SINGLE_INNER_HOLDER_TYPE))
            return InnerPagerFragment.newInstance(position);

        else if(type.equals(Constants.SINGLE_TYPE))
            return ArticleFragmentImpl.newInstance(position,itemId);

        else if(type.equals(Constants.HOME_TYPE_FRONT_PAGE))
            if(position == 0)
                return HomeFragmentFrontPageImpl.newInstance();
            else {
                return HomePagesFragmentImpl.newInstance(position);
            }
        else if(type.equals(Constants.TYPE_HOME_ITEMS)){
             return HomeFragmentItemImpl.newInstance(position,parentIndex);
        }
        else
            return null;
    }

    @Override
    public int getCount() {
        return countNumber;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void setDataCount(int numOfPages) {
        countNumber = numOfPages;
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(type.equals(Constants.SINGLE_INNER_HOLDER_TYPE)){
            switch (position){
                case 0:
                    return Constants.MOST_POPULAR_TYPE.substring(0,1).toUpperCase()
                            + Constants.MOST_POPULAR_TYPE.substring(1);
                case 1:
                    return Constants.MOST_READ_TYPE.substring(0,1).toUpperCase()
                            + Constants.MOST_READ_TYPE.substring(1);
                case 2:
                    return Constants.MOST_NEW_TYPE.substring(0,1).toUpperCase()
                            + Constants.MOST_NEW_TYPE.substring(1);
                default: return "";
            }
        }
        else if(type.equals(Constants.HOME_TYPE_FRONT_PAGE)){
            HomeMainPresenter presenter = (HomeMainPresenter)this.presenter;
            return presenter.getBottomMenuData().get(position).getTitle();
        }
        else if(type.equals(Constants.TYPE_HOME_ITEMS)){

            switch (position){
                case 0:
                    return Constants.MOST_NEW_TYPE.substring(0,1).toUpperCase()
                            + Constants.MOST_NEW_TYPE.substring(1);
                case 1:
                    return Constants.MOST_READ_TYPE.substring(0,1).toUpperCase()
                            + Constants.MOST_READ_TYPE.substring(1);
                default: return "";
            }
        }
        else
            return "";
    }

    @Override
    public void setParentIndex(int parentIndex){
        this.parentIndex = parentIndex;
    }

    public void setItemId(String id) {
        itemId = id;
    }
}
