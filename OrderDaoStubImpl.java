/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paddy
 */
public class OrderDaoStubImpl implements OrderDao {

    //declare order
    private Order onlyOrder;
    //declare arraylist to store orders
    private List<Order> orderList = new ArrayList<>();
    //format of files
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    // stubb class constructor
    public OrderDaoStubImpl() {
        //create new order and add them to the orderlist
        onlyOrder = new Order();
        onlyOrder.setOrderNumber(100);
        onlyOrder.setOrderDate(LocalDate.parse("03/14/2018", formatter));
        onlyOrder.setCustomerName("Paddy");
        onlyOrder.setState("MN");
        onlyOrder.setTaxRate(new BigDecimal("6.00"));
        onlyOrder.setProductType("Marble");
        onlyOrder.setArea(new BigDecimal("35"));
        onlyOrder.setMaterialCostPerSqFt(new BigDecimal("7.05"));
        onlyOrder.setLaborCostPerSqFt(new BigDecimal("5.00"));
        onlyOrder.setMaterialCost(new BigDecimal("245.75"));
        onlyOrder.setLaborCost(new BigDecimal("200.00"));
        onlyOrder.setTax(new BigDecimal("31.20"));
        onlyOrder.setTotalCost(new BigDecimal("678.12"));

        orderList.add(onlyOrder);

    }

    @Override
    public Order addOrder(int orderNumber, Order order) throws OrderPersistenceException {

        if (orderNumber == onlyOrder.getOrderNumber()) {

            return onlyOrder;

        } else {

            return null;

        }

    }

    @Override
    public List<Order> findOrdersByDate(LocalDate date) throws OrderPersistenceException {

        if (date.equals(onlyOrder.getOrderDate())) {

            return orderList;

        } else {

            return null;

        }

    }

    @Override
    public Order findOrderByNumber(LocalDate date, int orderNumber) throws OrderPersistenceException {

        if (date.equals(onlyOrder.getOrderDate()) && orderNumber == onlyOrder.getOrderNumber()) {

            return onlyOrder;

        } else {

            return null;

        }

    }

    @Override
    public Order editOrder(int orderNumber, Order order) throws OrderPersistenceException {

        if (orderNumber == onlyOrder.getOrderNumber()) {

            return onlyOrder;

        } else {

            return null;

        }

    }

    @Override
    public Order removeOrder(int orderNumber, Order order) throws OrderPersistenceException {

        if (orderNumber == onlyOrder.getOrderNumber()) {

            return onlyOrder;

        } else {

            return null;

        }

    }

    @Override
    public void saveOrders() throws OrderPersistenceException {

        //Nothing to return
    }
}
