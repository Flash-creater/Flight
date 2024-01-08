package com.flight.test;

import com.flight.dao.AgencyDao;
import com.flight.dao.Impl.AgencyDaoImpl;
import com.flight.domain.Route;
import com.flight.domain.Traveller;

import java.util.List;

public class AgencyTest {

    public List<Traveller> findAllTraveller(String aid) throws Exception {
        AgencyDao agDao = new AgencyDaoImpl();

        //根据agencyId查找对应的游客id
        List<Traveller> tids = agDao.findTids(aid);
        System.out.println(tids.size());
        if(tids.size() == 0){
            return null;
        }

        //根据查询到的tid查找对应的traveller的信息
        for(Traveller tid:tids){
            //根据tid和aid查询出对应的路线id
            List<Route> routes = agDao.findRidsByTidAndAid(tid.getId(), aid);
            tid.setRouteList(routes);
            tids.add(tid);
        }
        return tids;
    }

    public static void main(String[] args) throws Exception {
        AgencyTest test1 = new AgencyTest();
        test1.findAllTraveller("01-001");
    }
}
