package com.fpyrypt.whereisatm;

import com.fpyrypt.whereisatm.service.AtmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WhereisatmApplicationTests {

    @Autowired
    AtmService atmService;

    @Test
    void contextLoads() {
    }

//    @Test
//    void test() {
//        ResponseEntity<?> response = atmService.getAtmDetails(153463);
//
//        System.out.println(response.toString());
//    }
//
//    @Test
//    void test2() {
//        ResponseEntity<?> response = atmService.getNearestAtm("55.66", "37.63", true);
//
//        System.out.println(response.toString());
//    }

}
