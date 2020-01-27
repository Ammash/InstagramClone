package com.english.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText  edtEnterName ,edtEnterPassword ;
    Button btnSignUp  , btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtEnterName = findViewById(R.id.edtEnterName);
        edtEnterPassword = findViewById(R.id.edtEnterPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSignUp :
                Intent intent = new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
                break;

            case R.id.btnLogIn :

                ParseUser.logInInBackground(edtEnterName.getText().toString(), edtEnterPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e == null){
                            transitionToSocialMediaActivity();
                            FancyToast.makeText(LoginActivity.this,user.getUsername() + " - ok", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }else {

                        }
                    }
                });
                break;
        }

    }

    public void transitionToSocialMediaActivity() {

        Intent intent = new Intent(LoginActivity.this , SocialMediaActivity.class);
        startActivity(intent);

    }
}
