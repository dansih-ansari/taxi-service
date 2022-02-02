package com.hobby.taxisvc.controller;


import com.hobby.taxisvc.domain.Taxi;
import com.hobby.taxisvc.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/taxi/")
public class TaxiController {

    @Autowired
    TaxiService service;

    @PostMapping("/register")
    public Taxi register(@RequestBody @Valid Taxi taxi) {
        var registerTaxi =  service.register(taxi);
        return registerTaxi;
    }
}
