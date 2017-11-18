package org.proingesistinfor.ventasapp.activity.register;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.activity.login.LoginActivity;
import org.proingesistinfor.ventasapp.base.BaseActivity;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.User;
import java.io.File;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private static final int LOAD_IMAGE_REQUEST_COVER = 1001;
    private static final int LOAD_IMAGE_REQUEST_PHOTO = 1002;

    private Toolbar toolbar;
    private EditText editTextFullName;
    private EditText editTextBirthdate;
    private EditText editTextEmail;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonCover;
    private ImageView imageViewCover;
    private Button buttonPhoto;
    private CircleImageView imageViewPhoto;
    private FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setStyleToolbar("Registrate", toolbar, true);

        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextFullName.setTypeface(Util.fontRegular(this));
        editTextBirthdate = (EditText) findViewById(R.id.editTextBirthdate);
        editTextBirthdate.setTypeface(Util.fontRegular(this));
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextEmail.setTypeface(Util.fontRegular(this));
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextUsername.setTypeface(Util.fontRegular(this));
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword.setTypeface(Util.fontRegular(this));
        buttonCover = (Button) findViewById(R.id.buttonCover);
        buttonCover.setTypeface(Util.fontBold(this));
        buttonCover.setOnClickListener(this);
        imageViewCover = (ImageView) findViewById(R.id.imageViewCover);
        buttonPhoto = (Button) findViewById(R.id.buttonPhoto);
        buttonPhoto.setTypeface(Util.fontBold(this));
        buttonPhoto.setOnClickListener(this);
        imageViewPhoto = (CircleImageView) findViewById(R.id.imageViewPhoto);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setBusinessLogic(new BusinessLogic(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCover:
                checkPermission(LOAD_IMAGE_REQUEST_COVER);
                break;
            case R.id.buttonPhoto:
                checkPermission(LOAD_IMAGE_REQUEST_PHOTO);
                break;
            case R.id.fab:
                registerUser(
                        editTextFullName.getText().toString(),
                        editTextBirthdate.getText().toString(),
                        editTextEmail.getText().toString(),
                        editTextUsername.getText().toString(),
                        editTextPassword.getText().toString(),
                        imageViewPhoto,
                        imageViewCover
                );
                break;
        }
    }

    private void registerUser(String fullName, String birthdate, String email, String username,
                              String password, ImageView imageViewPhoto, ImageView imageViewCover) {

        if (!fullName.isEmpty() && !birthdate.isEmpty() && !email.isEmpty() && !username.isEmpty()
                && !password.isEmpty() && imageViewPhoto.getVisibility() != View.GONE && imageViewCover.getVisibility() != View.GONE) {
            User user = new User(
                    fullName,
                    birthdate,
                    email,
                    username,
                    password,
                    Util.getBase64ImageView(imageViewPhoto),
                    Util.getBase64ImageView(imageViewCover)
            );
            if (getBusinessLogic().userRegister(user)) {
                showToast("Registro exitoso!!!");
                replaceActivity(LoginActivity.class, true);
            } else {
                showToast("Hubo problemas en el registro.");
            }
        } else {
            showToast("Datos incompletos");
        }
    }

    private void openAlbumPicture(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    private void checkPermission(int requestCode) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openAlbumPicture(requestCode);
        } else {
            int permissionCheck = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode);
            } else if (permissionCheck == PackageManager.PERMISSION_GRANTED){
                openAlbumPicture(requestCode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(LOAD_IMAGE_REQUEST_COVER == requestCode || LOAD_IMAGE_REQUEST_PHOTO == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbumPicture(requestCode);
            } else {
                showToast("Permisos no concedidos!");
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void previewBitmap(Intent data, ImageView imageView) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        File file = new File(picturePath);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LOAD_IMAGE_REQUEST_COVER:
                    previewBitmap(data, imageViewCover);
                    break;
                case LOAD_IMAGE_REQUEST_PHOTO:
                    previewBitmap(data, imageViewPhoto);
                    break;
            }
        }
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
