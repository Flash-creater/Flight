package com.tyut.service;

import com.tyut.domain.Traveller;

public interface TravellerService {
    public Boolean isLogin(Traveller traveller);
    public int addTraveller(Traveller traveller);
}
