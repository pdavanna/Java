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
public class VendingMachineUserChangeDaoInMemImpl implements VendingMachineChangeDao {

    private Change userChange = new Change();
    
    @Override
    public Change addChange(Change change) {
        userChange.setDollars(userChange.getDollars() + change.getDollars());
        userChange.setQuarters(userChange.getQuarters() + change.getQuarters());
        userChange.setDimes(userChange.getDimes() + change.getDimes());
        userChange.setNickels(userChange.getNickels() + change.getNickels());
        userChange.setPennies(userChange.getPennies() + change.getPennies());
        return change;
    }

    @Override
    public Change getChange() {
        return userChange;
    }

    @Override
    public Change updateChange(Change change) {
        userChange.setDollars(change.getDollars());
        userChange.setQuarters(change.getQuarters());
        userChange.setDimes(change.getDimes());
        userChange.setNickels(change.getNickels());
        userChange.setPennies(change.getPennies());
        return change;
    }

    @Override
    public void removeChange() {
        userChange = new Change();
        userChange.setChangeToZero();
    }
    
}
