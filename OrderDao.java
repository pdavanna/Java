/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author paddy
 */
public interface OrderDao {
    
    Order addOrder(int orderNumber, Order order) throws OrderPersistenceException;
    
    List<Order> findOrdersByDate(LocalDate date) throws OrderPersistenceException;
    
    Order findOrderByNumber(LocalDate date, int orderNumber) throws OrderPersistenceException;
    
    Order editOrder(int orderNumber, Order order) throws OrderPersistenceException;
    
    Order removeOrder(int orderNumber, Order order) throws OrderPersistenceException;
    
    void saveOrders() throws OrderPersistenceException;
}
