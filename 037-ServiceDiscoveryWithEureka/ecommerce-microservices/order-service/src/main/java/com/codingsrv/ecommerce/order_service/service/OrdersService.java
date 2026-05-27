package com.codingsrv.ecommerce.order_service.service;

import com.codingsrv.ecommerce.order_service.dto.OrderRequestDto;
import com.codingsrv.ecommerce.order_service.entity.Orders;
import com.codingsrv.ecommerce.order_service.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    public List<OrderRequestDto> getAllOrders(){
        log.info("fetching all orders");
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(orders1 -> modelMapper.map(orders1, OrderRequestDto.class))
                .toList();
    }

    public OrderRequestDto getOrderById(Long id){
        log.info("fetching the order with id:{}",id);
        Orders order = ordersRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderRequestDto.class);
    }



}
