package com.tyut.service.Impl;

import com.tyut.domain.Admin;
import com.tyut.mapper.AdminMapper;
import com.tyut.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Boolean isLogin(Admin admin) {
        Admin admin_1 = adminMapper.selectPassword(admin.getName());
        if(admin.getPassword().equals(admin_1.getPassword()) ) return true;
        else return false;
    }
}
