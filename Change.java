/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author paddy
 */
public class Change {
    private Integer dollars;
    private Integer quarters;
    private Integer dimes;
    private Integer nickels;
    private Integer pennies;
    
    
    
    public Change() {
        dollars = 0;
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
    }
    
    // Method to reset all change fields to zero
    public void setChangeToZero() {
        this.setPennies(0);
        this.setNickels(0);
        this.setDimes(0);
        this.setQuarters(0);
        this.setDollars(0);        
    }

    public Integer getDollars() {
        return dollars;
    }

    public void setDollars(Integer dollars) {
        this.dollars = dollars;
    }

    public Integer getQuarters() {
        return quarters;
    }

    public void setQuarters(Integer quarters) {
        this.quarters = quarters;
    }

    public Integer getDimes() {
        return dimes;
    }

    public void setDimes(Integer dimes) {
        this.dimes = dimes;
    }

    public Integer getNickels() {
        return nickels;
    }

    public void setNickels(Integer nickels) {
        this.nickels = nickels;
    }

    public Integer getPennies() {
        return pennies;
    }

    public void setPennies(Integer pennies) {
        this.pennies = pennies;
    }
    
     // Get BigDecimal USD value of only pennies in change object
    public BigDecimal getValueOfPennies() {
        return new BigDecimal(new BigDecimal(this.getPennies().toString())
                .multiply(new BigDecimal("0.01")).toString());
    } 
    // Get BigDecimal USD value of only nickels in change object
    public BigDecimal getValueOfNickels() {
        return new BigDecimal(new BigDecimal(this.getNickels().toString())
                .multiply(new BigDecimal("0.05")).toString());
    }    
    // Get BigDecimal USD value of only dimes in change object
    public BigDecimal getValueOfDimes() {
        return new BigDecimal(new BigDecimal(this.getDimes().toString())
                .multiply(new BigDecimal("0.10")).toString());
    } 
    /// Get BigDecimal USD value of only quarters in change object
    public BigDecimal getValueOfQuarters() {
        return new BigDecimal(new BigDecimal(this.getQuarters().toString())
                .multiply(new BigDecimal("0.25")).toString());
    }
    /// Get BigDecimal USD value of only quarters in change object
    public BigDecimal getValueOfDollars() {
        return new BigDecimal(new BigDecimal(this.getDollars().toString())
                .multiply(new BigDecimal("1.00")).toString());
    }    
    
    // Get total value of change object in USD as a big decimal
    public BigDecimal getValueOfChange() {
        return new BigDecimal(getValueOfPennies().add(getValueOfNickels())
            .add(getValueOfDimes()).add(getValueOfQuarters()).add(getValueOfDollars()).toString());
    }

    
    @Override
    public String toString() {
        return "Quarters: " + quarters + " Dimes: " + dimes + " Nickels: " + nickels + " Pennies: " + pennies;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.dollars);
        hash = 41 * hash + Objects.hashCode(this.quarters);
        hash = 41 * hash + Objects.hashCode(this.dimes);
        hash = 41 * hash + Objects.hashCode(this.nickels);
        hash = 41 * hash + Objects.hashCode(this.pennies);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (!Objects.equals(this.dollars, other.dollars)) {
            return false;
        }
        if (!Objects.equals(this.quarters, other.quarters)) {
            return false;
        }
        if (!Objects.equals(this.dimes, other.dimes)) {
            return false;
        }
        if (!Objects.equals(this.nickels, other.nickels)) {
            return false;
        }
        if (!Objects.equals(this.pennies, other.pennies)) {
            return false;
        }
        return true;
    }
    
    

}
