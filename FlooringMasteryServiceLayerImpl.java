/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.AuditDao;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.OrderModeDao;
import com.sg.flooringmastery.dao.OrderNumberDao;
import com.sg.flooringmastery.dao.OrderPersistenceException;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.TaxRateDao;
import com.sg.flooringmastery.dto.AppMode;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author paddy
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

    // Declare ordderDao, productDao, etc. objects
    OrderDao orderDao;
    ProductDao productDao;
    TaxRateDao taxRateDao;
    OrderNumberDao orderNumberDao;
    OrderModeDao orderModeDao;
    AuditDao auditDao;
    AppMode ode = new AppMode("t");
    
    // Create class constructor that takes the objects implementations as parameters
    public FlooringMasteryServiceLayerImpl(OrderDao orderDao, ProductDao productDao, TaxRateDao taxRateDao, OrderNumberDao orderNumberDao, OrderModeDao orderModeDao, AuditDao auditDao) {

        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxRateDao = taxRateDao;
        this.orderNumberDao = orderNumberDao;
        this.orderModeDao = orderModeDao;
        this.auditDao = auditDao;

    }

    @Override
    public Order addOrder(Order order) throws OrderPersistenceException {
        
        if (ode.getProduction().equals("p")){
            orderDao.addOrder(order.getOrderNumber(), order);
            orderNumberDao.increaseOrderNumber();

            return order;
        }
        return order;
    }

    @Override
    public Map<String, BigDecimal> pullStates() throws OrderPersistenceException {

        return taxRateDao.pullAvailableStates();

    }

    @Override
    public List<Product> pullProducts() throws OrderPersistenceException {

        return productDao.pullAllProducts();

    }

    @Override
    public Order calculateOrderInfo(Order order, boolean newOrder) throws OrderPersistenceException, InvalidOrderInfoException {

        //Set State and Product to easily comparible states
        String state = order.getState().toUpperCase();
        order.setState(state);

        //Setting format to 'Sentence case' type format
        if (!"".equals(order.getProductType())) {

            String productType = order.getProductType().substring(0, 1).toUpperCase() + order.getProductType().substring(1).toLowerCase();
            order.setProductType(productType);

        }

        //Validate info before proceeding
        validateOrderInfo(order);

        Product product = productDao.findProduct(order.getProductType());

        if (newOrder) {

            int orderNumber = orderNumberDao.findOrderNumber();
            order.setOrderNumber(orderNumber);

        }

        BigDecimal taxRate = taxRateDao.findTaxRate(order.getState());
        order.setTaxRate(taxRate);

        BigDecimal materialCostPerSqFt = product.getMaterialCostPerSqFt();
        order.setMaterialCostPerSqFt(materialCostPerSqFt);

        BigDecimal laborCostPerSqFt = product.getLaborCostPerSqFt();
        order.setLaborCostPerSqFt(laborCostPerSqFt);

        //Calculate total material cost
        BigDecimal materialCost = materialCostPerSqFt.multiply(order.getArea());
        materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);
        order.setMaterialCost(materialCost);

        //Calculate total labor cost
        BigDecimal laborCost = laborCostPerSqFt.multiply(order.getArea());
        laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);
        order.setLaborCost(laborCost);

        //Convert tax rate to percentage and calculate tax
        taxRate = taxRate.divide(new BigDecimal("100"), 3, RoundingMode.FLOOR);
        BigDecimal subTotal = materialCost.add(laborCost);
        BigDecimal tax = subTotal.multiply(taxRate);
        tax = tax.setScale(2, RoundingMode.HALF_UP);
        order.setTax(tax);

        //Calculate total
        BigDecimal total = subTotal.add(tax);
        order.setTotalCost(total);

        return order;

    }

    @Override
    public List<Order> findOrdersByDate(LocalDate date) throws OrderPersistenceException {

        return orderDao.findOrdersByDate(date);

    }

    @Override
    public Order findOrdersByNumber(LocalDate date, int orderNumber) throws OrderPersistenceException {

        return orderDao.findOrderByNumber(date, orderNumber);

    }

    @Override
    public Order editOrder(Order order) throws OrderPersistenceException {

        //validateOrderInfo(order);
        return orderDao.editOrder(order.getOrderNumber(), order);

    }

    @Override
    public Order removeOrder(int orderNumber, Order order) throws OrderPersistenceException {

        Order removedOrder = orderDao.removeOrder(orderNumber, order);

        return removedOrder;

    }

    @Override
    public boolean saveWork() throws OrderPersistenceException {

        String mode = orderModeDao.findOrderMode();

        //Return false if unable to save - Test AppMode
        //Return true if able to save - Prod AppMode
        if ("p".equalsIgnoreCase(mode)) {

            orderDao.saveOrders();
            orderNumberDao.saveOrderNumber();
            auditDao.writeAuditEntry("Orders/Changes have been saved.");
            return true;

        } else {

            auditDao.writeAuditEntry("Orders/Changes have not been saved.");
            return false;

        }

    }

    private void validateOrderInfo(Order order) throws InvalidOrderInfoException, OrderPersistenceException {

        LocalDate dateCutOff = LocalDate.parse("1960-01-01");

        //Check info for general errors
        if (order.getOrderDate() == null
                || order.getCustomerName() == null
                || order.getCustomerName().trim().length() == 0
                || order.getCustomerName().contains(",")
                || order.getState() == null
                || order.getState().trim().length() == 0
                || order.getState().contains(",")
                || order.getProductType() == null
                || order.getProductType().trim().length() == 0
                || order.getProductType().contains(",")
                || order.getArea() == null
                || order.getArea().compareTo(BigDecimal.ZERO) <= 0) {

            throw new InvalidOrderInfoException("ERROR: Fields (Order Date, Customer Name, and Area) are all required."
                    + "Please double check that you use the specified format and that fields do not contain commas.");

        }

        //Verify date is recent enough
        if (order.getOrderDate().isBefore(dateCutOff)) {

            throw new InvalidOrderInfoException("ERROR: Enter a valid date.");

        }

        //Check for invalid state
        if (taxRateDao.findTaxRate(order.getState()) == null) {

            throw new InvalidOrderInfoException("ERROR: The state you entered doesn't match any of the available states."
                    + "Enter a valid state and try again.");

        }

        //Check for invalid product
        if (productDao.findProduct(order.getProductType()) == null) {

            throw new InvalidOrderInfoException("ERROR: The product you entered doesn't match any of the available products."
                    + "Enter a valid product and try again.");

        }

    }
}
