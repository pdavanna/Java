/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;

/**
 *
 * @author paddy
 */
public class VendingMachineChangeDaoInMemImpl implements VendingMachineChangeDao {
      private Change machineChange;
      
    public VendingMachineChangeDaoInMemImpl() {
        this.machineChange = new Change();
        this.machineChange.setDollars(100);
        this.machineChange.setQuarters(100);
        this.machineChange.setDimes(100);
        this.machineChange.setNickels(100);
        this.machineChange.setPennies(100);       
    }
    
    @Override
    public Change addChange(Change change) {
        machineChange.setDollars(machineChange.getDollars() + change.getDollars());
        machineChange.setQuarters(machineChange.getQuarters() + change.getQuarters());
        machineChange.setDimes(machineChange.getDimes() + change.getDimes());
        machineChange.setNickels(machineChange.getNickels() + change.getNickels());
        machineChange.setPennies(machineChange.getPennies() + change.getPennies());
        return change;
    }

    @Override
    public Change getChange() {
        return machineChange;
    }

    @Override
    public Change updateChange(Change change) {
        machineChange = change;
        return change;
    }

    @Override
    public void removeChange() {
        machineChange = new Change();
        machineChange.setChangeToZero();        
    }
    
    
}
