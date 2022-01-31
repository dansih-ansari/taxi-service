package com.hobby.taxisvc.service;

import com.hobby.taxisvc.domain.Taxi;
import org.springframework.stereotype.Service;

@Service
public class TaxiService {
    public Taxi register(Taxi taxi) {
        return taxi;
    }
}
