/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author paddy
 */
public class FlooringMasteryServiceLayerTest {
    
    private FlooringMasteryServiceLayer service;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    public FlooringMasteryServiceLayerTest() {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {
        
        Order order = new Order();
        order.setOrderNumber(200);
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        order.setMaterialCostPerSqFt(new BigDecimal("2.25"));
        order.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order.setMaterialCost(new BigDecimal("112.50"));
        order.setLaborCost(new BigDecimal("105.00"));
        order.setTax(new BigDecimal("13.49"));
        order.setTotalCost(new BigDecimal("230.99"));
        
        service.addOrder(order);
        
    }
    
    /**
     * Test of pullStates method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testPullStates() throws Exception {
        
        assertEquals(4, service.pullStates().size());
        
    }
    
    /**
     * Test of pullProducts method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testPullProducts() throws Exception {
        
        assertEquals(4, service.pullProducts().size());
        
    }

    /**
     * Test of calculateOrderInfo method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCalculateNewOrderInfo() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("OH");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        order = service.calculateOrderInfo(order, true);
        
        assertNotEquals(0, order.getOrderNumber());
        assertEquals(new BigDecimal("6.20"), order.getTaxRate());
        assertEquals(new BigDecimal("2.30"), order.getMaterialCostPerSqFt());
        assertEquals(new BigDecimal("2.25"), order.getLaborCostPerSqFt());
        assertEquals(new BigDecimal("115.00"), order.getMaterialCost());
        assertEquals(new BigDecimal("112.50"), order.getLaborCost());
        assertEquals(new BigDecimal("14.11"), order.getTax());
        assertEquals(new BigDecimal("241.61"), order.getTotalCost());
        
    }
    
    @Test
    public void testCalculateOrderEmptyCustomerName() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("");
        order.setState("OH");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderCommaPresentCustomerName() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("Name,");
        order.setState("OH");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderZeroArea() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("OH");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("0"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInfoException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderNegativeArea() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("OH");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("-50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInfoException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderInvalidState() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("Denial");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderEmptyState() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderCommaInState() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("I,N");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderInvalidProduct() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("IN");
        order.setProductType("concrete");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderEmptyProduct() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("IN");
        order.setProductType("");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderCommaInProduct() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("IN");
        order.setProductType("Com,ma");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateOrderDateOutOfBounds() throws Exception {
        
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("01/01/1800", formatter));
        order.setCustomerName("");
        order.setState("OH");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        try {
            
            order = service.calculateOrderInfo(order, true);
            fail("Expected InvalidOrderInformationException was not thrown.");
            
        } catch (InvalidOrderInfoException e) {
            
            return;
            
        }
        
    }
    
    @Test
    public void testCalculateEditedOrderInfo() throws Exception {
        
        Order order = new Order();
        order.setOrderNumber(200);
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("John");
        order.setState("OH");
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        
        order = service.calculateOrderInfo(order, false);
        
        assertEquals(200, order.getOrderNumber());
        assertEquals(new BigDecimal("6.20"), order.getTaxRate());
        assertEquals(new BigDecimal("2.30"), order.getMaterialCostPerSqFt());
        assertEquals(new BigDecimal("2.25"), order.getLaborCostPerSqFt());
        assertEquals(new BigDecimal("115.00"), order.getMaterialCost());
        assertEquals(new BigDecimal("112.50"), order.getLaborCost());
        assertEquals(new BigDecimal("14.11"), order.getTax());
        assertEquals(new BigDecimal("241.61"), order.getTotalCost());
        
    }

    /**
     * Test of findOrdersByDate method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testFindOrdersByDate() throws Exception {
        
        assertEquals(1, service.findOrdersByDate(LocalDate.parse("01/01/2017", formatter)).size());
        
    }
    
    @Test
    public void testFindOrdersByDateEmptyDate() throws Exception {
        
        assertNull(service.findOrdersByDate(LocalDate.parse("01/01/1000", formatter)));
        
    }
    
    @Test
    public void testFindOrdersByDateInvalidFormat() throws Exception {
        
        try {
            
            service.findOrdersByDate(LocalDate.parse("", formatter));
            fail("Expected DateTimeParseException not thrown.");
            
        } catch (DateTimeParseException e) {
            
            return;
            
        }
        
    }

    /**
     * Test of findOrdersByNumber method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testFindOrdersByNumber() throws Exception {
        
        Order order = service.findOrdersByNumber(LocalDate.parse("01/01/2017", formatter), 100);
        assertNotNull(order);
        
        order = service.findOrdersByNumber(LocalDate.parse("01/01/2017", formatter), 300);
        assertNull(order);
        
        order = service.findOrdersByNumber(LocalDate.parse("01/01/1000", formatter), 100);
        assertNull(order);
        
    }
    
    @Test
    public void testFindOrdersByNumberInvalidDate() throws Exception {
        
        try {
            Order order = service.findOrdersByNumber(LocalDate.parse("01-01-2017", formatter), 100);
            fail("Expected DateTimeParseException not thrown.");
            
        } catch (DateTimeParseException e) {
            
            return;
            
        }
        
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
        
        Order order = new Order();
        order.setOrderNumber(200);
        order.setOrderDate(LocalDate.parse("01/01/2017", formatter));
        order.setCustomerName("Julie");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Carpet");
        order.setArea(new BigDecimal("50"));
        order.setMaterialCostPerSqFt(new BigDecimal("2.25"));
        order.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order.setMaterialCost(new BigDecimal("112.50"));
        order.setLaborCost(new BigDecimal("105.00"));
        order.setTax(new BigDecimal("13.49"));
        order.setTotalCost(new BigDecimal("230.99"));
        
        service.editOrder(order);
        
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        
        Order order = new Order();
        
        order = service.removeOrder(100, order);
        assertNotNull(order);
        
        order = service.removeOrder(300, order);
        assertNull(order);
        
    }

    /**
     * Test of saveWork method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testSaveWork() throws Exception {
        
        boolean allowSave = service.saveWork();
        
        //If in prod
        assertTrue(allowSave);
        
        //If in test
        //assertFalse(allowSave);
        
    }
    
}