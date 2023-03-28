/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class OrderModeDaoFileImpl implements OrderModeDao {

    public static final String MODE_FILE = "orderMode.txt";
    public static String modeType = "p";

    private void loadMode() throws OrderPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(MODE_FILE)));
        } catch (FileNotFoundException e) {
            throw new OrderPersistenceException(
                    "-_- Could not load mode file into inventory.", e);
        }
        while (scanner.hasNextLine()) {
            
            modeType = scanner.nextLine();
        }

        scanner.close();
    }

    @Override
    public String findOrderMode() throws OrderPersistenceException {
        loadMode();
        return modeType;

    }

}