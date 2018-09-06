package news.factory.com.base;

import news.factory.com.R;

public class RecyclerWrapper<Object> {

    public static final int TYPE_ARTICLE_TITLE = R.layout.cell_article_title;
    public static final int TYPE_ARTICLE_HEADER = R.layout.cell_article_header;
    public static final int TYPE_ARTICLE_IMAGE = R.layout.cell_article_image;
    public static final int TYPE_ARTICLE_TEXT = R.layout.cell_article_content_text;

    private Object data;
    private int type;

    public RecyclerWrapper(int type) {
        this.type = type;
    }

    public RecyclerWrapper(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public int getType() {
        return type;
    }
}
