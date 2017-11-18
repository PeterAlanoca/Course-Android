package org.proingesistinfor.ventasapp.activity.menu.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.base.BaseFragment;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.Ad;

/**
 * Created by peter on 05-11-17.
 */

public class NewAdFragment extends BaseFragment implements View.OnClickListener{

    private static int CAMERA_PIC_REQUEST = 1000;
    private static int PERMISSIONS_REQUEST_CAMERA = 2001;
    private EditText editTextName;
    private EditText editTextPrice;
    private EditText editTextDescription;
    private Button buttonImage;
    private ImageView imageView;
    private FloatingActionButton fab;

    public static NewAdFragment newInstance() {
        NewAdFragment fragment = new NewAdFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_ad, container, false);
        setTitle("Nuevo anuncio");
        editTextName = (EditText) view.findViewById(R.id.editTextName);
        editTextPrice = (EditText) view.findViewById(R.id.editTextPrice);
        editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        buttonImage = (Button) view.findViewById(R.id.buttonImage);
        buttonImage.setOnClickListener(this);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        setBusinessLogic(new BusinessLogic(getActivity()));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonImage:
                checkPermission();
                break;
            case R.id.fab:
                adRegister(
                        getUser().getId(),
                        editTextName.getText().toString(),
                        editTextDescription.getText().toString(),
                        editTextPrice.getText().toString(),
                        imageView
                );
                break;
        }
    }

    private void adRegister(int idUser, String name, String description, String price, ImageView imageView){
        if (idUser >= 0 && !name.isEmpty() && !description.isEmpty() && !price.isEmpty() && imageView.getVisibility() != View.GONE) {
            Ad ad = new Ad(idUser, name, description, Double.parseDouble(price), Util.getBase64ImageView(imageView));
            if (getBusinessLogic().adRegister(ad)) {
                showToast("Anuncio registrado");
                replaceFragment(HomeFragment.newInstance());
            } else {
                showToast("Error en el registro");
            }
        } else {
            showToast("Datos incompletos");
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_PIC_REQUEST);
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openCamera();
        } else {
            int permissionCheck = getActivity().checkSelfPermission(Manifest.permission.CAMERA);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
            } else if (permissionCheck == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(PERMISSIONS_REQUEST_CAMERA == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                showToast("Permisos no concedidos!");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_PIC_REQUEST) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
            }
        }
    }
}