package com.tyut.domain;
/*
* 路线javaBean
* */
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Route implements Serializable {
    //主键
    //路线编号
    private String routeId;
    //路线名称
    private String routeName;
     //价格
    private Double price;
    //出发时间
    private Date departureTime;
    private String departureTimeStr;
    //出发城市
    private String departureCity;
    private String finalCity;

    public String getFinalCity() {
        return finalCity;
    }

    public void setFinalCity(String finalCity) {
        this.finalCity = finalCity;
    }

    //获取路线的订单信息
    private AgencyOrder agencyOrder;

    public AgencyOrder getAgencyOrder() {
        return agencyOrder;
    }

    public void setAgencyOrder(AgencyOrder agencyOrder) {
        this.agencyOrder = agencyOrder;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDepartureTime() throws ParseException {
        if(departureTimeStr != null){
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return df.parse(departureTimeStr);
        }
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTimeStr() {
        if(departureTime != null){
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String time=df.format(departureTime);
            return time;
        }
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId='" + routeId + '\'' +
                ", routeName='" + routeName + '\'' +
                ", price=" + price +
                ", departureTime=" + departureTime +
                ", departureTimeStr='" + departureTimeStr + '\'' +
                ", departureCity='" + departureCity + '\'' +
                '}';
    }
}
