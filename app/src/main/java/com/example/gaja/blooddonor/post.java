package com.example.gaja.blooddonor;

/**
 * Created by gaja on 4/27/2017.
 */

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class post {
    public String name;
    public String place;
    public String phone;

    //public Map<String, Boolean> stars = new HashMap<>();

    public post(){

    }

    public post(String Name, String Place, String Number){
        this.name = Name;
        this.place = Place;
        this.phone = Number;

    }

//    @Exclude
//    public Map<String, Object> toMap() {
//        HashMap<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
//        result.put("discription", about);
//        result.put("title", title);
//        result.put("teamnum", teamnum);
//        result.put("email", email);
//        result.put("domain", domain);
//
//        return result;
//    }
}
