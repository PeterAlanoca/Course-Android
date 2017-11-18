package org.proingesistinfor.ventasapp.base;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.Ad;
import org.proingesistinfor.ventasapp.model.User;

import java.util.List;

/**
 * Created by peter on 04-11-17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static String KEY_USER = "USER";
    private static String KEY_AD = "AD";
    private User user;
    private List<Ad> ads;
    private BusinessLogic businessLogic;
    private int onStartCount = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        } else {
            onStartCount = 2;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        } else if (onStartCount == 1) {
            onStartCount++;
        }
    }

    protected void setStyleToolbar(String title, Toolbar toolbar, boolean buttonBack) {
        setSupportActionBar(toolbar);
        ((TextView) toolbar.getChildAt(0)).setTypeface(Util.fontBold(this));
        setTitle(title);
        if (buttonBack) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    }

    protected void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.getView().setPadding(10, 10, 10, 10);
        toast.getView().setBackgroundColor(getResources().getColor(R.color.secondaryDarkColor));
        TextView text = (TextView) toast.getView().findViewById(android.R.id.message);
        text.setTextColor(getResources().getColor(R.color.colorPrimary));
        toast.show();
    }

    protected void replaceActivity(Class<?> cls, boolean finish) {
        startActivity(new Intent(getApplicationContext(), cls));
        if (finish) {
            finish();
        }
    }

    protected void replaceActivity(Class<?> cls, User user, boolean finish) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.putExtra(KEY_USER, user);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }

    protected void replaceActivity(Class<?> cls, Ad ad, boolean finish) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.putExtra(KEY_AD, ad);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }

    protected void replaceFragment(Fragment fragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public BusinessLogic getBusinessLogic() {
        return businessLogic;
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }
}
