package com.example.gaja.blooddonor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;
/*import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.cognito.Dataset;
import com.amazonaws.mobileconnectors.cognito.DefaultSyncCallback;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.*;
import com.amazonaws.services.dynamodbv2.model.*;
*/


public class login extends AppCompatActivity {
    EditText number,login_password;
    String num,password,mail;
    Button login,register;
    FirebaseAuth fFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        number = (EditText) findViewById(R.id.login_user);
        login_password = (EditText) findViewById(R.id.login_pass);
        login=(Button) findViewById(R.id.login_button);
        register=(Button) findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                num = number.getText().toString();
                password =login_password.getText().toString();
                mail=num+"@gmail.com";



                if (num.isEmpty() || password.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "One or more field(s) is missing", Toast.LENGTH_LONG).show();


                }
                else if (number.getText().toString().equals("1234") && login_password.getText().toString().equals("1234")) {
                    Intent i = new Intent(getApplicationContext(),admin.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "ADMIN PANEL", Toast.LENGTH_SHORT).show();
                }else {

                    fFirebaseAuth = FirebaseAuth.getInstance();

                    fFirebaseAuth
                            .signInWithEmailAndPassword(mail, password)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {


                                        startActivity(new Intent(getApplicationContext(), navigation.class));
                                        Toast.makeText(getApplicationContext(), "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "LOGIN UNSUCCESSFUL", Toast.LENGTH_SHORT).show();


                                    }
                                }
                            });

                }
            }
        });

    }





    public void registers(View v)
    {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,register.class);
                startActivity(i);
            }
        });

    }



}










