package app.gianglong.tracuudienthoai.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import app.gianglong.tracuudienthoai.R;

/**
 * Created by Giang Long on 7/29/2016.
 */

public class Fragment_view_price extends Fragment{
    ListView lvPrice;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_layout_view_price, container,false);
        lvPrice = (ListView) v.findViewById(R.id.lvPriceProduct);




        return v;
    }
}
