package org.proingesistinfor.ventasapp.activity.profile;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.activity.ad.AdActivity;
import org.proingesistinfor.ventasapp.adapters.AdAdapter;
import org.proingesistinfor.ventasapp.base.BaseActivity;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.interfaces.RecyclerViewOnItemClickListener;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.Ad;
import org.proingesistinfor.ventasapp.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity implements RecyclerViewOnItemClickListener {

    private static String KEY_USER = "USER";
    private Toolbar toolbar;
    private CircleImageView imageViewPhoto;
    private ImageView imageViewCover;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView textViewEmail;
    private RecyclerView recyclerViewAds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        User user = (User) getIntent().getSerializableExtra(KEY_USER);
        setBusinessLogic(new BusinessLogic(this));
        setAds(getBusinessLogic().getAdAll(user.getId(), 5));
        initialize(user);
    }

    private void initialize(User user) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imageViewCover = (ImageView) findViewById(R.id.imageViewCover);
        imageViewPhoto = (CircleImageView) findViewById(R.id.imageViewPhoto);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setCollapsedTitleTypeface(Util.fontBold(this));
        collapsingToolbarLayout.setExpandedTitleTypeface(Util.fontBold(this));
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewEmail.setTypeface(Util.fontRegular(this));
        recyclerViewAds = (RecyclerView) findViewById(R.id.recyclerView_ads);
        recyclerViewAds.setAdapter(new AdAdapter(getAds(), this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewAds.setLayoutManager(layoutManager);
        setUser(user);
        setCover(getUser().getCover());
        setPhoto(getUser().getPhoto());
        setFullName(getUser().getFullName());
        setEmail(getUser().getEmail());
    }

    @Override
    public void onClick(View view, int position) {
        Ad ad = getAds().get(position);
        replaceActivity(AdActivity.class, ad, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCover(String cover) {
        imageViewCover.setImageBitmap(Util.base64ToBitmap(cover));
    }

    private void setPhoto(String photo) {
        imageViewPhoto.setImageBitmap(Util.base64ToBitmap(photo));
    }

    private void setFullName(String fullName) {
        collapsingToolbarLayout.setTitle(fullName);
    }

    private void setEmail(String email) {
        textViewEmail.setText(email);
    }


}
