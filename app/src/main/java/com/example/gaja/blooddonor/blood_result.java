//package com.example.gaja.blooddonor;
//
///**
// * Created by gaja on 4/27/2017.
// */
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.EditText;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.Query;
//
//public class blood_result extends PostListFragment {
//
//    public blood_result() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public Query getQuery(DatabaseReference databaseReference) {
//        // All my posts
//        search s = new search();
//        String str = s.result_grp.getText().toString();
//        if(str != null){
//            return databaseReference.child("bloodgroup").child("a+");
//        }
//        else{
//            return databaseReference.child("bloodgroup").child(str);
//        }
//
//    }
//
//}