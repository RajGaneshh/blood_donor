package com.example.gaja.blooddonor;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class search extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    EditText result_grp, result_place;
    String place, bloodgroup;
    Button search1;
    DatabaseReference mDatabase;
    ArrayList<String> bb;
    TextView t;
    private FirebaseAuth firebaseAuth;
    private AlertDialog.Builder builder;
    public String val;
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        boolean valid = true;
        val = "a+";
        result_grp = (EditText) findViewById(R.id.bloodgrp);
        search1 = (Button) findViewById(R.id.search);
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = result_grp.getText().toString();
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        bb = new ArrayList<>();
        t = (TextView) findViewById(R.id.name);
        val = result_grp.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("edttext", val);
// set Fragmentclass Arguments
        PostListFragment fragobj = new PostListFragment();
        fragobj.setArguments(bundle);


            mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){

                private final Fragment[] mFragments = new Fragment[]{
                        new PostListFragment()
                };
                private final String[] mFragmentNames = new String[]{
                        "Recent"
                };
                @Override
                public Fragment getItem(int position){
                    return mFragments[position];
                }
                @Override
                public int getCount() {
                return mFragments.length;
                }
                @Override
                public CharSequence getPageTitle(int position){
                return mFragmentNames[position];
            }
            };


            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mPagerAdapter);

        };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

