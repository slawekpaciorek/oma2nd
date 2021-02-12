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
        return deliveryPointService.getAllDeliveryPoints();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void addDeliveryPoint(@RequestBody DeliveryPoint deliveryPoint){
        deliveryPointService.saveDeliveryPoint(deliveryPoint);
    }

    @PutMapping(value = "/update", consumes = "application/json")
    public void updateDeliveryPoint(@RequestParam("id") String id, @RequestBody DeliveryPoint deliveryPoint){
        deliveryPointService.updateDeliveryPoint(Long.parseLong(id), deliveryPoint);
    }

    @DeleteMapping(value = "/remove")
    public void removeDeliveryPoint(@RequestParam String id){
        deliveryPointService.removeDeliveryPoint(Long.parseLong(id));
    }

}
