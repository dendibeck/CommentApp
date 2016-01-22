package com.example.dsunardi.commentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {
    protected EditText mUsername;
    protected EditText mEmail;
    protected EditText mPassword;
    protected Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //initialize
        mUsername = (EditText)findViewById(R.id.usernameText);
        mEmail = (EditText)findViewById(R.id.emailText);
        mPassword = (EditText)findViewById(R.id.passwordText);
        mRegisterButton = (Button)findViewById(R.id.registerButton);

        //Listen register button
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the username, password and email and convert to string
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();

                //store user in parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            //user signing up successfully
                            Toast.makeText(RegisterActivity.this, "Congratulation, please login", Toast.LENGTH_LONG).show();

                            //take user Loginpage
                            Intent takeUserLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(takeUserLogin);

                        } else {
                            //still was an error signing up user
                        }
                    }
                });
            }
        });
    }

}
