package app.gianglong.tracuudienthoai.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import app.gianglong.tracuudienthoai.ImageLoader.AppController;
import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

/**
 * Created by Giang Long on 7/28/2016.
 */

public class ListAdapter_top_product extends BaseAdapter{
    private Activity mActivity;
    private LayoutInflater mInflater;
    private ArrayList<ProductObject> mItems;

    ImageLoader mImageLoader = AppController.getmInstance().getmImageLoader();

    public ListAdapter_top_product(Activity activity, ArrayList<ProductObject> list){
        this.mActivity = activity;
        this.mItems = list;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(mInflater == null){
            mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = mInflater.inflate(R.layout.custom_row_top_product, null);

        }

        if(mImageLoader == null){
            mImageLoader = AppController.getmInstance().getmImageLoader();
        }
        NetworkImageView mImageView = (NetworkImageView) view.findViewById(R.id.row_ivLogo);
        TextView title= (TextView) view.findViewById(R.id.row_tvName);
        final ImageView ivStart = (ImageView) view.findViewById(R.id.row_ivFavorite);
        //getting data for row
        ProductObject item = mItems.get(i);
        mImageView.setImageUrl(item.getImage(), mImageLoader);
        //title
        title.setText(item.getName());

        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ivStart.getDrawable().getConstantState() == ivStart.getResources().getDrawable(R.drawable.star_gray).getConstantState()){
                    ivStart.setImageResource(R.drawable.star_yellow);
                }else{
                    ivStart.setImageResource(R.drawable.star_gray);
                }
            }
        });


        int positionID = 0;
        TextView tvViews = (TextView) view.findViewById(R.id.tvViews);
        for (int j = 0; j < MainActivity.arrCount.size(); j++) {
            if (item.getId().toString().equals(MainActivity.arrCount.get(j).getId())) {
                positionID = j;
            }
        }


        try {
            tvViews.setText(MainActivity.arrCount.get(positionID).getCount() + "");
        } catch ( Exception e ) {
            tvViews.setText("");
        }

        return view;
    }
}
