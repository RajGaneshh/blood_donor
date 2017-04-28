package com.example.gaja.blooddonor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Locale;

public class add_donor_admin extends AppCompatActivity {
    Spinner spin2;
    EditText donor_name,donor_place, donor_number,donor_bloodgrp;
    String username, place, phone, password,email,bloodgroup;
    Button add_donor;

    String mUser;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mDatabase;
    FirebaseUser mFirebaseUser;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Locale[] locales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_add_donor_admin);



        donor_name = (EditText)findViewById(R.id.name);
        donor_place = (EditText)findViewById(R.id.place);
        donor_number = (EditText)findViewById(R.id.num);
        add_donor =(Button)findViewById(R.id.add_admin);
        donor_bloodgrp = (EditText)findViewById(R.id.blood_grp_admin);

        //bloodgroup();






        add_donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = donor_name.getText().toString();

                phone = donor_number.getText().toString();

                place=donor_place.getText().toString();

                bloodgroup=donor_bloodgrp.getText().toString();


                email = phone + "@gmail.com";
                password = phone;


                Log.d("email", "Sfasfasfasf");
                bloodgroup = donor_bloodgrp.getText().toString();
                if(!(bloodgroup.equals("A+") || bloodgroup.equals("O+")||bloodgroup.equals("AB+") || bloodgroup.equals("A-")
                        ||bloodgroup.equals("B-") || bloodgroup.equals("O-")||bloodgroup.equals("B+") || bloodgroup.equals("AB-")
                        ||bloodgroup.equals("a+") || bloodgroup.equals("o+")||bloodgroup.equals("ab+") || bloodgroup.equals("a-")
                        ||bloodgroup.equals("b-") || bloodgroup.equals("o-")||bloodgroup.equals("b+") || bloodgroup.equals("ab-")))
                {
                    Toast.makeText(add_donor_admin.this, "Re-enter the Blood Group", Toast.LENGTH_SHORT).show();
                    donor_bloodgrp.setText("");
                }






                else if (username.isEmpty() || place.isEmpty() ||
                        phone.isEmpty() )
                {

                    Toast.makeText(getApplicationContext(), "One or more field(s) is missing", Toast.LENGTH_LONG).show();


                }
              else {


                    mFirebaseAuth = FirebaseAuth.getInstance();

                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(add_donor_admin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        mFirebaseAuth = FirebaseAuth.getInstance();
                                        mFirebaseUser = mFirebaseAuth.getCurrentUser();
                                        mUser = mFirebaseUser.getUid();

                                        mDatabase = FirebaseDatabase.getInstance().getReference();


                                        mDatabase.child("donors").child(mUser).child("data").child("email").setValue(email);
                                        mDatabase.child("donors").child(mUser).child("data").child("name").setValue((donor_name.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("phone").setValue((donor_number.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("password").setValue((donor_number.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("place").setValue((donor_place.getText().toString().toUpperCase()));
                                        //mDatabase.child("donors").child(mUser).child("data").child("address").setValue((regAddress.getText().toString()));
                                        //fDatabase.child("donors").child(fUser).child("data").child("coords").setValue((regCoords.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("bloodgroup").setValue((donor_bloodgrp.getText().toString().toUpperCase()));
                                        mDatabase.child("bloodgroup").child(bloodgroup).child(mUser).child("name").setValue(donor_name.getText().toString());
                                        mDatabase.child("bloodgroup").child(bloodgroup).child(mUser).child("phone").setValue(donor_number.getText().toString());

                                        mDatabase.child("bloodgroup").child(bloodgroup).child(mUser).child("place").setValue(donor_place.getText().toString());



                                        save("phone", phone);
                                        save("place",place);
                                        save("name", username);




                                        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                                                .addOnCompleteListener(add_donor_admin.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (task.isSuccessful()) {

                                                            FirebaseMessaging.getInstance().subscribeToTopic("Ap");
                                                            FirebaseMessaging.getInstance().subscribeToTopic("An");
                                                            FirebaseMessaging.getInstance().subscribeToTopic("Bp");
                                                            FirebaseMessaging.getInstance().subscribeToTopic("Bn");
                                                            FirebaseMessaging.getInstance().subscribeToTopic("Op");
                                                            FirebaseMessaging.getInstance().subscribeToTopic("On");
                                                            FirebaseMessaging.getInstance().subscribeToTopic("Ap");
                                                            FirebaseMessaging.getInstance().subscribeToTopic("An");


                                                            Log.d("AndroidBash", "Subscribed");
                                                            Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                                                            Toast.makeText(getApplicationContext(), "NOTE:PHONE NUMBER AND PASSWORD IS THE SAME", Toast.LENGTH_SHORT).show();
                                                            String token = FirebaseInstanceId.getInstance().getToken();
                                                            Log.d("AndroidBash", token);

                                                            save("code", "1");
                                                            Intent gotologin = new Intent(getApplicationContext(), login.class);
                                                            startActivity(gotologin);

                                                        } else {

                                                            Toast.makeText(getApplicationContext(), "NOT REGISTERED :(", Toast.LENGTH_SHORT).show();


                                                        }
                                                    }
                                                });


                                    } else {

                                        Toast.makeText(getApplicationContext(), "NOT REGISTERED", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }
            }
        });



    }


    public void save(String key, String value) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    /*

A+
O+
B+
AB+
A-
O-
B-
AB- */
 /*  public void bloodgroup()
    {
        locales=Locale.getAvailableLocales();

        bloodgrp=new ArrayList<String>();

        bloodgrp.add("A+");
        bloodgrp.add("O+");
        bloodgrp.add("B+");
        bloodgrp.add("AB+");
        bloodgrp.add("A-");
        bloodgrp.add("B-");
        bloodgrp.add("AB-");
        stringArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,bloodgrp);
        spin.setAdapter(stringArrayAdapter);



    }*/








}

