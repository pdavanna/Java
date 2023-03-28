/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

/**
 *
 * @author paddy
 */
public interface OrderNumberDao {

    int findOrderNumber() throws OrderPersistenceException;

    void increaseOrderNumber();

    void saveOrderNumber() throws OrderPersistenceException;
}
