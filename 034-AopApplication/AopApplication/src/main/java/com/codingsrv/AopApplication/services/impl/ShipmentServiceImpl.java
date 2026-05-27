package com.codingsrv.AopApplication.services.impl;

import com.codingsrv.AopApplication.aspect.MyLogging;
import com.codingsrv.AopApplication.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Override
    @MyLogging // this is a custom annotation which I have created in MyLogging class
    public String beforeOrderPackage(Long orderId) {  // join points

        try {
            log.info("processing the order...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("error occurred while processing the order",e);
        }
        return "order has been processed successfully: "+orderId;
    }

    @Override
    @Transactional
    public String trackPackage(Long orderId) {  // join points

        try {
            log.info("tracking the order...");
            Thread.sleep(500);
            throw new RuntimeException("exception occurred during trackPackage");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
