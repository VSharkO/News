package news.factory.com.utils;

import android.content.Context;

public class AppStaticsImpl implements AppStatics{
    Context appContext;

    public AppStaticsImpl(Context appContext) {
        this.appContext = appContext;
    }


    @Override
    public Context provideAppContext() {
        return appContext;
    }
}
