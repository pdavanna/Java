/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author paddy
 */
public interface TaxRateDao {
    
    Map<String, BigDecimal> pullAvailableStates() throws OrderPersistenceException;
    
    BigDecimal findTaxRate(String state) throws OrderPersistenceException;
}
