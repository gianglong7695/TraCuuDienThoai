package app.gianglong.tracuudienthoai.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import app.gianglong.tracuudienthoai.R;

public class Introduct_activity extends AppCompatActivity {
    String [] arr = {"Điều khoản sử dụng","Tính năng & Hướng dẫn", "Kiểm tra phiên bản mới", "Hỗ trợ", "Liên hệ"};
    ArrayAdapter<String> adapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduct_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Thông tin ứng dụng");

        lv = (ListView) findViewById(R.id.lvInfo);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        }
        return true;
    }
}
