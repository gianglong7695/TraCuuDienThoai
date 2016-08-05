package app.gianglong.tracuudienthoai.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.gianglong.tracuudienthoai.Database.Database;
import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.Objects.CountObjectProduct;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

import static app.gianglong.tracuudienthoai.MainActivity.arrCount;
import static app.gianglong.tracuudienthoai.MainActivity.myRef;

public class SearchProduct_Activity extends AppCompatActivity {
    AutoCompleteTextView etSearch;
    ArrayList listNameProduct;
    Database db;
    Button btSearch;
    ImageView ivClear;
    Snackbar mSnackbar;
    public static ArrayList<ProductObject> arrlProduct;
    public static ProductObject object;
    ListView lvHistory;
    public static ArrayList<String> listHistory;
    ArrayAdapter adapterHistory;


    public static DatabaseReference firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product_);
        setTitle("Tìm kiếm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViews();
        db = new Database(this);

        listNameProduct = new ArrayList();
        listNameProduct = db.getProductName();
        arrlProduct = new ArrayList<>();
        listHistory = new ArrayList();
        listHistory = db.getHistory();
        db.getProduct();


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, listNameProduct);
        etSearch.setThreshold(1);
        etSearch.setAdapter(adapter);


        adapterHistory = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listHistory);
        lvHistory.setAdapter(adapterHistory);

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
            }
        });


        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etSearch.getText().toString().equals("")) {
                    int position = 0;
                    boolean result = false;
                    for (int i = 0; i < arrlProduct.size(); i++) {
                        if (etSearch.getText().toString().equals(arrlProduct.get(i).getName())) {
                            position = i;
                            result = true;
                        }
                    }
                    firebase = FirebaseDatabase.getInstance().getReference("count").child("product_id");
                    int count = 0;
                    try {
                        count = getCountProduct(arrlProduct.get(position).getId());
                    }catch (Exception e){

                    }

                    Map<String, String> user = new HashMap<String, String>();
                    user.put("count", String.valueOf(++count));

                    firebase.child(arrlProduct.get(position).getId()).setValue(user);

                    object = arrlProduct.get(position);

                    if (!result) {
                        Toast.makeText(SearchProduct_Activity.this, "Không tìm thấy kết quả !", Toast.LENGTH_SHORT).show();
                    } else {
                        db.addHistory(etSearch.getText().toString());
                        db.addHistorySearching(etSearch.getText().toString());


                        Intent it = new Intent(getApplicationContext(), ShowProduct_Activity.class);
                        it.putExtra("product", object);
                        startActivity(it);


                        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                    }
                } else {
                    mSnackbar = Snackbar.make(view, "Nhập vào để tìm kiếm ... ", Snackbar.LENGTH_LONG);
                    mSnackbar.setAction("Hiểu", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSnackbar.dismiss();
                        }
                    });
                    mSnackbar.show();
                }


            }
        });

        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int position = 0;
                for (int j = 0; j < arrlProduct.size(); j++) {
                    if (listHistory.get(i).equals(arrlProduct.get(j).getName())) {
                        position = j;
                    }
                }
                firebase = FirebaseDatabase.getInstance().getReference("count").child("product_id");
                int count = getCountProduct(arrlProduct.get(position).getId());
                Map<String, String> user = new HashMap<String, String>();
                user.put("count", String.valueOf(++count));

                firebase.child(arrlProduct.get(position).getId()).setValue(user);
                object = arrlProduct.get(position);

                db.addHistory(listHistory.get(i));
                db.addHistorySearching(listHistory.get(i));


                Intent it = new Intent(getApplicationContext(), ShowProduct_Activity.class);
                it.putExtra("product", object);
                startActivity(it);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }
        });

    }

    public void setViews() {
        etSearch = (AutoCompleteTextView) findViewById(R.id.etSearch);
        btSearch = (Button) findViewById(R.id.btSearchProduct);
        ivClear = (ImageView) findViewById(R.id.ivClear);
        lvHistory = (ListView) findViewById(R.id.lvHistory);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        }
        if(item.getItemId() == R.id.clear_history){
            db.deleteHistory();
            listHistory = db.getHistory();

            adapterHistory = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listHistory);
            lvHistory.setAdapter(adapterHistory);
            Toast.makeText(this, "Xóa lịch sử thành công !", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    public int getCountProduct(String id) {
        int position = 0;
        for (int i = 0; i < arrCount.size(); i++) {
            if (arrCount.get(i).getId().equals(id)) {
                position = i;
            }
        }

        return arrCount.get(position).getCount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MainActivity.arrCount.clear();
        myRef.child("product_id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, String> value = (Map<String, String>) dataSnapshot.getValue();
                arrCount.add(new CountObjectProduct(dataSnapshot.getKey(), Integer.parseInt(value.get("count"))));

                //handleText(dataSnapshot.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        etSearch.setText("");


        adapterHistory = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listHistory);
        lvHistory.setAdapter(adapterHistory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_activity_, menu);
        return true;
    }




}
