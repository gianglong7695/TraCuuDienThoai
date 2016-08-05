package app.gianglong.tracuudienthoai.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import app.gianglong.tracuudienthoai.Other.SlidingTabLayout;
import app.gianglong.tracuudienthoai.Other.TabAdapter;
import app.gianglong.tracuudienthoai.R;

public class ViewByProduct_Activity extends AppCompatActivity {
    SlidingTabLayout mSlidingTabLayout;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_by_product_);
        getSupportActionBar().hide();


        mViewPager = (ViewPager) findViewById(R.id.vp_tab);
        mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), this));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tab);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
