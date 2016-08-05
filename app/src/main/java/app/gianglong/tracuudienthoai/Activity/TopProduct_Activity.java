package app.gianglong.tracuudienthoai.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import app.gianglong.tracuudienthoai.Adapter.ListAdapter_top_product;
import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.CountObjectProduct;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

public class TopProduct_Activity extends AppCompatActivity {
    ListView lv;
    ArrayList<CountObjectProduct> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_product_);
        setTitle("Top 10 tìm kiếm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv = (ListView) findViewById(R.id.lvTopProduct);

        list = new ArrayList<>();
        list = MainActivity.arrCount;


        // Sắp xếp theo thứ tự tăng dần
        Collections.sort(list, new Comparator<CountObjectProduct>() {
            public int compare(CountObjectProduct self, CountObjectProduct other) {
                return Integer.valueOf(self.getCount()).compareTo(other.getCount());
            }
        });
        // Đảo chiều arraylist
        Collections.reverse(list);




        for (int i = 0; i < 10; i++){
            try {
                String id = list.get(i).getId();
                for (int j = 0 ; j < MainActivity.arrl.size(); j++){
                    if(id.equals(MainActivity.arrl.get(j).getId())){
                        ProductObject obj = MainActivity.arrl.get(j);
                        MainActivity.arrl.remove(j);
                        MainActivity.arrl.add(i, obj);
                    }
                }
            }catch (Exception e){

            }

        }

        ListAdapter_top_product adapter = new ListAdapter_top_product(this, MainActivity.arrl);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProductObject object = MainActivity.arrl.get(i);
                Intent it = new Intent(getApplicationContext(), ShowProduct_Activity.class);
                it.putExtra("product", object);
                startActivity(it);

                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);

        }
        return super.onOptionsItemSelected(item);
    }

}
