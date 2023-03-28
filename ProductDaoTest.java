/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.List;
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
public class ProductDaoTest {
    
    private ProductDao productDao = new ProductDaoFileImpl();
    
    public ProductDaoTest() {
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
     * Test of findProduct method, of class ProductDao.
     */
    @Test
    public void testFindProduct() throws Exception {
        
        //Make sure it can pull all types of product
        String productType = "Carpet";
        Product product = productDao.findProduct(productType);
        assertEquals("Carpet", product.getProductType());
        assertEquals(new BigDecimal("2.30"), product.getMaterialCostPerSqFt());
        assertEquals(new BigDecimal("2.25"), product.getLaborCostPerSqFt());
        
        productType = "Laminate";
        product = productDao.findProduct(productType);
        assertEquals("Laminate", product.getProductType());
        assertEquals(new BigDecimal("1.50"), product.getMaterialCostPerSqFt());
        assertEquals(new BigDecimal("2.00"), product.getLaborCostPerSqFt());
        
        productType = "Tile";
        product = productDao.findProduct(productType);
        assertEquals("Tile", product.getProductType());
        assertEquals(new BigDecimal("3.30"), product.getMaterialCostPerSqFt());
        assertEquals(new BigDecimal("4.50"), product.getLaborCostPerSqFt());
        
        productType = "Wood";
        product = productDao.findProduct(productType);
        assertEquals("Wood", product.getProductType());
        assertEquals(new BigDecimal("5.00"), product.getMaterialCostPerSqFt());
        assertEquals(new BigDecimal("4.00"), product.getLaborCostPerSqFt());
        
    }
    
    @Test
    public void testFindProductInvalidProduct() throws Exception {
        
        String productType = "plaster";
        assertNull(productDao.findProduct(productType));
        
    }
    
    @Test
    public void testFindProductBlankField() throws Exception {
        
        String productType = "";
        assertNull(productDao.findProduct(productType));
        
    }
    
    @Test
    public void testPullAllProducts() throws Exception {
        
        List<Product> products = productDao.pullAllProducts();
        assertEquals(4, products.size());
        
    }
    
}