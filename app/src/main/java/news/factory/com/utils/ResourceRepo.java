package news.factory.com.utils;

public interface ResourceRepo {
    String provideString(int stringID);
    String provideString(int stringID, String data);
}
