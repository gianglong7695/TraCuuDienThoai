package app.gianglong.tracuudienthoai.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import app.gianglong.tracuudienthoai.MainActivity;
import app.gianglong.tracuudienthoai.R;

public class SplashScreen_activity extends AppCompatActivity {
    Intent it;
    ImageView iv;
    Animation an;
    Animation an2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_activity);
        getSupportActionBar().hide();

        iv = (ImageView) findViewById(R.id.imageView);
        an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                it = new Intent(getBaseContext(), MainActivity.class);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                startActivity(it);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
