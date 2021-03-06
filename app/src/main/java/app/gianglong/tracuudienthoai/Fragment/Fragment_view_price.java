package app.gianglong.tracuudienthoai.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import app.gianglong.tracuudienthoai.R;

/**
 * Created by Giang Long on 7/29/2016.
 */

public class Fragment_view_price extends Fragment{
    ListView lvPrice;
    String [] listPrice = {"Không còn bán", "Dưới 3 triệu", "Từ 3 - 7 triệu", "Từ 7 - 10 triệu", "Từ 10 - 15 triệu", "Trên 15 triệu"};
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_layout_view_price, container,false);
        lvPrice = (ListView) v.findViewById(R.id.lvPriceProduct);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listPrice);
        lvPrice.setAdapter(adapter);

        lvPrice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Chức năng hiện đang xây dựng ...", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
