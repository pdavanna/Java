/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.InvalidOrderInfoException;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author paddy
 */
public class FlooringMasteryController {

    // Declare service and view objects
    FlooringMasteryView view;
    FlooringMasteryServiceLayer service;

    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {

        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            
            while (keepGoing) {
                
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveWork();
                    break;
                case 6:
                    saveAndExit();
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }
    catch (OrderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
    }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void displayOrders() throws OrderPersistenceException {
        
        view.displayOrderDisplayBanner();
        LocalDate date = view.getOrderDate();
        List<Order> orderList = service.findOrdersByDate(date);
        view.displayOrders(orderList, date);
        
    }
    
    private void addOrder() throws OrderPersistenceException {
        
        view.displayAddOrderBanner();
        boolean hasErrors = false;
        boolean confirmOrder = false;
        
        Map<String, BigDecimal> statesMap = service.pullStates();
        List<Product> productsList = service.pullProducts();
        
        do {
            
            Order newOrder = view.getNewOrderInfo(statesMap, productsList);
            
            //Setting boolean to true - new order needs order number
            try {
            
                newOrder = service.calculateOrderInfo(newOrder, true);
                confirmOrder = view.getOrderConfirmation(newOrder);
                hasErrors = false;
                
            } catch (InvalidOrderInfoException e) {

                hasErrors = true;
                view.displayErrorMessage(e.getMessage());

            }
            
            if (confirmOrder) {
            
                service.addOrder(newOrder);
                view.displayAddOrderSuccessBanner();
                hasErrors = false;
                
            } else if (hasErrors) {
                
                view.displayOrderRestartBanner();
                
            } else {
                
                view.displayAddCancelledBanner();
                
            }
            
        } while (hasErrors);
        
    }
    
    private void editOrder() throws OrderPersistenceException {
        
        boolean hasErrors = false;
        
        view.displayEditOrderBanner();
        
        LocalDate date = view.getOrderDate();
        int orderNumber = view.getOrderNumber();
        Order order = service.findOrdersByNumber(date, orderNumber);
        Map<String, BigDecimal> statesMap = service.pullStates();
        List<Product> productsList = service.pullProducts();
        
        if (order != null) {
        
            Order editedOrder = new Order(order);
            editedOrder = view.editOrderInfo(editedOrder, statesMap, productsList);

            //Setting boolean to false as it's not a new order - doesn't need a new order number    
            try {
 
                editedOrder = service.calculateOrderInfo(editedOrder, false);

            } catch (InvalidOrderInfoException e) {

                hasErrors = true;
                view.displayErrorMessage(e.getMessage());

            }

            if (!hasErrors) {

                //remove old order information - will be replaced after edit
                service.removeOrder(orderNumber, order);

                service.editOrder(editedOrder);
                view.displayEditedOrderInfo(editedOrder);

            } else {

                view.displayEditCancelledBanner();

            }
            
        } else {
            
            view.displayNullOrderBanner();
            
        }
        
    }
    
    private void removeOrder() throws OrderPersistenceException {
        
        view.displayRemoveOrderBanner();
        LocalDate date = view.getOrderDate();
        int orderNumber = view.getOrderNumber();
        Order order = service.findOrdersByNumber(date, orderNumber);
        boolean removeOrder = view.displayRemoveOrderConfirmation(order);
        
        if (removeOrder) {
            
            service.removeOrder(orderNumber, order);
            view.displayRemoveOrderSuccessBanner();
            
        } else {
            
            view.displayRemoveOrderCancelledBanner();
            
        }
        
    }
    
    private void saveWork() throws OrderPersistenceException {
            
        boolean workSaved = service.saveWork();

        if (workSaved) {

            view.displaySaveSuccessfulBanner();

        } else {

            view.displaySaveDisabledBanner();

        }
        
    }
    
    private void saveAndExit() throws OrderPersistenceException {
        
        boolean saveChanges = view.displaySaveWorkConfirmation();
        
        if (saveChanges) {
            
            saveWork();
            
        } else {
            
            view.displaySaveCancelledBanner();
            
        }
        
    }

    private void unknownCommand() {

        view.displayUnknownCommandBanner();
        
    }
    
    private void exitMessage() {
        
        view.displayExitBanner();
        
    }

}