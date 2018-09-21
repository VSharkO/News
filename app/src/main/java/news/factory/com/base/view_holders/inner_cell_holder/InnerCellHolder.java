package news.factory.com.base.view_holders.inner_cell_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.joda.time.DateTime;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;
import news.factory.com.base.RecyclerWrapper;
import news.factory.com.model.Articles;
import news.factory.com.model.TopNews;
import news.factory.com.utils.Constants;
import timber.log.Timber;

public class InnerCellHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.innerPagerFragmentTitle)
    TextView title;
    @BindView(R.id.innerPagerFragmentpublished)
    TextView published;
    @BindView(R.id.innerPagerFragmentshares)
    TextView shares;
    @BindView(R.id.innerPagerFragmentImage)
    ImageView image;
    @BindView(R.id.categoryInnerText)
    TextView category;

    private List<RecyclerWrapper> dataList;

    public InnerCellHolder(View itemView, List<RecyclerWrapper> dataList) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.dataList = dataList;
    }

    public void onBind(int position){
        InnerCellData data = (InnerCellData) dataList.get(position).getData();
        Articles articles = data.getArticles();

        title.setText(articles.getTitle());
        published.setText(getPastDaysNumber(articles.getPublishedAtHumans())); //logika za ovo
        shares.setText(articles.getShares());
        Glide.with(image.getContext())
                .load(Constants.NEWS_PICTURE_BASE_URL + articles.getFeaturedImage().getL())
                .into(image);
        category.setText(articles.getCategory());
    }

    public String getPastDaysNumber(String date) {
        String result = "da";
        String[] dateSpliter = date.split(" ");
        String[] time = dateSpliter[1].split(":");
        dateSpliter = dateSpliter[0].split("\\.");

        int year = Integer.parseInt(dateSpliter[2]);
        int month = Integer.parseInt(dateSpliter[1]);
        int day = Integer.parseInt(dateSpliter[0]);
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(dateSpliter[1]);

        DateTime dateTimeArticle = new DateTime(year, month, day, hours, minutes);
        DateTime dateTime = new DateTime();

        int yearDif = dateTime.getYear() - year;
        int monthDif = dateTime.getMonthOfYear() - month;
        int dayDif = dateTime.getDayOfMonth() - day;
        int hourDif = dateTime.getHourOfDay() - dateTimeArticle.getHourOfDay();

        //godina
        if (yearDif > 0) {
            if (yearDif%10 < 5 && yearDif%10 > 0 )
                result = "Prije " + yearDif + " godine";
            else result = "Prije " + yearDif + " godina";
        //mjesec
        } else if (yearDif == 0 && monthDif > 0) {

            if (monthDif%10>1 && monthDif%10 < 5)
                result = "Prije " + monthDif + " mjeseca";

            else if(monthDif%10==1)
                result = "Prije " + monthDif + " mjesec";
            else
                result = "Prije " + monthDif + " mjeseci";
        //dan
        } else if (yearDif == 0 && monthDif == 0 && dayDif > 0 && hourDif < 24) {
            if (dayDif%10 == 1)
                result = "Prije " + dayDif + " dan";
            else
                result = "Prije " + dayDif + " dana";
        //sat
        } else if (yearDif == 0 && monthDif == 0 && dayDif == 0) {
            if (hourDif%10 > 4 && hourDif%10 < 21) {
                result = "Prije " + hourDif + " sati";
            } else {
                result = "Prije " + hourDif + " sata";
            }
        }
        return result;
    }
}
