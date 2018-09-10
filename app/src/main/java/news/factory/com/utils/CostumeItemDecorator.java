package news.factory.com.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import news.factory.com.R;

public class CostumeItemDecorator extends RecyclerView.ItemDecoration {

    private Context mContext;

    public CostumeItemDecorator(Context context){
        this.mContext = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if(parent.getChildAdapterPosition(view)<parent.getAdapter().getItemCount()-2)
        outRect.bottom = (int)mContext.getResources().getDimension(R.dimen.dp16);
        else{
            outRect.bottom = (int)mContext.getResources().getDimension(R.dimen.dp5);
        }
    }
}
