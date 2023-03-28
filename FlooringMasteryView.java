/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author paddy
 */
public class FlooringMasteryView {

    private UserIO io;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public FlooringMasteryView(UserIO io) {

        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("<< Flooring Program >>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Order getNewOrderInfo(Map<String, BigDecimal> statesMap, List<Product> productsList) {

        boolean validEntry = false;
        LocalDate date = null;
        String customerName = null;
        String state = null;
        String product = null;
        BigDecimal area = BigDecimal.ZERO;

        while (!validEntry) {

            try {

                date = LocalDate.parse(io.readString("Enter the order date (MM/dd/yyyy)."), formatter);
                validEntry = true;
            } catch (DateTimeParseException e) {

                io.print("Invalid format. Try again.");
                validEntry = false;
            }
        }

        customerName = io.readString("Enter your name.");

        //Get State
        io.print("Available States");

        for (String key : statesMap.keySet()) {

            io.print(key);
        }

        state = io.readString("Enter one of the states listed.");

        //Get the flooring selection
        io.print("Flooring Options");

        for (Product currentProduct : productsList) {

            io.print(currentProduct.getProductType() + " | Cost/Sq.Ft. = $" + currentProduct.getMaterialCostPerSqFt()
                    + " | Labor Cost/Sq. Ft. = $" + currentProduct.getLaborCostPerSqFt());
        }

        product = io.readString("Enter one of the products available.");

        //Get the area
        validEntry = false;

        while (!validEntry) {

            try {

                area = new BigDecimal(io.readString("Enter the square footage (Sq.Ft.)"));
                area = area.setScale(0, RoundingMode.CEILING);

                if (area.compareTo(BigDecimal.ZERO) > 0) {

                    validEntry = true;

                } else {

                    io.print("Enter a number greater than 0.");
                    validEntry = false;
                }
            } catch (NumberFormatException e) {

                io.print("Formatting error, re-enter a number.");
                validEntry = false;
            }
        }

        Order currentOrder = new Order();
        currentOrder.setOrderDate(date);
        currentOrder.setCustomerName(customerName);
        currentOrder.setState(state);
        currentOrder.setProductType(product);
        currentOrder.setArea(area);

        return currentOrder;

    }

    public boolean getOrderConfirmation(Order order) {

        io.print("<< New Order Information >>");
        io.print("Order Number: " + order.getOrderNumber());
        io.print("Order Date: " + order.getOrderDate().format(formatter));
        io.print("Customer Name: " + order.getCustomerName());
        io.print("State: " + order.getState());
        io.print("Tax Rate: " + order.getTaxRate() + "%");
        io.print("Product: " + order.getProductType());
        io.print("Area: " + order.getArea() + " sq ft");
        io.print("Material Cost/Sq Ft: $" + order.getMaterialCostPerSqFt());
        io.print("Labor Cost/Sq Ft: $" + order.getLaborCostPerSqFt());
        io.print("Material Cost: $" + order.getMaterialCost());
        io.print("Labor Cost: $" + order.getLaborCost());
        io.print("Tax: $" + order.getTax());
        io.print("Total: $" + order.getTotalCost());

        String confirmOrder = io.readString("Does the above order information look correct? (y/n)");

        if ("y".equalsIgnoreCase(confirmOrder)) {

            return true;

        } else {

            return false;

        }

    }

    public void displayAddOrderBanner() {
        io.print("<< Create New Order >>");
    }

    public void displayAddOrderSuccessBanner() {
        io.readString("The order has been successfully created. Hit enter to continue.");
    }

    public void displayOrderRestartBanner() {
        io.readString("Re-enter the information again.");
    }

    public void displayAddCancelledBanner() {
        io.readString("The order has been cancelled. Hit enter to continue.");
    }

    public void displayOrderDisplayBanner() {
        io.readString("<< Display Order >>");
    }

    public LocalDate getOrderDate() {

        boolean validEntry = false;
        LocalDate date = null;

        while (!validEntry) {

            try {

                do {

                    date = LocalDate.parse(io.readString("Enter the order date (MM/dd/yyyy)."), formatter);
                    validEntry = true;

                } while (date == null || date.toString().length() == 0);

            } catch (DateTimeParseException e) {

                io.print("Enter the date in the correct format.");
                validEntry = false;

            }

        }

        return date;

    }

    public int getOrderNumber() {

        boolean validEntry = false;
        int orderNumber = 0;

        while (!validEntry) {

            try {

                orderNumber = io.readInt("Enter the order number.");
                validEntry = true;

            } catch (NumberFormatException e) {

                io.print("Make sure you've entered a proper order number.");
                validEntry = false;

            }

        }

        return orderNumber;

    }

    public void displayOrders(List<Order> orderList, LocalDate date) {

        if (!orderList.isEmpty() && orderList != null) {

            for (Order currentOrder : orderList) {

                if (currentOrder.getOrderDate().equals(date)) {

                    io.print("\nOrder Number: " + currentOrder.getOrderNumber());
                    io.print("Order Date: " + currentOrder.getOrderDate().format(formatter));
                    io.print("Customer Name: " + currentOrder.getCustomerName());
                    io.print("State: " + currentOrder.getState());
                    io.print("Tax Rate: " + currentOrder.getTaxRate() + "%");
                    io.print("Product: " + currentOrder.getProductType());
                    io.print("Area: " + currentOrder.getArea() + " sq ft");
                    io.print("Material Cost/Sq Ft: $" + currentOrder.getMaterialCostPerSqFt());
                    io.print("Labor Cost/Sq Ft: $" + currentOrder.getLaborCostPerSqFt());
                    io.print("Material Cost: $" + currentOrder.getMaterialCost());
                    io.print("Labor Cost: $" + currentOrder.getLaborCost());
                    io.print("Tax: $" + currentOrder.getTax());
                    io.print("Total: $" + currentOrder.getTotalCost());

                }

            }

        } else {

            io.print("There are no orders from that date.");

        }

        io.readString("Hit enter to continue.");

    }

    public void displayEditOrderBanner() {

        io.print("<< Edit Order >>");

    }

    public Order editOrderInfo(Order order, Map<String, BigDecimal> statesMap, List<Product> productsList) {

        if (order != null) {

            boolean validEntry = false;

            LocalDate date = order.getOrderDate();
            String userDateInput = "";
            LocalDate newDate = null;

            String customerName = order.getCustomerName();
            String newCustomerName = null;

            String state = order.getState();
            String newState = null;

            String product = order.getProductType();
            String newProduct = null;

            BigDecimal area = order.getArea();
            String userAreaInput = "";
            BigDecimal newArea = null;

            //Get Date
            while (!validEntry) {

                io.print("Current Date: " + date);
                userDateInput = io.readString("Enter the new order date (MM/dd/yyyy) or hit enter to skip.");

                if ("".equalsIgnoreCase(userDateInput)) {

                    //If empty, user didn't put anything new in - old date will be kept.
                    validEntry = true;

                } else {

                    try {

                        newDate = LocalDate.parse(userDateInput, formatter);
                        validEntry = true;

                    } catch (DateTimeParseException e) {

                        io.print("Make sure you are entering the correct format.");
                        validEntry = false;

                    }

                }

            }

            //If newDate was changed/passed above, will replace old date.
            if (newDate != null) {

                order.setOrderDate(newDate);

            }

            //Get customer name
            io.print("Current Name: " + customerName);
            newCustomerName = io.readString("Enter the new customer name or hit enter to skip.");

            if (newCustomerName != null && !"".equals(newCustomerName)) {

                order.setCustomerName(newCustomerName);

            }

            //Get the state
            io.print("Current State: " + state);
            io.print("Available States");

            for (String key : statesMap.keySet()) {

                io.print(key);

            }

            newState = io.readString("Enter the new state from those available or hit enter to skip.");

            if (newState != null && !"".equals(newState)) {

                order.setState(newState);

            }

            //Get the flooring selection
            io.print("Current Product: " + product);
            io.print("Flooring Options");

            for (Product currentProduct : productsList) {

                io.print(currentProduct.getProductType() + " | Cost/Sq. Ft. = $" + currentProduct.getMaterialCostPerSqFt()
                        + " | Labor Cost/Sq. Ft. = $" + currentProduct.getLaborCostPerSqFt());

            }

            newProduct = io.readString("Enter the new product from those available or hit enter to skip.");

            if (newProduct != null && !"".equals(newProduct)) {

                order.setProductType(newProduct);

            }

            //Get Area
            validEntry = false;

            while (!validEntry) {

                io.print("Current Area: " + area + " sq ft");
                userAreaInput = io.readString("Enter the new square footage or hit enter to skip.");

                if ("".equalsIgnoreCase(userAreaInput)) {

                    //If empty, nothing new was entered = old area will be kept.
                    validEntry = true;

                } else {

                    try {

                        newArea = new BigDecimal(userAreaInput);
                        newArea = newArea.setScale(0, RoundingMode.CEILING);

                        if (newArea.compareTo(BigDecimal.ZERO) > 0) {

                            validEntry = true;

                        } else {

                            io.print("Enter a number greater than 0.");
                            validEntry = false;

                        }

                    } catch (NumberFormatException e) {

                        io.print("Formatting error, enter a number value.");
                        validEntry = false;

                    }

                }

            }

            if (newArea != null) {

                order.setArea(newArea);

            }

            return order;

        } else {

            return null;

        }

    }

    public void displayEditedOrderInfo(Order order) {

        io.print("<< Updated Information for Order " + order.getOrderNumber() + " >>");
        io.print("Order Number:         " + order.getOrderNumber());
        io.print("Order Date:           " + order.getOrderDate().format(formatter));
        io.print("Customer Name:        " + order.getCustomerName());
        io.print("State:                " + order.getState());
        io.print("Tax Rate:             " + order.getTaxRate() + "%");
        io.print("Product:              " + order.getProductType());
        io.print("Area:                 " + order.getArea() + " sq ft");
        io.print("Material Cost/Sq Ft: $" + order.getMaterialCostPerSqFt());
        io.print("Labor Cost/Sq Ft:    $" + order.getLaborCostPerSqFt());
        io.print("Material Cost:       $" + order.getMaterialCost());
        io.print("Labor Cost:          $" + order.getLaborCost());
        io.print("Tax:                 $" + order.getTax());
        io.print("Total:               $" + order.getTotalCost());

        io.readString("The order has been updated. Hit enter to continue.");

    }

    public void displayEditCancelledBanner() {

        io.readString("Editing has been cancelled. Hit enter to continue.");

    }

    public void displayNullOrderBanner() {

        io.readString("There doesn't appear to be an order with that order number/date. Hit enter to continue.");

    }

    public void displayRemoveOrderBanner() {

        io.print("<< Remove Order >>");

    }

    public boolean displayRemoveOrderConfirmation(Order order) {

        if (order != null) {

            io.print("Order Number:         " + order.getOrderNumber());
            io.print("Order Date:           " + order.getOrderDate().format(formatter));
            io.print("Customer Name:        " + order.getCustomerName());
            io.print("State:                " + order.getState());
            io.print("Tax Rate:             " + order.getTaxRate() + "%");
            io.print("Product:              " + order.getProductType());
            io.print("Area:                 " + order.getArea() + " sq ft");
            io.print("Material Cost/Sq Ft: $" + order.getMaterialCostPerSqFt());
            io.print("Labor Cost/Sq Ft:    $" + order.getLaborCostPerSqFt());
            io.print("Material Cost:       $" + order.getMaterialCost());
            io.print("Labor Cost:          $" + order.getLaborCost());
            io.print("Tax:                 $" + order.getTax());
            io.print("Total:               $" + order.getTotalCost());

            String confirmRemove = io.readString("Would you like to remove this order? (y/n) ");

            if ("y".equalsIgnoreCase(confirmRemove)) {

                return true;

            } else {

                return false;

            }

        } else {

            io.print("There doesn't appear to be an order.");
            return false;

        }

    }

    public void displayRemoveOrderSuccessBanner() {

        io.readString("The order has been marked for deletion. Please hit enter to continue.");

    }

    public void displayRemoveOrderCancelledBanner() {

        io.readString("The order has been kept. Hit enter to continue.");

    }

    public void displaySaveSuccessfulBanner() {

        io.readString("The orders have been updated. Hit enter to continue.");

    }

    public void displaySaveDisabledBanner() {

        io.readString("Currently running TEST MODE. Saving has been disabled for this mode.");

    }

    public boolean displaySaveWorkConfirmation() {

        String saveWork = io.readString("Do you want to save your changes? (y/n)");

        if ("y".equalsIgnoreCase(saveWork)) {

            return true;

        } else {

            return false;

        }

    }

    public void displaySaveCancelledBanner() {

        io.readString("Changes have not been saved. Hit enter to continue.");

    }

    public void displayUnknownCommandBanner() {

        io.print("<< Unknown Command >>");

    }

    public void displayErrorMessage(String errorMsg) {

        io.print("<< ERROR >>");
        io.print(errorMsg);

    }

    public void displayExitBanner() {

        io.print("Thank you!");
    }
}
