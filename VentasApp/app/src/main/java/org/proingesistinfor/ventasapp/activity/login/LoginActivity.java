package org.proingesistinfor.ventasapp.activity.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.proingesistinfor.ventasapp.R;
import org.proingesistinfor.ventasapp.activity.register.RegisterActivity;
import org.proingesistinfor.ventasapp.activity.menu.MenuActivity;
import org.proingesistinfor.ventasapp.base.BaseActivity;
import org.proingesistinfor.ventasapp.general.Util;
import org.proingesistinfor.ventasapp.logic.BusinessLogic;
import org.proingesistinfor.ventasapp.model.User;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private TextView textViewTitle;
    private TextInputLayout textInputLayoutUsername;
    private EditText editTextUsername;
    private TextInputLayout textInputLayoutPassword;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewTitle.setTypeface(Util.fontBold(this));
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutUsername.setTypeface(Util.fontRegular(this));
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextUsername.setTypeface(Util.fontRegular(this));
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutPassword.setTypeface(Util.fontRegular(this));
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPassword.setTypeface(Util.fontRegular(this));
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setTypeface(Util.fontBold(this));
        buttonLogin.setOnClickListener(this);
        textViewRegister = (TextView) findViewById(R.id.textViewRegister);
        textViewRegister.setTypeface(Util.fontRegular(this));
        textViewRegister.setOnClickListener(this);
        setBusinessLogic(new BusinessLogic(this));
        //userAuthentication("peter.alanoca", "123456");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                userAuthentication(editTextUsername.getText().toString(), editTextPassword.getText().toString());
                break;
            case R.id.textViewRegister:
                replaceActivity(RegisterActivity.class, false);
                break;
        }
    }

    private void userAuthentication(String username, String password) {
        if (!username.isEmpty() && !password.isEmpty()){
            User user = getBusinessLogic().userAuthentication(username, password);
            if (user.getId() > 0) {
                showToast("Bienvenido " + user.getFullName());
                replaceActivity(MenuActivity.class, user, true);
            } else {
                showToast("Usuario invalido!");
            }
        } else {
            showToast("Datos incompletos!");
        }
    }
}
