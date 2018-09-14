package news.factory.com.utils;

import javax.inject.Inject;

import news.factory.com.App;

public class ResourseRepoImpl implements ResourceRepo {
    private App appContext;

    @Inject
    public ResourseRepoImpl(App app) {
        this.appContext = app;
    }

    @Override
    public String provideString(int stringID) {
        return appContext.getString(stringID);
    }

    @Override
    public String provideString(int stringID, String data) {
        return appContext.getString(stringID, data);
    }
}
