/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paddy
 */
public class OrderModeDaoTest {
    
    private OrderModeDao orderModeDao = new OrderModeDaoFileImpl();
    
    public OrderModeDaoTest() {
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
     * Test of findOrderMode method, of class OrderModeDao.
     */
    @Test
    public void testFindOrderMode() throws Exception {
        
        String modeTest = orderModeDao.findOrderMode();
        assertTrue("t".equalsIgnoreCase(modeTest) || "p".equalsIgnoreCase(modeTest));
        
    }
    
}