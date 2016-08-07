package app.gianglong.tracuudienthoai.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import app.gianglong.tracuudienthoai.Activity.ShowProduct_Activity;
import app.gianglong.tracuudienthoai.Adapter.ListAdapter_product;
import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.FavoriteObject;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

import static app.gianglong.tracuudienthoai.MainActivity.db;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_view_favorite extends Fragment {
    View mView;
    ArrayList<ProductObject> alFavorite;
    ListView lvFavorite;
    ListAdapter_product adapter;
    ArrayList<FavoriteObject> listFavorite = new ArrayList<>();
    ArrayList<ProductObject> listProduct = new ArrayList<>();
    AlertDialog.Builder builder;
    ProductObject objectProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_fragment_view_favorite, container, false);
        lvFavorite = (ListView) mView.findViewById(R.id.lvFavorite);
        alFavorite = new ArrayList<>();

        // Set dialog

        listFavorite = db.getFavorite();
        listProduct = MainActivity.arrl;

        for (int i = 0 ; i < listProduct.size(); i++){
            for (int j = 0 ; j < listFavorite.size(); j++){
                if(listProduct.get(i).getId().equals(listFavorite.get(j).getId())){
                    alFavorite.add(listProduct.get(i));
                }
            }
        }

        adapter = new ListAdapter_product(getActivity(), alFavorite);
        lvFavorite.setAdapter(adapter);

        lvFavorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ProductObject object =  alFavorite.get(position);

                Intent it = new Intent(getActivity(), ShowProduct_Activity.class);
                it.putExtra("product",object);
                startActivity(it);
                getActivity().overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        });


        lvFavorite.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                objectProduct =  alFavorite.get(position);
                builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có chắc sẽ xóa sản phẩm này ra khỏi danh sách ưa thích ?");
                builder.setNegativeButton("Không, cảm ơn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("Tất nhiên", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.db.deleteFavorite(objectProduct.getId());
                        alFavorite.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.create().show();

                return true;
            }
        });


        return mView;
    }

}
