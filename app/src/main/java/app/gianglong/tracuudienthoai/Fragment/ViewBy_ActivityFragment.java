package app.gianglong.tracuudienthoai.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.gianglong.tracuudienthoai.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ViewBy_ActivityFragment extends Fragment {

    public ViewBy_ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_by_, container, false);
    }
}
