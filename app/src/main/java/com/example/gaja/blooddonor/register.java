package com.example.gaja.blooddonor;

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

public class register extends AppCompatActivity{

    EditText register_name, register_pass, register_place, register_number,register_bloodgrp;
    String username, place, phone, password,email,bloodgroup;
    ArrayList<String> bloodgrp;
    ArrayAdapter<String> stringArrayAdapter;


    Button register_button;



    Spinner spin;

    String mUser;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mDatabase;
    FirebaseUser mFirebaseUser;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Locale[] locales;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_name = (EditText)findViewById(R.id.name);
        register_pass = (EditText)findViewById(R.id.reg_pass);
        register_place = (EditText)findViewById(R.id.place);
        register_number = (EditText)findViewById(R.id.num);
        register_bloodgrp= (EditText)findViewById(R.id.blood_grp_reg);

        //bloodgroup();
        bloodgrp=new ArrayList<>();
        bloodgrp.add("a+");
        bloodgrp.add("b+");
        bloodgrp.add("ab+");
        bloodgrp.add("o+");
        bloodgrp.add("a-");
        bloodgrp.add("b-");
        bloodgrp.add("o-");
        bloodgrp.add("ab-");







        register_button =(Button)findViewById(R.id.reg_data);

     //   spin=(Spinner) findViewById(R.id.spinner);
       // ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.bloodgroup,android.R.layout.simple_spinner_item);
        //spin.setAdapter(adapter);
        /*spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {

                int pos=spin.getSelectedItemPosition();
                if((!(pos==0)||(pos==1)))
                {
                   bloodgroup= bloodgrp.get(pos);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

*/



        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = register_name.getText().toString();

                phone = register_number.getText().toString();

                password = register_pass.getText().toString();
                email = phone + "@gmail.com";

                bloodgroup = register_bloodgrp.getText().toString();
                if(!(bloodgroup.equals("A+") || bloodgroup.equals("O+")||bloodgroup.equals("AB+") || bloodgroup.equals("A-")
                    ||bloodgroup.equals("B-") || bloodgroup.equals("O-")||bloodgroup.equals("B+") || bloodgroup.equals("AB-")
                    ||bloodgroup.equals("a+") || bloodgroup.equals("o+")||bloodgroup.equals("ab+") || bloodgroup.equals("a-")
                        ||bloodgroup.equals("b-") || bloodgroup.equals("o-")||bloodgroup.equals("b+") || bloodgroup.equals("ab-")))
                {
                    Toast.makeText(register.this, "Re-enter the Blood Group", Toast.LENGTH_SHORT).show();
                    register_bloodgrp.setText("");
                }









                else if (username.isEmpty() || email.isEmpty() ||
                        phone.isEmpty() ||
                        password.isEmpty() ) {

                    Toast.makeText(getApplicationContext(), "One or more field(s) is missing", Toast.LENGTH_LONG).show();


                }
                else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password should have at least 6 characters", Toast.LENGTH_LONG).show();
                    register_pass.setText("");

                }  else {


                    mFirebaseAuth = FirebaseAuth.getInstance();

                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                public static final String TAG = "TAG";

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        mFirebaseAuth = FirebaseAuth.getInstance();
                                        mFirebaseUser = mFirebaseAuth.getCurrentUser();
                                        mUser = mFirebaseUser.getUid();

                                        mDatabase = FirebaseDatabase.getInstance().getReference();
                                        String value=register_bloodgrp.getText().toString();

                                        if(bloodgrp.contains(value))
                                        {
                                            String key = mDatabase.child("bloodgroup").push().getKey();
                                            mDatabase.child("bloodgroup").child(value).child(key).child("name").setValue(register_name.getText().toString());
                                            mDatabase.child("bloodgroup").child(value).child(key).child("phone").setValue(register_number.getText().toString());

                                            mDatabase.child("bloodgroup").child(value).child(key).child("place").setValue(register_place.getText().toString());
                                            Log.d(TAG, "onComplete: " + register_name.getText().toString() + " " +register_place.getText().toString()+" "+ register_number.getText().toString());

                                        } else
                                        {
                                            Toast.makeText(register.this, "Wrong entry", Toast.LENGTH_SHORT).show();
                                            register_bloodgrp.setText("");
                                        }


                                        mDatabase.child("donors").child(mUser).child("data").child("email").setValue(email);
                                        mDatabase.child("donors").child(mUser).child("data").child("name").setValue((register_name.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("phone").setValue((register_number.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("password").setValue((register_pass.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("place").setValue((register_place.getText().toString()));
                                        //mDatabase.child("donors").child(mUser).child("data").child("address").setValue((regAddress.getText().toString()));
                                        //fDatabase.child("donors").child(fUser).child("data").child("coords").setValue((regCoords.getText().toString()));
                                        mDatabase.child("donors").child(mUser).child("data").child("bloodgroup").setValue((register_bloodgrp.getText().toString().toUpperCase()));




                                        save("phone", phone);
                                        save("place",place);
                                        save("name", username);




                                        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                                                .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                                        if (!task.isSuccessful()) {

                                                            Toast.makeText(register.this, "successful", Toast.LENGTH_SHORT).show();
                                                            save("code", "1");
                                                            Intent gotologin = new Intent(getApplicationContext(), login.class);
                                                            startActivity(gotologin);

                                                        } else {

                                                            Toast.makeText(getApplicationContext(), "unsuccessful", Toast.LENGTH_SHORT).show();


                                                        }
                                                    }
                                                });


                                    } else {

                                        Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
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

