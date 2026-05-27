package com.codingsrv.AopApplication.services.impl;

import com.codingsrv.AopApplication.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ShipmentServiceImplTest {

    @Autowired
    private ShipmentService shipmentService;

    @Test
    void orderPackage() {
        String orderString = shipmentService.beforeOrderPackage(-4L);
        log.info(orderString);
    }

    @Test
    void trackPackage() {
        shipmentService.trackPackage(4L);
    }
}