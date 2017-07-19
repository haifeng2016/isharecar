package com.samsung.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.samsung.isharecar.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLManager implements BaseData{
    
    private final String NAME_TABLE_CONTACT = "user";
    private final String NAME_TABLE_ROUTE = "Route";
    
    private String TAG = "SQLManager";

    private final String PINCHE = "app_pinche";
    private final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY, name TEXT, phone TEXT, sex TEXT, age TEXT, "
            + "occupation TEXT, signature TEXT, hometown TEXT,picstr TEXT, driverlicence TEXT, drivinglicence TEXT, carnumber TEXT, password TEXT);";
    private final String CREATE_ROUTE_TABLE = "CREATE TABLE IF NOT EXISTS route (id INTEGER PRIMARY KEY, userphone TEXT, username TEXT, "
            + "timego TEXT, numperson TEXT, home TEXT, work TEXT, passway TEXT, identify TEXT, numofpos TEXT, passengername TEXT);";
    private final String DELETE_TABLE_USER = "DELETE FROM user;";
    private final String DELETE_TABLE_ROUTE = "DELETE FROM route;";
    
    private SQLiteDatabase db;
    
    public SQLManager(Context context){
        db = context.openOrCreateDatabase(PINCHE, Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_ROUTE_TABLE);
    }

    /***********user table operation start**************/

    public long insertUser(Context context,String name, String phone, String sex, String age, String occupation,String signature,String hometown,String picstr, 
            String driverlicence, String drivinglicence, String carnumber, String password){
        ContentValues values = new ContentValues();
        values.put(USER_COLUM_NAME, name);
        values.put(USER_COLUM_PHONE, phone);
        values.put(USER_COLUM_SEX, sex);
        values.put(USER_COLUM_AGE, age);
        values.put(USER_COLUM_OCCU, occupation);
        values.put(USER_COLUM_SIG, signature);
        values.put(USER_COLUM_HOMETOWN, hometown);
        values.put(USER_COLUM_PICSTR, picstr);
        values.put(USER_COLUM_DRIVERLIC, driverlicence);
        values.put(USER_COLUM_DRIVINGLIC, drivinglicence);
        values.put(USER_COLUM_CARNUM, carnumber);
        values.put(USER_COLUM_PASSWORD, password);

        long id = db.insert(NAME_TABLE_CONTACT, "", values);
        return id;
    }
    
    //query all data
    public Cursor queryUser(Context context){
        Cursor c = db.query(NAME_TABLE_CONTACT, null, null, null, null, null, null, null);
        return c;
    }
    //query by name
    public Cursor queryUserByName(Context context, String value){
        //获取Cursor
        Cursor c = db.query(NAME_TABLE_CONTACT, null, USER_COLUM_NAME + "='" + value + "'", null, null, null, null);
        //Cursor query(String table, String[] columns, String selection,String[] selectionArgs, String groupBy, String having,String orderBy)
        return c;
    }
    
    //delete by id
    public void deleteContact(Context context, Contact mcontact){
        int id = mcontact.get_id();
        db.delete(NAME_TABLE_CONTACT, "id=?", new String[]{String.valueOf(id)});
    }

    //update the data and the phone number can't update
    public void updateUser(Context context,Contact mcontact){    
        ContentValues cv = new ContentValues();
        cv.put(USER_COLUM_NAME, mcontact.get_name());
        cv.put(USER_COLUM_SEX, mcontact.getSex());
        cv.put(USER_COLUM_AGE, mcontact.getAge());
        cv.put(USER_COLUM_OCCU, mcontact.getOccupation());
        cv.put(USER_COLUM_SIG, mcontact.getSignature());
        cv.put(USER_COLUM_HOMETOWN, mcontact.get_hometown());
        cv.put(USER_COLUM_PICSTR, mcontact.get_picStr());
        cv.put(USER_COLUM_DRIVERLIC, mcontact.getDriverLicense());
        cv.put(USER_COLUM_DRIVINGLIC, mcontact.getDrivingLicense());
        cv.put(USER_COLUM_CARNUM, mcontact.getCarNumber());
        cv.put(USER_COLUM_PASSWORD, mcontact.get_passWord());
        
        Log.d(TAG,"KMA ...name:"+mcontact.get_name());
        Log.d(TAG,"KMA ...sex:"+mcontact.getSex());
        Log.d(TAG,"KMA ...age:"+mcontact.getAge());
        Log.d(TAG,"KMA ...occupation:"+mcontact.getOccupation());
        Log.d(TAG,"KMA ...signature:"+mcontact.getSignature());
        Log.d(TAG,"KMA ...hometown:"+mcontact.get_hometown());
        Log.d(TAG,"KMA ...number:"+mcontact.get_phone_number());

        String phoneNum = mcontact.get_phone_number();
        String[] args = {String.valueOf(phoneNum)};
        updateUser(cv,USER_COLUM_PHONE+"=?",args);
    }
    
    //update the data 
    public void updateUser(ContentValues values, String whereClause, String[]whereArgs){
        db.update(NAME_TABLE_CONTACT, values, whereClause, whereArgs);
    }
    
    //close
    public void close(Context context){
        if(db != null){
            db.close();
        }
    }
    
    public List<Contact> retrieveListContact(Context context){
        
        List<Contact> listOfContact = new ArrayList<Contact>();

        Cursor c = queryUser(context);
        
        if(c.getCount() > 0){
            c.moveToFirst();
            for(int x = 0; x < c.getCount(); x++){
                listOfContact.add(new Contact(c.getInt(0), c.getString(1),c.getString(2), c.getString(3),c.getString(4),c.getString(5),
                        c.getString(6),c.getString(7),c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12)));
                c.moveToNext();
            }
        }

        return listOfContact;
    }
    
    public List<Contact> searchByContactColumn(Context context,String column, String valueSearch){
        Log.d(TAG,"KMA ...column:"+column);
        Log.d(TAG,"KMA ...valueSearch:"+valueSearch);
        List<Contact> listOfContact = new ArrayList<Contact>();
        Cursor c = db.query(NAME_TABLE_CONTACT, null, column + "='" + valueSearch + "'", null, null, null, null);
        Log.d(TAG,"KMA ...cursor count:"+(c.getCount()));
        
        if(c.getCount() > 0){
            c.moveToFirst();
            for(int x = 0; x < c.getCount(); x++){
                listOfContact.add(new Contact(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5),
                        c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10), c.getString(11), c.getString(12)));
                c.moveToNext();
            }
        }
        
        return listOfContact;
    }
    
    public void updateUserPic(Context context, Contact mcontact, Bitmap bitmap){
        /****to do***/
        return;
    }
    /***********user table operation end**************/

    /***********route table operation start**************/

    public long insertRoute(Context context,String userphone,String username, String time, String number, String home, String work, String passway,
            String identify, String numOfPosition, String passengerName){
        ContentValues values = new ContentValues();
        values.put(ROUTE_COLUM_USERPhone, userphone);
        values.put(ROUTE_COLUM_USERNAME,username);
        values.put(ROUTE_COLUM_TIME, time);
        values.put(ROUTE_COLUM_NUM, number);
        values.put(ROUTE_COLUM_HOME, home);
        values.put(ROUTE_COLUM_WORK, work);
        values.put(ROUTE_COLUM_PASSWAY, passway);
        values.put(ROUTE_COLUM_IDENTIFY, identify);
        values.put(ROUTE_COLUM_NUMOFPOS, numOfPosition);
        values.put(ROUTE_COLUM_PASSENGERNAME, passengerName);

        long id = db.insert(NAME_TABLE_ROUTE, "", values);
        Log.d(TAG,"KMA ...insert id:"+id);
        return id;
    }
    
    //query all route
    public Cursor queryRoute(Context context){
        Cursor c = db.query(NAME_TABLE_ROUTE, null, null, null, null, null, null, null);
        return c;
    }
    //query by home address
    public Cursor queryRouteByHome(Context context,String home){
        //获取Cursor
        Cursor c = db.query(NAME_TABLE_ROUTE, null, ROUTE_COLUM_HOME + "='" + home + "'", null, null, null, null);
        
        return c;
    }
    
    //delete by id
    public void deleteRoute(Context context, Route mroute){
        int id = mroute.getId();
        Log.d(TAG, "KMA ...id:"+id);
        int deleteId = db.delete(NAME_TABLE_ROUTE, "id=?", new String[]{String.valueOf(id)});
        Log.d(TAG, "KMA ...deleteId:"+deleteId);
    }

    //update the data 
    public void updateRoute(ContentValues values, String whereClause, String[]whereArgs){
        db.update(NAME_TABLE_ROUTE, values, whereClause, whereArgs);
    }

    public List<Route> retrieveListRoute(Context context){
        
        List<Route> listOfRoute = new ArrayList<Route>();

        Cursor c = queryRoute(context);
        
        if(c.getCount() > 0){
            
            c.moveToFirst();
            for(int x = 0; x < c.getCount(); x++){
                listOfRoute.add(new Route(c.getInt(0),c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                        c.getString(7), c.getString(8), c.getString(9), c.getString(10)));
                c.moveToNext();
            }
        }

        return listOfRoute;
    }
    
    
    public List<Route> searchByRouteColumn(Context context,String goTime){
        List<Route> listOfRoute = new ArrayList<Route>();
        Cursor c = db.query(NAME_TABLE_ROUTE, null, ROUTE_COLUM_TIME + ">='" + goTime + "'", null, null, null, null);
        
        if(c.getCount() > 0){
            c.moveToFirst();
            for(int x = 0; x < c.getCount(); x++){
                listOfRoute.add(new Route(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                        c.getString(7), c.getString(8), c.getString(9), c.getString(10)));
                c.moveToNext();
            }
        }
        return listOfRoute;
    }
    
    public List<Route> searchByUserRoute(Context context,String goTime, String userPhone){
        List<Route> listOfRoute = new ArrayList<Route>();
        Cursor c = db.query(NAME_TABLE_ROUTE, null, ROUTE_COLUM_TIME + ">='" + goTime + "'"+ " and "+ ROUTE_COLUM_HOME + "='" + userPhone + "'", null, null, null, null);
        
        if(c.getCount() > 0){
            c.moveToFirst();
            for(int x = 0; x < c.getCount(); x++){
                listOfRoute.add(new Route(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                        c.getString(7), c.getString(8),c.getString(9), c.getString(10)));
                c.moveToNext();
            }
        }
        return listOfRoute;
    }
    
    public List<Route> searchByRouteColumn(Context context,String goTime, String homeAddress){
        List<Route> listOfRoute = new ArrayList<Route>();
        Cursor c = db.query(NAME_TABLE_ROUTE, null, ROUTE_COLUM_TIME + ">='" + goTime + "'"+ " and "+ ROUTE_COLUM_HOME + "='" + homeAddress + "'", null, null, null, null);
        
        if(c.getCount() > 0){
            c.moveToFirst();
            for(int x = 0; x < c.getCount(); x++){
                listOfRoute.add(new Route(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                        c.getString(7), c.getString(8), c.getString(9), c.getString(10)));
                c.moveToNext();
            }
        }
        return listOfRoute;
    }
    
    public List<Route> searchByRouteColumn(Context context, String[] queryInfo){
        List<Route> listOfRoute = new ArrayList<Route>();
        String querySql = null;
        Log.d(TAG, "KMA ...searchByRouteColumn");
        for(int i = 0; i < lengthOfRoute; i++){
            if((i == 0) && (queryInfo[i] != null)){ // check userPhone
                    querySql = ROUTE_COLUM_USERPhone + "='" + queryInfo[i] + "'";
            }
            else if((i == 1) && (queryInfo[i] != null)){ // check userName
                if(querySql == null)
                    querySql = ROUTE_COLUM_USERNAME + "='" + queryInfo[i] + "'";
                else
                    querySql =querySql + " and "+ROUTE_COLUM_USERNAME + "='" + queryInfo[i] + "'";
            }
            else if((i == 2) && (queryInfo[i] != null)){  // check go time
                if(querySql == null)
                    querySql = ROUTE_COLUM_TIME + ">='" + queryInfo[i] + "'";
                else
                    querySql = querySql +" and "+ ROUTE_COLUM_TIME + ">='" + queryInfo[i] + "'";
            }
            else if((i == 4) && (queryInfo[i] != null)){ //check the start address
                if(querySql == null)
                    querySql = ROUTE_COLUM_HOME + "='" + queryInfo[i] + "'";
                else
                    querySql = querySql + " and "+ ROUTE_COLUM_HOME + "='" + queryInfo[i] + "'";
            }
            else if((i == 5) && (queryInfo[i] != null)){ //check the end address
                if(querySql == null)
                    querySql = ROUTE_COLUM_WORK + "='" + queryInfo[i] + "'";
                else
                    querySql = querySql + " and "+ ROUTE_COLUM_WORK + "='" + queryInfo[i] + "'";
            }
            else if((i == 7) && (queryInfo[i] != null)){ //check the identify 0:passenger; 1:driver
                if(querySql == null)
                    querySql = ROUTE_COLUM_IDENTIFY + "='" + queryInfo[i] + "'";
                else
                    querySql = querySql + " and "+ ROUTE_COLUM_IDENTIFY + "='" + queryInfo[i] + "'";
            }
            
            
            else if((i == 8) && (queryInfo[i] != null)){ 
                //check the car have the absent position number  
                //"0": query all of the route
                //"1": query all of the car have absent position
                Log.d(TAG, "KMA ...num of pos:"+(queryInfo[i]));
                if(querySql == null)
                    querySql = ROUTE_COLUM_NUMOFPOS + "='" + queryInfo[i] + "'";
                else
                    querySql = querySql + " and "+ ROUTE_COLUM_NUMOFPOS + ">='" + queryInfo[i] + "'";
            }
            
        }
        
        Log.d(TAG, "KMA ...querySql:"+querySql);
        
        Cursor c = db.query(NAME_TABLE_ROUTE, null, querySql, null, null, null, null);
        
        if(c.getCount() > 0){
            c.moveToFirst();
            for(int x = 0; x < c.getCount(); x++){
                listOfRoute.add(new Route(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                        c.getString(7), c.getString(8), c.getString(9), c.getString(10)));
                c.moveToNext();
            }
        }
        return listOfRoute;
    }
    
    public List<Route> searchByRouteColumn(Context context, List<Route> queryInfo){
        List<Route> listOfRoute = new ArrayList<Route>();
        String currentTime = null;
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentTime = df.format(day).toString();
        
        String homeAddress = null;
        String numPos = null;
        String identify = null;
        boolean hasFind = false;
        for(int i = 0; i < queryInfo.size(); i++){
            homeAddress = queryInfo.get(i).getHomeAddress();
            for(int k = 0; k < i; k++){
                if(homeAddress.equalsIgnoreCase(queryInfo.get(k).getHomeAddress())){
                    hasFind = true;
                }
            }
            if(hasFind){
                Log.d(TAG, "KMA ...duplicate home address, just return");
                continue;
            }
            identify = queryInfo.get(i).getIdentify();
            numPos = query_absent_driver_route;
            
            Cursor c = null;
            Log.d(TAG,"KMA ...identify:"+identify);
            if(identify.equalsIgnoreCase(Identify_Passenger)){
                Log.d(TAG, "KMA ...Passenger");
               c = db.query(NAME_TABLE_ROUTE, null, ROUTE_COLUM_TIME + ">='" + currentTime + "'"+ " and "+ ROUTE_COLUM_HOME + "='" + homeAddress + "'" +" and "+ ROUTE_COLUM_IDENTIFY +"='" + Identify_Driver +"'"+" and "+ ROUTE_COLUM_NUMOFPOS +">='" + numPos +"'", null, null, null, null);
            }
            else
                c = db.query(NAME_TABLE_ROUTE, null, ROUTE_COLUM_TIME + ">='" + currentTime + "'"+ " and "+ ROUTE_COLUM_HOME + "='" + homeAddress + "'" +" and "+ ROUTE_COLUM_IDENTIFY +"='" + Identify_Passenger +"'", null, null, null, null);
                
            if(c.getCount() > 0){
                c.moveToFirst();
                for(int x = 0; x < c.getCount(); x++){
                    listOfRoute.add(new Route(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                            c.getString(7), c.getString(8), c.getString(9), c.getString(10)));
                    c.moveToNext();
                }
            }
        }
        return listOfRoute;
    }

    /***********route table operation end**************/
    
    public void cleanUserDB(Context context){
        db.execSQL(DELETE_TABLE_USER);
    }
    public void cleanRouteDB(Context context){
        db.execSQL(DELETE_TABLE_ROUTE);
    }
}