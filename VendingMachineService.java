/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author paddy
 */
public interface VendingMachineService {
    //keep
    public Change addChange(Change change);
    public Change getChange();
    List<Item> getAllItems();

    BigDecimal addUserMoney(BigDecimal userMoney);
    BigDecimal getUserMoney();

    Item getItem(String itemId);

    void vendItem(Item item, BigDecimal totalMoney) throws
            VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException;

    Change makeChange(BigDecimal totalMoney);
    
}

