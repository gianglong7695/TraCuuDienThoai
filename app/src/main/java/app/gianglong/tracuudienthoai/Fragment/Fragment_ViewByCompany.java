package app.gianglong.tracuudienthoai.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.R;


public class Fragment_ViewByCompany extends android.app.Fragment {
    View v;
    ListView lvCompany;
    public static ArrayList<String> arrComporation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fragment__view_by_company, container, false);
        lvCompany = (ListView) v.findViewById(R.id.lvCompany);
        arrComporation = new ArrayList<>();

        String s = MainActivity.arrl.get(0).getCompany();
        arrComporation.add(MainActivity.arrl.get(0).getCompany());
        for (int i = 1 ; i < MainActivity.arrl.size(); i++){
            if(!MainActivity.arrl.get(i).getCompany().equals(s)){
                s = MainActivity.arrl.get(i).getCompany();
                arrComporation.add(MainActivity.arrl.get(i).getCompany());
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,arrComporation);
        lvCompany.setAdapter(adapter);

        lvCompany.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Bundle data = new Bundle();
                data.putInt("position", position);
                Fragment_view_company.ft = getActivity().getFragmentManager().beginTransaction();

                Fragment_ViewCompany_Product sub = new Fragment_ViewCompany_Product();
                sub.setArguments(data);

                Fragment_view_company.ft.replace(R.id.fragment_layout, sub);
                Fragment_view_company.ft.commit();
            }
        });




        return v;
    }

}
