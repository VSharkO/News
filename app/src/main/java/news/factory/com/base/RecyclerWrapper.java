package news.factory.com.base;

import news.factory.com.R;

public class RecyclerWrapper{

    public static final int TYPE_ARTICLE_TITLE = R.layout.cell_article_title;
    public static final int TYPE_ARTICLE_HEADER = R.layout.cell_article_header;
    public static final int TYPE_ARTICLE_IMAGE = R.layout.cell_article_image;
    public static final int TYPE_ARTICLE_TEXT = R.layout.cell_article_content;
    public static final int TYPE_ARTICLE_UPPER_TITLE = R.layout.cell_article_upper_title;
    public static final int TYPE_ARTICLE_AUTHOR_SHARES= R.layout.cell_article_author_shares;
    public static final int TYPE_ARTICLE_PUBLISHED = R.layout.cell_article_published;
    public static final int TYPE_ARTICLE_INDICATOR = R.layout.cell_article_indicator;
    public static final int TYPE_INNER_ARTICLE_PAGER = R.layout.cell_inner_pager;
    public static final int TYPE_INNER_ARTICLE_CELL = R.layout.inner_cell_layout;
    public static final int TYPE_HOME_FRONT_PAGE = R.layout.home_pager;
    public static final int TYPE_HOME_FRONT_ITEM = R.layout.cell_home_item;
    public static final int TYPE_HOME_FRONT_TITLE = R.layout.home_category_title;
    public static final int TYPE_HOME_BUTTON = R.layout.home_item_recycler_button;

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
