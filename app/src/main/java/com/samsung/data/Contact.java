package com.samsung.data;

import cn.bmob.v3.BmobObject;

/*
 * Contact
 */

public class Contact extends BmobObject{

    // private variables
    private int _id;
    /*the name for user*/
    private String _name;
    /*the phone number of user*/
    private String _phone_number;
    //private String _home_address;
    //private String _work_address;
    /*the sex for user*/
    private String _sex;
    /*the age for user*/
    private String _age;
    /*the occupation for user*/
    private String _occupation;
    /*the signature for user*/
    private String _signature;
    /*the home town for user*/
    private String _hometown;
    /*the address of picture for user*/
    private String _picStr;
    /*the password for user login*/
    private String _passWord;
    
    
    /****the infromation for car****/
    
    /*the driver license for user*/
    private String driverLicense;
    /*the driving license for user*/
    private String drivingLicense;
    /*the car number for user*/
    private String carNumber;
    
    
    // empty constructor
    public Contact(){}
    
    // constructor
    public Contact(int id, String name, String phone_number,String sex, String age, String occupation, String singature,
            String hometown,String picstr, String driverLicense, String drivingLicense, String carNumber,String password){
        this.set_id(id);
        this.set_name(name);
        this.set_phone_number(phone_number);
        this.setSex(sex);
        this.setAge(age);
        this.setOccupation(occupation);
        this.setSignature(singature);
        this.set_hometown(hometown);
        this.set_picStr(picstr);
        this.setDriverLicense(driverLicense);
        this.setDrivingLicense(drivingLicense);
        this.setCarNumber(carNumber);
        this.set_passWord(password);
    }
    
    // constructor
    public Contact(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;
    }

    // getting id
    public int get_id() {
        return _id;
    }

    // setting id
    public void set_id(int _id) {
        this._id = _id;
    }

    // getting name
    public String get_name() {
        return _name;
    }

    // setting name
    public void set_name(String _name) {
        this._name = _name;
    }

    // getting phone number
    public String get_phone_number() {
        return _phone_number;
    }

    // setting phone number
    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }

    /*public String getHomeAddress() {
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
    }*/

    public String getSex() {
        return _sex;
    }

    public void setSex(String _sex) {
        this._sex = _sex;
    }

    public String getAge() {
        return _age;
    }

    public void setAge(String _age) {
        this._age = _age;
    }

    public String getOccupation() {
        return _occupation;
    }

    public void setOccupation(String _occupation) {
        this._occupation = _occupation;
    }

    public String getSignature() {
        return _signature;
    }

    public void setSignature(String _signature) {
        this._signature = _signature;
    }

    public String get_hometown() {
        return _hometown;
    }

    public void set_hometown(String _hometown) {
        this._hometown = _hometown;
    }

    public String get_picStr() {
        return _picStr;
    }

    public void set_picStr(String _picStr) {
        this._picStr = _picStr;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String get_passWord() {
        return _passWord;
    }

    public void set_passWord(String _passWord) {
        this._passWord = _passWord;
    }
}
