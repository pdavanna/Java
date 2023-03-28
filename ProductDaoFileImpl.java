/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author paddy
 */
public class ProductDaoFileImpl implements ProductDao {
    // Instantiate HashMap to link numbers to products
    private Map<String, Product> products = new HashMap<>();

    public static final String PRODUCTS_FILE = "products.txt";
    public static final String DELIMITER = ",";

    private void loadProducts() throws OrderPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));

        } catch (FileNotFoundException e) {

            //Need to not end application if file not found
            throw new OrderPersistenceException(" - Could not load product data into memory.", e);

        }
        // Declares a string to hold the data file data line by line
        String currentLine;
        // Declares a string array to hold data field data as tokens
        String[] currentTokens;
        // Reads in each existing line of data file, which represents a product object, 
        // and splits string into tokens representing each individual field of an object
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProduct = new Product();
            currentProduct.setProductType(currentTokens[0]);
            currentProduct.setMaterialCostPerSqFt(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSqFt(new BigDecimal(currentTokens[2]));
            // Add new product object to HashMap
            products.put(currentProduct.getProductType(), currentProduct);

        }

        scanner.close();

    }

    @Override
    public List<Product> pullAllProducts() throws OrderPersistenceException {

        loadProducts();
        return products.values()
                .stream()
                .collect(Collectors.toList());

    }

    @Override
    public Product findProduct(String productType) throws OrderPersistenceException {

        loadProducts();
        return products.get(productType);

    }

}
