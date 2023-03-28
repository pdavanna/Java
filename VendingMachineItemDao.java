/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineNoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author paddy
 */
public interface VendingMachineItemDao {

    public List<Item> getAllItems();
    public void setItemId(String itemId);
    public String getItemId();
    public Item updateItem(Item item);
    // Delete    
    public Item removeItem(String itemId);
    
public BigDecimal addUserMoney(BigDecimal userMoney);
    
    public BigDecimal getUserMoney();
    
    public Item getItem(String itemId);
    
    public void vendItem(Item item) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException;
    
}
