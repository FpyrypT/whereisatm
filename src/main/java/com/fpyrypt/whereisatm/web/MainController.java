package com.fpyrypt.whereisatm.web;

import com.fpyrypt.whereisatm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atms")
public class MainController {

    @Autowired
    AtmService atmService;

    @GetMapping("/{deviceId}")
    public ResponseEntity<?> getDeviceId(@PathVariable Integer deviceId) {
        return atmService.getAtmDetails(deviceId);
    }

    @GetMapping("/nearest")
    public ResponseEntity<?> getNearestAtm(String latitude, String longitude, Boolean payments) {
        return atmService.getNearestAtm(latitude, longitude, payments);
    }

}
