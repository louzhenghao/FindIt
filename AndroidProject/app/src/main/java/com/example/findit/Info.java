package com.example.findit;

import android.app.Application;
import android.util.Log;

import com.example.findit.entity.User;


public class Info extends Application {



    public static final String BASE_URL = "http://192.168.2.102:8080/EnjoyBallServer/";

    public static String registrationId ;
    @Override
    public void onCreate() {
        super.onCreate();

//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);


//        String registrationId = JPushInterface.getRegistrationID(this);
        Log.e("1099", "run:--------->registrationId： "+registrationId );

//        registrationId = JPushInterface.getRegistrationID(this);
        Log.e("1099","id"+registrationId);

    }

    //当前登录的用户的信息
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    //当前用户头像存储地址
//    public static final String HEADPORTRAIT_URL= "/data/user/0/com.example.lenovo.enjoyball/files/HeadPortrait.jpg";




}
