package com.samsung.data;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.List;

public interface BaseData {
    /*user table*/
    public final String USER_COLUM_NAME = "name";
    public final String USER_COLUM_PHONE = "phone";
    public final String USER_COLUM_SEX = "sex";
    public final String USER_COLUM_AGE = "age";
    public final String USER_COLUM_OCCU = "occupation";
    public final String USER_COLUM_SIG = "signature";
    public final String USER_COLUM_HOMETOWN = "hometown";
    public final String USER_COLUM_PICSTR ="picstr";
    public final String USER_COLUM_DRIVERLIC = "driverlicence";
    public final String USER_COLUM_DRIVINGLIC = "drivinglicence";
    public final String USER_COLUM_CARNUM = "carnumber";
    public final String USER_COLUM_PASSWORD = "password";
    
    public final static int lenghtOfUser = 12;
    public final static int Index_User_Name = 0;
    public final static int Index_User_Phone = 1;
    public final static int Index_User_Sex = 2;
    public final static int Index_User_Age = 3;
    public final static int Index_User_Occu = 4;
    public final static int Index_User_Sig = 5;
    public final static int Index_User_HomeTown = 6;
    public final static int Index_User_PicStr = 7;
    public final static int Index_User_DriverLic = 8;
    public final static int Index_User_DrivingLic = 9;
    public final static int Index_User_CarNum = 10;
    public final static int Index_User_Password = 11;
    
    
    /*route table*/
    public final String ROUTE_COLUM_USERPhone = "userphone";
    public final String ROUTE_COLUM_USERNAME = "username";
    public final String ROUTE_COLUM_TIME = "timego";
    public final String ROUTE_COLUM_NUM = "numperson";
    public final String ROUTE_COLUM_HOME = "home";
    public final String ROUTE_COLUM_WORK = "work";
    public final String ROUTE_COLUM_PASSWAY = "passway";
    public final String ROUTE_COLUM_IDENTIFY ="identify";
    public final String ROUTE_COLUM_NUMOFPOS ="numofpos";
    public final String ROUTE_COLUM_PASSENGERNAME ="passengername";
    public final int lengthOfRoute = 10;
    
    public final static int Index_Route_UserPhone = 0;
    public final static int Index_Route_UserName = 1;
    public final static int Index_Route_Time = 2;
    public final static int Index_Route_Num = 3;
    public final static int Index_Route_Home = 4;
    public final static int Index_Route_Work = 5;
    public final static int Index_Route_PassWay = 6;
    public final static int Index_Route_Identify = 7;
    public final static int Index_Route_NumOfPos = 8;
    public final static int Index_Route_PassengerName = 9;
    
    public final static String Identify_Passenger = "0";
    public final static String Identify_Driver = "1";
    
    public final static String query_all_driver_route = "0";
    public final static String query_absent_driver_route = "1";
    
    /*user table operation*/
    public abstract long insertUser(Context context,String name, String phone, String sex, String age, String occupation,String signature,
            String homtown,String picstr, String driverlicence, String drivinglicence, String carnumber,String password);
    //public abstract Cursor queryUser(Context context);
    //public abstract Cursor queryUserByName(Context context,String value);
    public abstract void updateUser(Context context,Contact contact);
    public abstract List<Contact> retrieveListContact(Context context);
    public abstract void deleteContact(Context context, Contact mcontact);
    public abstract List<Contact> searchByContactColumn(Context context,String column, String valueSearch);
    public abstract void updateUserPic(Context context, Contact mcontact, Bitmap bitmap);
    
    /*route table operation*/
    public abstract long insertRoute(Context context,String userphone,String username, String time, String number, String home, String work, 
            String passway, String identify, String numofpos, String passengername);
    //public abstract Cursor queryRoute(Context context);
    //public abstract Cursor queryRouteByHome(Context context,String home);
    //public abstract void updateRoute(Context context,ContentValues values, String whereClause, String[]whereArgs);
    public abstract void deleteRoute(Context context, Route mroute);
    public abstract List<Route> retrieveListRoute(Context context);
    public abstract List<Route> searchByRouteColumn(Context context,String column, String valueSearch);
    public abstract List<Route> searchByRouteColumn(Context context,String goTime);
    public abstract List<Route> searchByRouteColumn(Context context, String[] queryInfo);
    public abstract List<Route> searchByRouteColumn(Context context, List<Route> queryInfo);
}
