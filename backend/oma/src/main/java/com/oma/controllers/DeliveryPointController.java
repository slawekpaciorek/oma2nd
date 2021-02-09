package com.oma.controllers;

import com.oma.model.DeliveryPoint;
import com.oma.services.DeliveryPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery-points")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class DeliveryPointController {

    @Autowired
    DeliveryPointService deliveryPointService;

    @GetMapping("/list")
    public List<DeliveryPoint> displayAllDeliveryPoints(){
        return null;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void addDeliveryPoint(@RequestBody DeliveryPoint deliveryPoint){

    }

    @PutMapping(value = "/update", consumes = "application/json")
    public void updateDeliveryPoint(@RequestParam String id, @RequestBody DeliveryPoint deliveryPoint){

    }

    @DeleteMapping(value = "/remove")
    public void removeDeliveryPoint(@RequestParam String id){

    }

}
