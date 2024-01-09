package com.msaproject.services;

import com.msaproject.models.Orders;
import com.msaproject.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    }

    public void updateOrder(Orders order){
        ordersRepository.save(order);
    }
}
