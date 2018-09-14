package news.factory.com.utils;

import android.content.Context;

public interface ResourceRepo {
    String provideString(int stringID);
    String provideString(int stringID, String data);
}
