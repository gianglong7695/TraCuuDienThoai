package app.gianglong.tracuudienthoai.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import app.gianglong.tracuudienthoai.Activity.ShowProduct_Activity;
import app.gianglong.tracuudienthoai.Adapter.ListAdapter_product;
import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

import static app.gianglong.tracuudienthoai.Fragment.Fragment_view_company.ft;


public class Fragment_ViewCompany_Product extends android.app.Fragment implements Serializable {
    View v;
    ListView lvCompanyProduct;
    int mPosition;
    ListAdapter_product adapter;
    ArrayList<ProductObject> alProduct;
    ImageView imageView;
    public  ProductObject object;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_fragment__view_company__product, container, false);
        lvCompanyProduct = (ListView) v.findViewById(R.id.lvCompanyProduct);
        imageView = (ImageView) v.findViewById(R.id.ivBackCompanyProduct);

        Bundle extras = getArguments();
        mPosition = extras.getInt("position");
        alProduct = new ArrayList<>();


        for (int i = 0 ; i < MainActivity.arrl.size(); i++){
            if(MainActivity.arrl.get(i).getCompany().equals(Fragment_ViewByCompany.arrComporation.get(mPosition))){
                alProduct.add(MainActivity.arrl.get(i));
            }
        }


        adapter = new ListAdapter_product(getActivity(), alProduct);
        lvCompanyProduct.setAdapter(adapter);

        lvCompanyProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                object =  alProduct.get(i);

                Intent it = new Intent(getActivity(), ShowProduct_Activity.class);
                it.putExtra("product",object);
                getActivity().startActivity(it);
                getActivity().overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft = getActivity().getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_layout, new Fragment_ViewByCompany());
                ft.commit();
            }
        });

        return v;
    }

}
