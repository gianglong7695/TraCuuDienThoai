package app.gianglong.tracuudienthoai.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import app.gianglong.tracuudienthoai.ImageLoader.AppController;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

/**
 * Created by Giang Long on 7/28/2016.
 */

public class ListAdapter_product extends BaseAdapter{
    private Activity mActivity;
    private LayoutInflater mInflater;
    private ArrayList<ProductObject> mItems;

    ImageLoader mImageLoader = AppController.getmInstance().getmImageLoader();

    public ListAdapter_product(Activity activity, ArrayList<ProductObject> list){
        this.mActivity = activity;
        this.mItems = list;
    }
    @Override
    public int getCount() {
        return mItems.size();
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
            view = mInflater.inflate(R.layout.custom_row_product, null);

        }

        if(mImageLoader == null){
            mImageLoader = AppController.getmInstance().getmImageLoader();
        }
        NetworkImageView mImageView = (NetworkImageView) view.findViewById(R.id.row_ivLogo_product);
        TextView title = (TextView) view.findViewById(R.id.row_tvName_product);


        //getting data for row
        ProductObject item = mItems.get(i);

        mImageView.setImageUrl(item.getImage(), mImageLoader);
        title.setText(item.getName());

        return view;
    }
}
