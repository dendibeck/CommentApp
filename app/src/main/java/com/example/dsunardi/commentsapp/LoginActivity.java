package com.example.dsunardi.commentsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    protected EditText mUsername;
    protected EditText mPassword;
    protected Button mLoginBtn;
    protected Button mCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //initialize component
        mUsername = (EditText)findViewById(R.id.usernameLoginText);
        mPassword = (EditText)findViewById(R.id.passwordLoginText);
        mLoginBtn = (Button)findViewById(R.id.loginButton);
        mCreateAccount = (Button)findViewById(R.id.createAccountButton);

        //create account button to register page
        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(toRegister);
            }
        });

        //listen to when the mLogin buton is clicked
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the user input from edittext convert to string
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //login the user using parse sdk
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e == null){
                            //success! user login
                            Toast.makeText(LoginActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();
                            Intent takeUserHome = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(takeUserHome);
                        }else{
                            //sorry there was a problem
                            AlertDialog.Builder builder =  new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sorry");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //close dialog
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }
        });

    }

//    public void toRegisterAct(View view) {
//        Intent takeUserToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
//        startActivity(takeUserToRegister);
//    }
}
