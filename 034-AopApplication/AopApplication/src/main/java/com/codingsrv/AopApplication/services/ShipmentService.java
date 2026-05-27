package com.codingsrv.AopApplication.services;

public interface ShipmentService {

    String beforeOrderPackage(Long orderId);

    String trackPackage(Long orderId);
}
