package com.tyut.controller;

import com.tyut.domain.Flight;
import com.tyut.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @RequestMapping("/showAllFlight")
    public String showAllFlight(String flightId,Model model, HttpServletRequest httpServletRequest){
        List<Flight> flights=flightService.findAllFlight();
        model.addAttribute("flights", flights);
        return "showFlight";
    }
    @RequestMapping("/showByDepAndFin")
    public String showByDepAndFin(String departureCity,String finalCity, Model model, HttpServletRequest httpServletRequest){
        List<Flight> flights = flightService.findByDepAndFin(departureCity,finalCity);
        model.addAttribute("flights",flights);
        return ""; //给到需要用出发和终点的城市
    }
}