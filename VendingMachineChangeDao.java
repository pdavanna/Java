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
public interface VendingMachineChangeDao {
    // Create
    public Change addChange(Change change);
    // Read
    public Change getChange();
    // Update
    public Change updateChange(Change change);
    // Delete    
    public void removeChange();   
    
}
