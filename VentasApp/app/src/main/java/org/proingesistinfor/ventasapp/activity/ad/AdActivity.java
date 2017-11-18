package org.proingesistinfor.ventasapp.activity.ad;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.base.BaseActivity;
import org.proingesistinfor.ventasapp.activity.profile.ProfileActivity;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.Ad;
import org.proingesistinfor.ventasapp.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdActivity extends BaseActivity implements View.OnClickListener{

    private static final String KEY_AD = "AD";
    private Toolbar toolbar;
    private ImageView imageViewCover;
    private CircleImageView imageViewPhoto;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView textViewPrice;
    private TextView textViewDescription;
    private TextView textViewFullName;
    private TextView textViewEmail;
    private FloatingActionButton fab;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        Ad ad = (Ad) getIntent().getSerializableExtra(KEY_AD);
        setBusinessLogic(new BusinessLogic(this));
        initialize(ad);
    }

    private void initialize(Ad ad) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imageViewCover = (ImageView) findViewById(R.id.imageViewCover);
        imageViewPhoto = (CircleImageView) findViewById(R.id.imageViewPhoto);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setCollapsedTitleTypeface(Util.fontBold(this));
        collapsingToolbarLayout.setExpandedTitleTypeface(Util.fontBold(this));
        textViewPrice = (TextView) findViewById(R.id.textViewPrice);
        textViewPrice.setTypeface(Util.fontBold(this));
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewDescription.setTypeface(Util.fontRegular(this));
        textViewFullName = (TextView) findViewById(R.id.textViewName);
        textViewFullName.setTypeface(Util.fontBold(this));
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewEmail.setTypeface(Util.fontRegular(this));
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setAd(ad);
    }

    @Override
    public void onClick(View v) {
        replaceActivity(ProfileActivity.class, user, false);
    }

    private void setAd(Ad ad) {
        imageViewCover.setImageBitmap(Util.base64ToBitmap(ad.getImage()));
        collapsingToolbarLayout.setTitle(ad.getName());
        textViewPrice.setText("Bs. " + ad.getPrice());
        textViewDescription.setText(ad.getDescription());
        user = getBusinessLogic().getUser(ad.getIdUser());
        imageViewPhoto.setImageBitmap(Util.base64ToBitmap(user.getPhoto()));
        textViewFullName.setText(user.getFullName());
        textViewEmail.setText(user.getEmail());
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


}
