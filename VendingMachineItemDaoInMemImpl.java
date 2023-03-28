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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author paddy
 */
public class VendingMachineItemDaoInMemImpl implements VendingMachineItemDao {

    public Map<String, Item> itemMap = new HashMap<>();
    public String itemId;
    public VendingMachineItemDaoInMemImpl() {
            Item item = new Item("1");
            item.setName("Snickers");
            item.setPrice(new BigDecimal("1.00"));
            item.setInStock(3);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("2");
            item.setName("Twix");
            item.setPrice(new BigDecimal("1.15"));
            item.setInStock(9);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("3");
            item.setName("Reeces Pieces");
            item.setPrice(new BigDecimal("1.20"));
            item.setInStock(7);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("4");
            item.setName("Almond Joy");
            item.setPrice(new BigDecimal("1.20"));
            item.setInStock(1);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("5");
            item.setName("Dorito's");
            item.setPrice(new BigDecimal("1.00"));
            item.setInStock(2);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("6");
            item.setName("Sour Patch Kids");
            item.setPrice(new BigDecimal("1.25"));
            item.setInStock(3);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("7");
            item.setName("Laffy Taffy");
            item.setPrice(new BigDecimal("1.30"));
            item.setInStock(4);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("8");
            item.setName("Skittles");
            item.setPrice(new BigDecimal("1.35"));
            item.setInStock(4);
            this.itemMap.put(item.getItemId(), item);

            item = new Item("9");
            item.setItemId("9");
            item.setName("Oreo's");
            item.setPrice(new BigDecimal("1.40"));
            item.setInStock(8);
            this.itemMap.put(item.getItemId(), item);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList(itemMap.values());
    }

    @Override
   public void setItemId(String itemId) {
       this.itemId = itemId;
   }
    @Override
    public String getItemId() {
        return itemId;
    }

    @Override
    public Item updateItem(Item item) {
        itemMap.put(item.getItemId(), item);
        return item;
    }

    @Override
    public Item removeItem(String itemId) {
        Item removedItem = itemMap.get(itemId);
        itemMap.remove(itemId);
        return removedItem;
    }

    
    
    private BigDecimal totalMoney = BigDecimal.ZERO;
    
    @Override
    public BigDecimal addUserMoney(BigDecimal userMoney) {
        totalMoney = totalMoney.add(userMoney);
        return totalMoney;
    }

    @Override
    public BigDecimal getUserMoney() {
        return totalMoney;
    }
    @Override
    public Item getItem(String itemId){
        itemMap.values();
        return itemMap.get(itemId);
    }
    @Override
    public void vendItem(Item item) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
            
            item.setInStock(item.getInStock() - 1);
            updateItem(item);
            totalMoney = BigDecimal.ZERO;
    }
    
}
