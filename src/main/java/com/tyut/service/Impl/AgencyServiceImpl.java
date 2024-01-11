package com.tyut.service.Impl;

import com.tyut.domain.Agency;
import com.tyut.mapper.AgencyMapper;
import com.tyut.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyServiceImpl implements AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;
    @Override
    public Boolean isLogin(Agency agency) {
        Agency agency1 = agencyMapper.selectPassword(agency.getAgencyName());
        if (agency1.getPassword().equals(agency.getPassword())) return true;
        else return false;
    }
}
