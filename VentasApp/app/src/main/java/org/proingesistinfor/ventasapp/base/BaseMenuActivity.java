package org.proingesistinfor.ventasapp.base;

import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by peter on 04-11-17.
 */

public class BaseMenuActivity extends BaseActivity {

    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected LinearLayout layoutCover;
    protected CircleImageView imageViewPhoto;
    protected TextView textViewFullName;
    protected TextView textViewEmail;

    protected void initialize(User user, NavigationView.OnNavigationItemSelectedListener listener) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setStyleToolbar("Inicio", toolbar, false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(listener);
        View header = navigationView.getHeaderView(0);
        layoutCover = (LinearLayout) header.findViewById(R.id.layoutCover);
        imageViewPhoto = (CircleImageView) header.findViewById(R.id.imageViewPhoto);
        textViewFullName = (TextView) header.findViewById(R.id.textViewName);
        textViewFullName.setTypeface(Util.fontBold(this));
        textViewEmail = (TextView) header.findViewById(R.id.textViewEmail);
        textViewEmail.setTypeface(Util.fontRegular(this));
        setBusinessLogic(new BusinessLogic(this));
        setUser(user);
        setAds(getBusinessLogic().getAdAll());
        setCover(getUser().getCover());
        setPhoto(getUser().getPhoto());
        setFullName(getUser().getFullName());
        setEmail(getUser().getEmail());
    }

    protected void setCover(String cover) {
        layoutCover.setBackgroundDrawable(new BitmapDrawable(Util.base64ToBitmap(cover)));
    }

    protected void setPhoto(String photo) {
        imageViewPhoto.setImageBitmap(Util.base64ToBitmap(photo));
    }

    protected void setFullName(String fullName) {
        textViewFullName.setText(fullName);
    }

    protected void setEmail(String email) {
        textViewEmail.setText(email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
