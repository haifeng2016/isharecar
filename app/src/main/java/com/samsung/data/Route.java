package com.samsung.data;

import cn.bmob.v3.BmobObject;

public class Route extends BmobObject{

    // private variables
    private int _id;
    /*the id of user*/
    private String _userphone;
    /*the name of user*/
    private String _user_name;
    /*the time to go for user*/
    private String _timego;
    /*the number of passenger*/
    private String _user_phone;
    /*the start of the address*/
    private String _home_address;
    /*the end of the address*/
    private String _work_address;
    /*pass way */
    private String _pass_way;
    /*the identify for user:  0: passenger; 1: driver */
    private String identify;
    
    
    /*only for driver about the absent position*/
    private String numOfPosition;
    /*the name for all passenger, divide by the character of ';'*/
    private String passengerName;

    // empty constructor
    public Route(){
        
    }

    // constructor
    public Route(int id, String _userphone, String _user_name, String _time, String _number, String _home_address, String _work_address,
            String _pass_way, String identify,String numOfPosition, String passengerName){
        this.setId(id);
        this.setUserPhone(_userphone);
        this.setUserName(_user_name);
        this.setTime(_time);
        this.setNumber(_number);
        this.setHomeAddress(_home_address);
        this.setWorkAddress(_work_address);
        this.setPassWay(_pass_way);
        this.setIdentify(identify);
        this.setNumOfPosition(numOfPosition);
        this.setPassengerName(passengerName);
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getUserPhone() {
        return _userphone;
    }

    public void setUserPhone(String _userphone) {
        this._userphone = _userphone;
    }

    public String getTime() {
        return _timego;
    }

    public void setTime(String time) {
        this._timego = time;
    }

    public String getNumber() {
        return _user_phone;
    }

    public void setNumber(String number) {
        this._user_phone = number;
    }

    public String getHomeAddress() {
        return _home_address;
    }

    public void setHomeAddress(String _home_address) {
        this._home_address = _home_address;
    }

    public String getWorkAddress() {
        return _work_address;
    }

    public void setWorkAddress(String _work_address) {
        this._work_address = _work_address;
    }

    public String getPassWay() {
        return _pass_way;
    }

    public void setPassWay(String _pass_way) {
        this._pass_way = _pass_way;
    }

    public String getUserName() {
        return _user_name;
    }

    public void setUserName(String _user_name) {
        this._user_name = _user_name;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getNumOfPosition() {
        return numOfPosition;
    }

    public void setNumOfPosition(String numOfPosition) {
        this.numOfPosition = numOfPosition;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

}
