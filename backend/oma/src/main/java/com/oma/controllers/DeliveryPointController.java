package com.oma.controllers;

import com.oma.services.DeliveryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("delivery-points")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class DeliveryPointController {

    @Autowired
    DeliveryPointService deliveryPointService;

}
