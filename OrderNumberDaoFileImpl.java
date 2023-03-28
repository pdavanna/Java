/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class OrderNumberDaoFileImpl implements OrderNumberDao {

    private int orderNumber = 0;
    public static final String ORDERNUMBER_FILE = "orderNumber.txt";

    private void loadOrderNumber() throws OrderPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDERNUMBER_FILE)));
        } catch (FileNotFoundException e) {
            throw new OrderPersistenceException(
                    "-_- Could not order number data into inventory.", e);
        }
        while (scanner.hasNextInt()) {

            orderNumber = scanner.nextInt();
        }

        scanner.close();
    }

    private void writeOrderNumber() throws OrderPersistenceException {

        PrintWriter out;

        try {

            out = new PrintWriter(new FileWriter(ORDERNUMBER_FILE));

        } catch (IOException e) {

            throw new OrderPersistenceException("Could not save new order number data.", e);

        }

        out.println(orderNumber);
        out.flush();
        out.close();

    }

    @Override
    public int findOrderNumber() throws OrderPersistenceException {

        //if orderNumber = 0, then new orders haven't been created yet and orderNum needs to be loaded in.
        if (orderNumber == 0) {

            loadOrderNumber();

        }

        return orderNumber;

    }

    @Override
    public void increaseOrderNumber() {

        orderNumber++;

    }

    @Override
    public void saveOrderNumber() throws OrderPersistenceException {

        //if orderNumber is 0, then new orders weren't created and orderNumber was not updated
        //orderNumber should then NOT be updated in the file
        if (orderNumber != 0) {

            writeOrderNumber();

        }

    }

}
