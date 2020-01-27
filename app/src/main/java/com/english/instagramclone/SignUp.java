package com.english.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText edtEnterEmail , edtEnterName ,edtEnterPassword ;
    Button btnSignUp  , btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Sign Up");

        edtEnterEmail = findViewById(R.id.edtEnterEmail);
        edtEnterName = findViewById(R.id.edtEnterName);
        edtEnterPassword = findViewById(R.id.edtEnterPassword);

        edtEnterPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignUp);

                }

                return false;
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
//            ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSignUp :

                if(edtEnterEmail.getText().toString().equals("") || edtEnterName.getText().toString().equals("") || edtEnterPassword.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this, "Email or UserName or Password is empty .", Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEnterEmail.getText().toString());
                    appUser.setUsername(edtEnterName.getText().toString());
                    appUser.setPassword(edtEnterPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setTitle("Signing Up " + edtEnterName.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                transitionToSocialMediaActivity();
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " - ok", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage() + " - error", Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
                }

            break;

            case R.id.btnLogIn :
                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
            break;
        }

    }


    //يخفي الكيبور عند الضغط على الخلفية
    public void rootLayoutTapped(View view) {

        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transitionToSocialMediaActivity() {

        Intent intent = new Intent(SignUp.this , SocialMediaActivity.class);
        startActivity(intent);

    }
}
