/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.StateTax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class TaxRateDaoFileImpl implements TaxRateDao {
    
    private Map<String, BigDecimal> taxRates = new HashMap<>();
    
    public static final String TAXES_FILE = "taxes.txt";
    public static final String DELIMITER = ",";
    
    private void loadTaxes() throws OrderPersistenceException {
        
        Scanner scanner;

        try {

            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));

        } catch (FileNotFoundException e) {

            throw new OrderPersistenceException(" - Could not load tax data into memory.", e);

        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            taxRates.put((currentTokens[0]), new BigDecimal(currentTokens[1]));

        }

        scanner.close();

    }
    
    @Override
    public Map<String, BigDecimal> pullAvailableStates() throws OrderPersistenceException {
        
        loadTaxes();
        return taxRates;
        
    }

    @Override
    public BigDecimal findTaxRate(String state) throws OrderPersistenceException {

        loadTaxes();
        StateTax tax = new StateTax();
        tax.setTaxRate(taxRates.get(state));
        return tax.getTaxRate();

    }
    
}

