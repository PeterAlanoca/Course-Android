package org.proingesistinfor.ventasapp.activity.menu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.activity.login.LoginActivity;
import org.proingesistinfor.ventasapp.activity.menu.fragments.AdsFragment;
import org.proingesistinfor.ventasapp.activity.menu.fragments.NewAdFragment;
import org.proingesistinfor.ventasapp.activity.profile.ProfileActivity;
import org.proingesistinfor.ventasapp.base.BaseMenuActivity;
import org.proingesistinfor.ventasapp.activity.menu.fragments.HomeFragment;
import org.proingesistinfor.ventasapp.model.User;

public class MenuActivity extends BaseMenuActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String KEY_USER = "USER";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        User user = (User) getIntent().getSerializableExtra(KEY_USER);
        initialize(user, this);
        replaceFragment(new HomeFragment());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                replaceFragment(HomeFragment.newInstance());
                break;
            case R.id.nav_profile:
                replaceActivity(ProfileActivity.class, getUser(), false);
                break;
            case R.id.nav_my_ads:
                replaceFragment(AdsFragment.newInstance());
                break;
            case R.id.nav_new_ad:
                replaceFragment(NewAdFragment.newInstance());
                break;
            case R.id.nav_exit:
                replaceActivity(LoginActivity.class, true);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
