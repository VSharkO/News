package news.factory.com.utils;

import android.support.v4.app.FragmentManager;

public class PresenterWithFragmentChildManager {
    Object presenter;
    FragmentManager manager;

    public PresenterWithFragmentChildManager(Object presenter, FragmentManager manager) {
        this.presenter = presenter;
        this.manager = manager;
    }

    public Object getPresenter() {
        return presenter;
    }

    public FragmentManager getManager() {
        return manager;
    }
}
