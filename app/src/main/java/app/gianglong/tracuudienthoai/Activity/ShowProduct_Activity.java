package app.gianglong.tracuudienthoai.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import app.gianglong.tracuudienthoai.ImageLoader.AppController;
import app.gianglong.tracuudienthoai.Objects.ProductObject;
import app.gianglong.tracuudienthoai.R;

public class ShowProduct_Activity extends AppCompatActivity {
    NetworkImageView ivProduct;
    TextView tvCompany, tvYear, tvScreen, tvSystem, tvCamera, tvCpu, tvRam, tvInternal, tvExternal, tvSim, tvConnect, tvPin, tvType, tvSkill, tvPrice;
    LinearLayout parameter_layout;
    ToggleButton tbShowParameter;
    //public static ProgressBar pbProduct;

    ProductObject object;
    ListView lvLink;
    ArrayList<String> arrLink;
    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        object = (ProductObject) getIntent().getSerializableExtra("product");
        setTitle(object.getName());
        setViews();
        setText();
        parameter_layout.setVisibility(View.GONE); // Ẩn parameter layout
        arrLink = new ArrayList<>();


        if(mImageLoader == null){

            mImageLoader = AppController.getmInstance().getmImageLoader();
        }
        ivProduct.setImageUrl(object.getImage(), mImageLoader);
        //ShowProduct_Activity.pbProduct.setVisibility(View.GONE);


        tbShowParameter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    parameter_layout.setVisibility(View.VISIBLE);

                }else{
                    parameter_layout.setVisibility(View.GONE);
                }
            }
        });




    }

    public void setText(){
        tvCompany.setText(object.getCompany());
        tvYear.setText(String.valueOf(object.getYear()));

        tvScreen.setText(object.getParameterObject().getScreen());
        tvSystem.setText(object.getParameterObject().getSystem());
        tvCamera.setText(object.getParameterObject().getCamera());
        tvCpu.setText(object.getParameterObject().getCpu());
        tvRam.setText(object.getParameterObject().getRam());
        tvInternal.setText(object.getParameterObject().getInternalMemory());
        tvExternal.setText(object.getParameterObject().getExternalMemory());
        tvSim.setText(object.getParameterObject().getSim());
        tvConnect.setText(object.getParameterObject().getConnect());
        tvPin.setText(object.getParameterObject().getPin());
        tvType.setText(object.getParameterObject().getType());
        tvSkill.setText(object.getParameterObject().getSkill());

        tvPrice.setText(object.getPrice());

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
        }
        return super.onOptionsItemSelected(item);
    }


    public void setViews(){
        ivProduct = (NetworkImageView) findViewById(R.id.ivProduct);
        tvCompany = (TextView) findViewById(R.id.tvCompany);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvScreen = (TextView) findViewById(R.id.tvScreen);
        tvSystem = (TextView) findViewById(R.id.tvSystem);
        tvCamera = (TextView) findViewById(R.id.tvCamera);
        tvCpu = (TextView) findViewById(R.id.tvCpu);
        tvRam = (TextView) findViewById(R.id.tvRam);
        tvInternal = (TextView) findViewById(R.id.tvInternal);
        tvExternal = (TextView) findViewById(R.id.tvExternal);
        tvSim = (TextView) findViewById(R.id.tvSim);
        tvConnect = (TextView) findViewById(R.id.tvConnect);
        tvPin = (TextView) findViewById(R.id.tvPin);
        tvType = (TextView) findViewById(R.id.tvType);
        tvSkill = (TextView) findViewById(R.id.tvSkill);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        parameter_layout = (LinearLayout) findViewById(R.id.parameter);
        tbShowParameter = (ToggleButton) findViewById(R.id.tbParameter);
        lvLink = (ListView) findViewById(R.id.lvLink);
        //pbProduct = (ProgressBar) findViewById(R.id.pbProduct);
    }
}
