package news.factory.com.base.view_holders.single.article_content;

import android.text.Html;

public class ArticleContentData {
    private String text;

    public ArticleContentData(String text) {
        this.text = text;
    }

    public String getText() {
        return Html.fromHtml(text).toString();
    }
}
