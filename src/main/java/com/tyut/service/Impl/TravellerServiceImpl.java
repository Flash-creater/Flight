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
        Traveller traveller1 = travellerMapper.selectPassword(traveller);
        if (traveller1 != null ){
            if (traveller1.getPassword().equals(traveller.getPassword())) return true;
            else return false;
        }
        else {
            return false;
        }

    }

    @Override
    public int addTraveller(Traveller traveller) {
        return travellerMapper.addTraveller(traveller);
    }

    @Override
    public Traveller findByEmail(String email) {
        return travellerMapper.findByEmail(email);
    }

    @Override
    public Traveller checkPassword(String id, String password) {
        return travellerMapper.checkPassword(id,password);
    }

    @Override
    public boolean modifyPassword(String id, String newPassword) {
        return travellerMapper.modifyPassword(Integer.parseInt(id),newPassword);
    }

}
