package app.gianglong.tracuudienthoai;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import app.gianglong.tracuudienthoai.Activity.SearchProduct_Activity;
import app.gianglong.tracuudienthoai.Activity.TopProduct_Activity;
import app.gianglong.tracuudienthoai.Activity.ViewBy_Activity;
import app.gianglong.tracuudienthoai.Database.Database;
import app.gianglong.tracuudienthoai.Objects.CountObjectProduct;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.Other.BroadcastReceiverChangeNetwork;
import app.gianglong.tracuudienthoai.Other.JsonAsynTask;
import app.gianglong.tracuudienthoai.Other.MyString;

import static app.gianglong.tracuudienthoai.Other.MyString.SQL_CREATE_TABLE;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mActivity;
    Button btSearch, btTopProduct, btUpdate, btViewBy;
    TextView tvLogo;
    String url = "http://gianglong7695.tk/Application/TraCuuDienThoai/ProductInfomation.json";
    public static Database db;
    public static ArrayList<ProductObject> arrl;
    AlertDialog.Builder builder;
    AlertDialog.Builder builder2;
    public static AlertDialog dialogNetwork;
    public static ProgressDialog progressDialog;
    public static Activity activity;
    Typeface mTypeface; // Create a font

    public static ArrayList<CountObjectProduct> arrCount;
    public static ArrayList<ProductObject> alTopProduct;


    public static DatabaseReference myRef;
    public static DatabaseReference mySizeFirebase;
    public static int sizeProduct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setViews();
        db = new Database(this);
        db.queryData(SQL_CREATE_TABLE); // Tạo bảng nếu chưa có
        db.queryData(MyString.SQL_HISTORY);
        arrl = new ArrayList<>();
        arrCount = new ArrayList<>();
        alTopProduct = new ArrayList<>();
        activity = this;
        setDialogNetwork();
        if(!isConnectNetwork()){
            dialogNetwork.show();
        }

        // Firebase
        myRef = FirebaseDatabase.getInstance().getReference("count");
        mySizeFirebase = FirebaseDatabase.getInstance().getReference("size");

        //  Khởi tạo dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Xin chờ ...");
        progressDialog.setCancelable(false);


        // Đổ dữ liệu từ file json về nếu chưa có
        if(db.getSize() == 0){
            builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Cảnh báo");
            builder.setMessage("Cơ sở dữ liệu hiện không sẵn có. Bạn có muốn cập nhật ngay bây giờ ?");
            builder.setNegativeButton("Không, cảm ơn", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setPositiveButton("Tất nhiên", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    progressDialog.show();
                    new JsonAsynTask().execute(url);
                }
            });

            builder.setCancelable(false);

            builder.create().show();
        }


        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), SearchProduct_Activity.class);
                startActivity(it);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);


            }
        });


        btViewBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent it = new Intent(MainActivity.this, ViewBy_Activity.class);
               startActivity(it);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


        btTopProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TopProduct_Activity.class);
                startActivity(it);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setTitle("Cập nhật");
                builder2.setMessage("Bạn có thực sự muốn cập nhật lại cơ sở dữ liệu ?");
                builder2.setNegativeButton("Để sau", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder2.setPositiveButton("Cập nhật ngay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Xóa CSDL và đổ lại về
                        progressDialog.show();
                        db.deleteDatabase(MyString.DATABASE_TABLE_NAME, null, null);
                        new JsonAsynTask().execute(url);
                    }
                });

                builder2.create().show();
            }
        });

    }


    public void setViews(){
        btSearch = (Button) findViewById(R.id.btSearch);
        btTopProduct = (Button) findViewById(R.id.btTopProduct);
        btUpdate = (Button) findViewById(R.id.btUpdateData);
        btViewBy = (Button)findViewById(R.id.btViewBy);
        tvLogo = (TextView) findViewById(R.id.tvLogo);
        // Set font
        mTypeface = Typeface.createFromAsset(getAssets(), "FiolexGirlVH.ttf");
        tvLogo.setTypeface(mTypeface);
    }


    @Override
    protected void onStart() {
        super.onStart();

        arrl = db.getProduct2();

        mySizeFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sizeProduct = Integer.parseInt(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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



    }

    public void setDialogNetwork(){
        dialogNetwork = new AlertDialog.Builder(MainActivity.this).create();
        dialogNetwork.setTitle("Cảnh báo");
        dialogNetwork.setMessage("Ứng dụng cần có mạng để hoạt động. Hãy kiểm tra mạng của bạn !");
        dialogNetwork.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }});

        dialogNetwork.setCancelable(false);
    }

    public boolean isConnectNetwork(){
        boolean isConnect = false;
        ConnectivityManager connec = (ConnectivityManager)getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {


            isConnect = true;

        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            MainActivity.mActivity.setContentView(R.layout.screen_network_disconect);
            LinearLayout layout = (LinearLayout) findViewById(R.id.layout_network_false);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(BroadcastReceiverChangeNetwork.isCheckNetwork){
                        MainActivity.mActivity.setContentView(R.layout.activity_main);
                    }
                }
            });

           isConnect = false;

        }

        return isConnect;
    }


//    public void loadingData(){
//        if(sizeProduct != 0){
//            progressDialog.show();
//
//            if(arrCount.){
//
//            }
//        }
//    }

}
