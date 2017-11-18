package org.proingesistinfor.ventasapp.activity.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.base.BaseActivity;
import org.proingesistinfor.ventasapp.activity.login.LoginActivity;
import org.proingesistinfor.ventasapp.general.Util;

public class SplashActivity extends BaseActivity {

    private static int TIME_SPLASH = 1500;
    private TextView textViewTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewTitle.setTypeface(Util.fontBold(this));
        textViewTitle.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_title));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                replaceActivity(LoginActivity.class, true);
            }
        }, TIME_SPLASH);
    }
}
