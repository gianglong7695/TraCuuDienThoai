package app.gianglong.tracuudienthoai.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.gianglong.tracuudienthoai.R;

/**
 * Created by Giang Long on 7/29/2016.
 */

public class Fragment_view_company extends Fragment{
    View mView;
    public static FragmentTransaction ft; // Add fragment in layout
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_layout_view_company, container,false);
        ft = getActivity().getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_layout, new Fragment_ViewByCompany());
        ft.commit();

        return mView;
    }
}
