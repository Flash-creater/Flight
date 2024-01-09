package com.tyut.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgencyOrder implements Serializable {
    private Integer id;
    private String aid;
    private String routeId;
    private String tid;
    private Date orderTime;
    private String orderTimeStr;
    private Integer orderStatus;
    private String orderStatusStr;
    private Date handlerTime;
    private String handlerTimeStr;
    private String beat;
    private Agency agency;
    private Route route;
    private Traveller traveller;

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Traveller getTraveller() {
        return traveller;
    }

    public void setTraveller(Traveller traveller) {
        this.traveller = traveller;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if(orderTime != null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return df.format(orderTime);
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusStr() {
        if(orderStatus == 1){
            return "已处理";
        }else if(orderStatus == 0){
            return "未处理";
        }else{
            return "已取消处理";
        }
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public Date getHandlerTime() {
        return handlerTime;
    }

    public void setHandlerTime(Date handlerTime) {
        this.handlerTime = handlerTime;
    }

    public String getHandlerTimeStr() {
        if(handlerTime != null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return df.format(handlerTime);
        }
        return handlerTimeStr;
    }

    public void setHandlerTimeStr(String handlerTimeStr) {
        this.handlerTimeStr = handlerTimeStr;
    }

    public String getBeat() {
        return beat;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }
}
