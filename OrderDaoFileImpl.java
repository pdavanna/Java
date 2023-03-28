/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author paddy
 */
public class OrderDaoFileImpl implements OrderDao {

    // Instantiate HashMap to link order numbers to a particular day
    private Map<Integer, Order> orders = new HashMap<>();
    private Map<Integer, Order> removeOrders = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

    public static final String DELIMITER = "::";

    /*File directory = new File("orders");
    File[] listOfFiles = directory.listFiles();*/
    private final String b = "orders";
    
    private void loadOrder() throws OrderPersistenceException, NullPointerException {
        
        File directory = new File(b);
        File[] listOfFiles = directory.listFiles();
        
        for (File file : listOfFiles) {

            //if (("Orders_" + date.format(formatter).toString() + ".txt").equals(file.getName())) {
            Scanner scanner;

            try {

                scanner = new Scanner(new BufferedReader(new FileReader(file)));

            } catch (FileNotFoundException e) {

                //Need to not end application if file not found
                throw new OrderPersistenceException(" - Could not load orders into memory.", e);

            }

            String currentLine;
            String[] currentTokens;

            while (scanner.hasNextLine()) {
                // holds the most recent line read from the file
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMITER);

                Order currentOrder = new Order();
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
                currentOrder.setOrderDate(LocalDate.parse(currentTokens[1]));
                currentOrder.setCustomerName(currentTokens[2]);
                currentOrder.setState(currentTokens[3]);
                currentOrder.setTaxRate(new BigDecimal(currentTokens[4]));
                currentOrder.setProductType(currentTokens[5]);
                currentOrder.setArea(new BigDecimal(currentTokens[6]));
                currentOrder.setMaterialCostPerSqFt(new BigDecimal(currentTokens[7]));
                currentOrder.setLaborCostPerSqFt(new BigDecimal(currentTokens[8]));
                currentOrder.setMaterialCost(new BigDecimal(currentTokens[9]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[10]));
                currentOrder.setTax(new BigDecimal(currentTokens[11]));
                currentOrder.setTotalCost(new BigDecimal(currentTokens[12]));
                // put currentOrder into the map using OrderNumber as the key
                orders.put(currentOrder.getOrderNumber(), currentOrder);

            }

            scanner.close();

            //}
        }

    }

    private void writeOrder(LocalDate date) throws OrderPersistenceException {

        PrintWriter out;

        File order = new File("orders", "Orders_" + date.format(formatter).toString() + ".txt");

        try {

            out = new PrintWriter(new FileWriter(order));

        } catch (IOException e) {

            throw new OrderPersistenceException("Could not save new order data.", e);
        }

        List<Order> orderList = this.findOrdersByDate(date);

        for (Order currentOrder : orderList) {

            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getOrderDate() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getMaterialCostPerSqFt() + DELIMITER
                    + currentOrder.getLaborCostPerSqFt() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getTax() + DELIMITER
                    + currentOrder.getTotalCost());

            out.flush();

        }

        //Iterate through directory of files AFTER entries have been added/removed above
        //If file is empty, it gets removed to keep directory as clean as possible
        if (order.length() == 0) {

            order.delete();

        }

        out.close();

    }

    @Override
    public Order addOrder(int orderNumber, Order order) throws OrderPersistenceException {

        Order newOrder = orders.put(orderNumber, order);
        return newOrder;

    }

    @Override
    public List<Order> findOrdersByDate(LocalDate date) throws OrderPersistenceException, NullPointerException {

        //Only load orders if they haven't been loaded yet - empty maps
        if (orders.isEmpty() && removeOrders.isEmpty()) {

            loadOrder();

        }

        return orders.values()
                .stream()
                .filter(o -> o.getOrderDate().equals(date))
                .collect(Collectors.toList());

    }

    @Override
    public Order findOrderByNumber(LocalDate date, int orderNumber) throws OrderPersistenceException {

        //Only load orders if they haven't been loaded yet - empty maps
        if (orders.isEmpty() && removeOrders.isEmpty()) {

            loadOrder();

        }

        return orders.values()
                .stream()
                .filter(o -> o.getOrderDate().equals(date))
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findFirst()
                .orElse(null);

    }

    @Override
    public Order editOrder(int orderNumber, Order order) throws OrderPersistenceException {

        //Only load orders if they haven't been loaded yet - empty maps
        if (orders.isEmpty() && removeOrders.isEmpty()) {

            loadOrder();

        }

        Order editedOrder = orders.put(orderNumber, order);
        return editedOrder;

    }

    @Override
    public Order removeOrder(int orderNumber, Order order) throws OrderPersistenceException {

        removeOrders.put(orderNumber, order);
        Order removedOrder = orders.remove(orderNumber);

        return removedOrder;

    }

    @Override
    public void saveOrders() throws OrderPersistenceException {

        //Iterate through the orders Map, grab order dates to write to file
        for (Map.Entry<Integer, Order> entry : orders.entrySet()) {

            Order order = entry.getValue();
            writeOrder(order.getOrderDate());

        }

        /*//Iterate through the removed orders Map, grab order dates to write to file
        for (Map.Entry<Integer, Order> entry : removeOrders.entrySet()) {

            Order removed = entry.getValue();
            removeOrders.remove(removed.getOrderNumber());
            writeOrder(removed.getOrderDate());

        }*/

    }

}
