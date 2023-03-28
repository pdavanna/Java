/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineChangeDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachineItemDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author paddy
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingMachineChangeDao machineChangeDao;
    private VendingMachineItemDao itemDao;
    private VendingMachineChangeDao userChangeDao;

    public VendingMachineServiceImpl(VendingMachineChangeDao machineChangeDao, VendingMachineItemDao itemDao,
            VendingMachineChangeDao userChangeDao) {
        this.machineChangeDao = machineChangeDao;
        this.itemDao = itemDao;
        this.userChangeDao = userChangeDao;
    }
    @Override
    public Change addChange(Change change) {
        return machineChangeDao.addChange(change);
    }
    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public BigDecimal addUserMoney(BigDecimal userMoney){
        return itemDao.addUserMoney(userMoney);
    }

    @Override
    public BigDecimal getUserMoney(){
        return itemDao.getUserMoney();
    }

    @Override
    public Item getItem(String itemId){
        return itemDao.getItem(itemId);
    }
    // Peform business logic to decide whether machine will vend item or not
    
    @Override
    public void vendItem(Item item, BigDecimal totalMoney) throws
            VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, NullPointerException {
       //Change change = getChange();
        if (item.getInStock()== 0) {
            throw new VendingMachineNoItemInventoryException(item.getName() + " is out of stock.");
        }

        if (item.getPrice().compareTo(totalMoney) > 0) {
            throw new VendingMachineInsufficientFundsException("Your funds are insufficient. Please deposit: $ " + item.getPrice().subtract(totalMoney).toString());
        }
        itemDao.vendItem(item);
        machineChangeDao.removeChange();
    }
    @Override
    public Change getChange() {
        return machineChangeDao.getChange();
    }
    @Override
    public Change makeChange(BigDecimal totalMoney) {
        //BigDecimal money;
        
        Change userChange = new Change();
        // Calculate number of quarters to return
        BigDecimal quarters = totalMoney.divide(new BigDecimal("0.25"));
        quarters = quarters.setScale(0, RoundingMode.FLOOR);
        totalMoney = totalMoney.subtract(quarters.multiply(new BigDecimal("0.25")));
        userChange.setQuarters(quarters.intValue());

        // Calculate number of dimes to return
        BigDecimal dimes = totalMoney.divide(new BigDecimal("0.1"));
        dimes = dimes.setScale(0, RoundingMode.FLOOR);
        totalMoney = totalMoney.subtract(dimes.multiply(new BigDecimal("0.1")));
        userChange.setDimes(dimes.intValue());

        // Calculate number of nickels to return
        BigDecimal nickels = totalMoney.divide(new BigDecimal("0.05"));
        nickels = nickels.setScale(0, RoundingMode.FLOOR);
        totalMoney = totalMoney.subtract(nickels.multiply(new BigDecimal("0.05")));
        userChange.setNickels(nickels.intValue());

        // Calculate number of pennies to return
        BigDecimal pennies = totalMoney.divide(new BigDecimal("0.01"));
        pennies = pennies.setScale(0, RoundingMode.FLOOR);
        totalMoney = totalMoney.subtract(pennies.multiply(new BigDecimal("0.01")));
        userChange.setPennies(pennies.intValue());

        // Return user's change object
        return userChange;
    }
}
