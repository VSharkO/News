package news.factory.com.base.view_holders.home.HomeItemButton;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import news.factory.com.R;

public class HomeItemButtonHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.homeButton)
    Button button;

    public HomeItemButtonHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void onBind(int position) {

    }
}


