package com.samsung.data;

import android.content.Context;
import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;

public class BmobManager implements BaseData{

    public static String APPID = "";
    private String TAG = "BmobManager";

    /*********return value***************/
    private List<Contact> queryContact = null;
    private long insertValue = 0;
    private List<Route> queryRoute = null;
    private boolean updateResulte = false;

    public BmobManager(Context context){
        //Bmob.initialize(this, APPID);
        //BmobPush.startWork(this, APPID);
    }

    /***********user table operation start**************/
    public long insertUser(Context context,String name, String phone, String sex, String age, String occupation,String signature,String hometown,
            String picstr, String driverlicence, String drivinglicence, String carnumber, String password){

            Contact contact = new Contact();
            contact.set_name(name);
            contact.set_phone_number(phone);
            contact.setSex(sex);
            contact.setAge(age);
            contact.setOccupation(occupation);
            contact.setSignature(signature);
            contact.set_hometown(hometown);
            contact.set_picStr(picstr);
            contact.setDriverLicense(driverlicence);
            contact.setDrivingLicense(drivinglicence);
            contact.setCarNumber(carnumber);
            contact.set_passWord(password);
            /*contact.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                insertValue = 1;
            }
            @Override
            public void onFailure(int code, String arg0) {
                insertValue = 0;
            }
        });*/
        return insertValue;
    }

    //query all contact data
    public List<Contact> retrieveListContact(Context context){

        BmobQuery<Contact> query = new BmobQuery<Contact>();
        query.order("-createdAt");
        /*query.findObjects(context, new FindListener<Contact>() {
            @Override
            public void onSuccess(List<Contact> arg0) {
                queryContact = arg0;
            }

            @Override
            public void onError(int code, String arg0) {
                //emptyView.setText(arg0);
            }
        });*/
        return queryContact;
    }
    
    public List<Contact> searchByContactColumn(Context context,String column, String valueSearch){
        BmobQuery<Contact> query = new BmobQuery<Contact>();
        query.addWhereEqualTo(column, valueSearch);
        /*query.findObjects(context, new FindListener<Contact>() {
            @Override
            public void onSuccess(List<Contact> arg0) {
                queryContact = arg0;
            }
            @Override
            public void onError(int code, String arg0) {
                queryContact = null;
            }
        });*/
        return queryContact;
    }
    

    //update contact data
    public void updateUser(Context context, Contact mcontact){
        Contact contact = mcontact;
        /*contact.update(context, String.valueOf(contact.get_id()), new UpdateListener(){
            @Override
            public void onSuccess(){
                Log.d("Contact","update successfully");
            }
            @Override
            public void onFailure(int code,String msg){
                Log.d("Contact","update fail");
            }
        });*/
    }

    //delete contact data
    public void deleteContact(Context context, Contact mcontact){
        Contact contact = mcontact;

        /*contact.delete(context, new DeleteListener(){
            @Override
            public void onSuccess(){
                Log.d("Contact","delete successfully");
            }
            @Override
            public void onFailure(int code,String msg){
                Log.d("Contact","delete fail");
            }
        });*/
    }

    public void updateUserPic(Context context, Contact mcontact, Bitmap bitmap){
        /****to do***/
        return;
    }
    /***********user table operation end**************/

    /***********route table operation start**************/
    public long insertRoute(Context context,String userphone,String username, String time, String number, String home, String work, String passway, 
            String identify, String numofpos, String passengername){

            Route route = new Route();
            route.setUserPhone(userphone);
            route.setTime(time);
            route.setUserName(username);
            route.setNumber(number);
            route.setHomeAddress(home);
            route.setWorkAddress(work);
            route.setPassWay(passway);
            route.setIdentify(identify);
            route.setNumOfPosition(numofpos);
            route.setPassengerName(passengername);
            /*route.save(context, new SaveListener() {
            @Override
            public void onSuccess() {
                insertValue = 1;

            }
            @Override
            public void onFailure(int code, String arg0) {
                insertValue = 0;
            }
        });*/
        return insertValue;

    }

    //query all route data
    public List<Route> retrieveListRoute(Context context){

        BmobQuery<Route> query = new BmobQuery<Route>();
        query.order("-createdAt");
        /*query.findObjects(context, new FindListener<Route>() {
            @Override
            public void onSuccess(List<Route> arg0) {
                queryRoute = arg0;
            }
            @Override
            public void onError(int code, String arg0) {
                queryRoute = null;
            }
        });*/
        return queryRoute;
    }

