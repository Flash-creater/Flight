package com.tyut.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/*
* 飞机的航班订单 信息
* */
public class FlightOrder implements Serializable {
    private Integer id; //主键
    private Integer tid; //旅客id
    private String aid;//旅行社id
    private String flightId;//飞机票的id
    private String routeId;//路线id

    private Integer payStatus;//支付状态
    private String payStatusStr;
    private double orderPrice;//订单价格
    private String beat;//舱位登记
    //支付时间
    private Date payTime;
    private String payTimeStr;
    //存储订单信息
    private Flight flight;
    private Traveller traveller;
    private Agency agency;
    private Route route;
    private AgencyOrder agencyOrder;

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayTimeStr() {
        if(payTime != null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return df.format(payTime);
        }
        return payTimeStr;
    }

    public void setPayTimeStr(String payTimeStr) {
        this.payTimeStr = payTimeStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusStr() {
        if(payStatus == 0){
            return "未支付";
        }else {
            return "已支付";
        }
    }

    public void setPayStatusStr(String payStatusStr) {
        this.payStatusStr = payStatusStr;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getBeat() {
        return beat;
    }

    public void setBeat(String beat) {
        this.beat = beat;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Traveller getTraveller() {
        return traveller;
    }

    public void setTraveller(Traveller traveller) {
        this.traveller = traveller;
    }

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

    public AgencyOrder getAgencyOrder() {
        return agencyOrder;
    }

    public void setAgencyOrder(AgencyOrder agencyOrder) {
        this.agencyOrder = agencyOrder;
    }

    @Override
    public String toString() {
        return "FlightOrder{" +
                "id=" + id +
                ", tid=" + tid +
                ", aid='" + aid + '\'' +
                ", flightId='" + flightId + '\'' +
                ", routeId='" + routeId + '\'' +
                ", payStatus=" + payStatus +
                ", payStatusStr='" + payStatusStr + '\'' +
                ", orderPrice=" + orderPrice +
                ", beat='" + beat + '\'' +
                ", payTime=" + payTime +
                ", payTimeStr='" + payTimeStr + '\'' +
                ", flight=" + flight +
                ", traveller=" + traveller +
                ", agency=" + agency +
                ", route=" + route +
                ", agencyOrder=" + agencyOrder +
                '}';
    }
}
