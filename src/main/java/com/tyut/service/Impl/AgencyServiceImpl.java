package com.tyut.service.Impl;

import com.tyut.domain.*;
import com.tyut.mapper.AgencyMapper;
import com.tyut.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;
    private Agency agency;
    @Override
    public Boolean isLogin(Agency agency) {
        Agency agency1 = agencyMapper.selectPassword(agency.getAgencyName());
        if (agency1.getPassword().equals(agency.getPassword())) return true;
        else return false;
    }

    public List<Flight> findAllFlight(){
        return agencyMapper.findAllFlight();
    }

    public List<Flight> findFlightByDepAndFinCity(String depCity,String finCity){
        return agencyMapper.findFlightByDepAndFinCity(depCity,finCity);
    }
    public boolean addFOrder(String aid, String routeId, String tid, String flightId, String beat, double price) throws ParseException {
        //1.修改对应的agencyOrder中的orderStatus 和 handlerTime
        //1.1 生成此时的处理时间handlerTime
        Date handlerTime = new Date();
        SimpleDateFormat df;
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(handlerTime);
        //1.2 对agencyOrder中的orderStatus 和 handlerTime进行修改
        agencyMapper.handlerAOrder(aid, routeId, Integer.parseInt(tid), df.parse(time));
        //2.添加flightOrder的订单
        boolean isOk = agencyMapper.addFOrder(aid, routeId, Integer.parseInt(tid), flightId, beat, price);
        //2.1减少航班对应舱位的座位数
        if("头等舱".equals(beat)){
            //减少对应得头等舱的座位数
            agencyMapper.subFirstBeatCount(flightId);
        }else if("商务舱".equals(beat)){
            //减少商务舱的个数
            agencyMapper.subBusinessBeatCount(flightId);
        }else if("经济舱".equals(beat)){
            //减少对应的经济舱的个数
            agencyMapper.subEconomyBeatCount(flightId);
        }
        return isOk;
    }
}
