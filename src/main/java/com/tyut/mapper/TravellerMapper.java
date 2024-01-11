package com.tyut.mapper;


import com.tyut.domain.Traveller;

public interface TravellerMapper {
    public Traveller selectPassword(String email);
    public int addTraveller(Traveller traveller);
}