    public List<Route> searchByRouteColumn(Context context,String column, String valueSearch){
        BmobQuery<Route> query = new BmobQuery<Route>();
        query.addWhereEqualTo(column, valueSearch);
        /*query.findObjects(context, new FindListener<Route>() {
            @Override
            public void onSuccess(List<Route> arg0) {
                queryRoute = arg0;
            }
            @Override
            public void onError(int code, String arg0) {
                queryRoute = null;
            }
        });*/
        return queryRoute;
    }
    
    public List<Route> searchByRouteColumn(Context context,String goTime){
        return queryRoute;
    }

    //delete Route data
    public void deleteRoute(Context context, Route mroute){
        Route route = mroute;
        /*route.delete(context, new DeleteListener(){
            @Override
            public void onSuccess(){
                Log.d("Route","delete successfully");
            }
            @Override
            public void onFailure(int code,String msg){
                Log.d("Route","delete fail");
            }
        });*/
    }

    //update contact data
    public boolean updateRoute(Context context, Route mroute){
        Route route = mroute;

        /*route.update(context, String.valueOf(route.getId()), new UpdateListener(){
            @Override
            public void onSuccess(){
                Log.d("Contact","update successfully");
                updateResulte = true;
            }
            @Override
            public void onFailure(int code,String msg){
                Log.d("Contact","update fail");
                updateResulte = false;
            }
        });*/
        return updateResulte;
    }
    
    public List<Route> searchByRouteColumn(Context context, String[] queryInfo){
        BmobQuery<Route> query = new BmobQuery<Route>();
        queryRoute = null;
        for(int i = 0; i < lengthOfRoute; i++){
            if((i == 0) && (queryInfo[i] != null)){ // check userPhone
                query.addWhereEqualTo(ROUTE_COLUM_USERPhone, queryInfo[i]);
            }
            if((i == 2) && queryInfo[i] != null) // check the go time
                query.addWhereGreaterThanOrEqualTo(ROUTE_COLUM_TIME, queryInfo[i]);
            
            else if((i == 4) && (queryInfo[i] != null)){ //check the start address
                query.addWhereEqualTo(ROUTE_COLUM_HOME, queryInfo[i]);
            }
            else if((i == 5) && (queryInfo[i] != null)){ //check the end address
                query.addWhereEqualTo(ROUTE_COLUM_WORK, queryInfo[i]);
            }
            else if((i == 7) && (queryInfo[i] != null)){ //check the identify 0:passenger; 1:driver
                query.addWhereEqualTo(ROUTE_COLUM_IDENTIFY, queryInfo[i]);
            }
            else if((i == 8) && (queryInfo[i] != null)){ // check the absent position
                query.addWhereGreaterThanOrEqualTo(ROUTE_COLUM_NUMOFPOS, queryInfo[i]);
            }
        }
        /*query.findObjects(context, new FindListener<Route>() {
            @Override
            public void onSuccess(List<Route> arg0) {
                queryRoute = arg0;
            }
            @Override
            public void onError(int code, String arg0) {
                queryRoute = null;
            }
        });*/
        return queryRoute;
    }

    public List<Route> searchByRouteColumn(Context context, List<Route> queryInfo){
        BmobQuery<Route> query = new BmobQuery<Route>();
        
        String currentTime = null;
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTime = df.format(day).toString();
        
        String homeAddress = null;
        String identify = null;
        for(int i = 0; i < queryInfo.size(); i++){
            homeAddress = queryInfo.get(i).getHomeAddress();
            identify = "1";
            query.addWhereEqualTo(ROUTE_COLUM_HOME, homeAddress);
            query.addWhereGreaterThanOrEqualTo(ROUTE_COLUM_TIME, currentTime);
            query.addWhereEqualTo(ROUTE_COLUM_IDENTIFY, identify);
            
            /*query.findObjects(context, new FindListener<Route>() {
                @Override
                public void onSuccess(List<Route> arg0) {
                    if(queryRoute != null)
                        queryRoute.addAll(arg0);
                    else
                        queryRoute = arg0;
                }
                @Override
                public void onError(int code, String arg0) {
                    Log.d(TAG, "query error:"+arg0);
                }
            });*/
        }
        return queryRoute;
    }

    /***********route table operation end**************/
}