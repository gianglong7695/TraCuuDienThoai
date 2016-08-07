package app.gianglong.tracuudienthoai.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import app.gianglong.tracuudienthoai.ImageLoader.AppController;
import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.CountObjectProduct;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

/**
 * Created by Giang Long on 7/28/2016.
 */

public class ListAdapter_top_product extends BaseAdapter {
    private Activity mActivity;
    private LayoutInflater mInflater;
    private ArrayList<ProductObject> mItems;
    ArrayList<CountObjectProduct> alCount = new ArrayList<>();

    ImageLoader mImageLoader = AppController.getmInstance().getmImageLoader();

    public ListAdapter_top_product(Activity activity, ArrayList<ProductObject> list) {
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
        final ViewHolder viewHolder;
        alCount = MainActivity.arrCount;
        if (mInflater == null) {
            mInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = mInflater.inflate(R.layout.custom_row_top_product, null);

            viewHolder = new ViewHolder();
            viewHolder.mImageView = (NetworkImageView) view.findViewById(R.id.row_ivLogo);
            viewHolder.title = (TextView) view.findViewById(R.id.row_tvName);
            viewHolder.ivStart = (ImageView) view.findViewById(R.id.row_ivFavorite);
            viewHolder.progress = (ProgressBar) view.findViewById(R.id.pg_loadingImage);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (mImageLoader == null) {
            mImageLoader = AppController.getmInstance().getmImageLoader();

        }

        //getting data for row
        final ProductObject item = mItems.get(i);
        viewHolder.mImageView.setImageUrl(item.getImage(), mImageLoader);
        viewHolder.progress.setVisibility(View.GONE);
        //title
        viewHolder.title.setText(item.getName());

        if(MainActivity.db.isExistFavorite(item.getId())){
            item.setChecked(true);
        }


        if (item.isChecked()) {
            viewHolder.ivStart.setImageResource(R.drawable.star_yellow);
        } else {
            viewHolder.ivStart.setImageResource(R.drawable.star_gray);
        }

        viewHolder.ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isChecked()) {
                    item.setChecked(false);
                    MainActivity.db.deleteFavorite(item.getId());
                } else {
                    item.setChecked(true);
                    MainActivity.db.insertFavorite(item.getId(), 1, MainActivity.db.getFavorite());
                }
                notifyDataSetChanged();
            }
        });


        int positionID = 0;
        TextView tvViews = (TextView) view.findViewById(R.id.tvViews);
        for (int j = 0; j <alCount.size(); j++) {
            if (item.getId().toString().equals(alCount.get(j).getId())) {
                positionID = j;
            }
        }


        try {
            tvViews.setText(alCount.get(positionID).getCount() + "");
        } catch (Exception e) {
            tvViews.setText("");
        }

        return view;
    }


    static class ViewHolder {
        NetworkImageView mImageView;
        TextView title;
        ImageView ivStart;
        ProgressBar progress;


    }
}
