package com.tyut.domain;

//实现序列化
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
* traveller旅客的javabean
* */
public class Traveller implements Serializable {
    //旅客id
    private Integer id;
    //旅客的用户名
    private String userName;
    //旅客的真实姓名
    private String trueName;
    //旅客的登陆密码
    private String password;
    //旅客的邮箱
    private String email;
    //旅客的电话
    private String phone;
    //旅客的性别
    private Integer sex;
    //旅客的身份证号
    private String ID_Card;
    //旅客的生日
    private Date birthday;
    private String birthdayStr;
    //旅客的余额
    private Double balance;
    //获取旅行社信息
    private List<Agency> agencies;
    //获取旅游路线信息
    private List<Route> routeList;

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public String getBirthdayStr() {
        if(birthday != null){
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            String time=df.format(birthday);
            return time;
        }
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getID_Card() {
        return ID_Card;
    }

    public void setID_Card(String ID_Card) {
        this.ID_Card = ID_Card;
    }

    public Date getBirthday() throws ParseException {
        if(birthdayStr != null){
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(birthdayStr);
        }
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Traveller{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", trueName='" + trueName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", ID_Card='" + ID_Card + '\'' +
                ", birthday=" + birthday +
                ", balance=" + balance +
                '}';
    }
}
