package app.gianglong.tracuudienthoai.Other;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import app.gianglong.tracuudienthoai.Fragment.Fragment_view_company;
import app.gianglong.tracuudienthoai.Fragment.Fragment_view_favorite;
import app.gianglong.tracuudienthoai.Fragment.Fragment_view_price;
import app.gianglong.tracuudienthoai.R;

/**
 * Created by giang on 5/4/2016.
 */
public class TabAdapter extends FragmentPagerAdapter{
    Context mContext;
    String[] titles = {"Comporation", "Money", "Favorite"};
    int [] icons = {R.drawable.ic_copyright_white_24dp, R.drawable.ic_attach_money_white_24dp, R.drawable.ic_favorite_border_white_24dp};
    int heightIcon;

    public TabAdapter(FragmentManager fm, Context c) {
        super(fm);

        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon = (int)(24*scale + 0.5f);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment mFragment = null;
        switch (position){
            case 0:
                mFragment = new Fragment_view_company();
                break;
            case 1:
                mFragment = new Fragment_view_price();
                break;
            case 2:
                mFragment = new Fragment_view_favorite();
                break;

        }

        Bundle b = new Bundle();
        b.putInt("position", position);

        mFragment.setArguments(b);
        return mFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Drawable d = mContext.getResources().getDrawable(icons[position]);
        d.setBounds(0,0, heightIcon, heightIcon);

        ImageSpan is = new ImageSpan(d);
        SpannableString sp = new SpannableString(" ");
        sp.setSpan(is, 0, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return (sp);
    }
}
