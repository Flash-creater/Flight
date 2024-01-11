package com.tyut.service.Impl;

import com.tyut.domain.Traveller;
import com.tyut.mapper.TravellerMapper;
import com.tyut.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravellerServiceImpl implements TravellerService {
    @Autowired
    private TravellerMapper travellerMapper;
    @Override
    public Boolean isLogin(Traveller traveller) {
        Traveller traveller1 = travellerMapper.selectPassword(traveller.getEmail());
        if (traveller1.getPassword().equals(traveller.getPassword())) return true;
        else return false;

    }

    @Override
    public int addTraveller(Traveller traveller) {
        return travellerMapper.addTraveller(traveller);
    }
}
