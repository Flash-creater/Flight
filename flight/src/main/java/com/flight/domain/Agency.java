package com.flight.domain;
/*
* 旅行社javaBean
* */
import java.io.Serializable;
import java.util.List;

public class Agency implements Serializable {
    //主键
    private String id;
    //旅行社名称
    private String agencyName;
    //登录密码
    private String password;
    // 旅行社信息
     private String agencyInfo;
    // 联系人
    private String contactName;
    //联系人电话
    private String contactPhone;
    //联系人邮箱
    private String email;
    //旅行社内的旅游路线
    private List<Route> routeList;

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

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyInfo() {
        return agencyInfo;
    }

    public void setAgencyInfo(String agencyInfo) {
        this.agencyInfo = agencyInfo;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "id='" + id + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", agencyInfo='" + agencyInfo + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", email='" + email + '\'' +
                ", routeList=" + routeList +
                '}';
    }
}
