/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineNoItemInventoryException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.sg.vendingmachinespringmvc.service.VendingMachineService;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author paddy
 */
@Controller
public class VendingMachineController {
    VendingMachineService service;
    String selectionString;
    @Inject
    public VendingMachineController(VendingMachineService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/getChange", method = RequestMethod.GET)
    public String getChange(HttpServletRequest request, Model model) {
        Change moneyEntered = service.getChange();
        BigDecimal moneyEntered1 = moneyEntered.getValueOfChange();
        Change change1 = service.makeChange(moneyEntered1);
        moneyEntered.setChangeToZero();
        model.addAttribute("changeToReturn", change1.toString());
        
        return "redirect:displayVendingMachine";
    }
    
    @RequestMapping(value = "/selectItem", method = RequestMethod.POST)
   public String selectItem(HttpServletRequest request, Model model) {
       selectionString = request.getParameter("item");
       model.addAttribute("selectionString", selectionString);
       return "redirect:displayVendingMachine";

   }

    @RequestMapping(value = "/addUserMoney", method = RequestMethod.POST)
    public String addUserMoney(@RequestParam(value = "button") String button,
            Model model) {

        Change change = new Change();

        if (button.equals("Add Dollar")) {
            change.setDollars(1);
        }
        if (button.equals("Add Quarter")) {
            change.setQuarters(1);
        }
        if (button.equals("Add Dime")) {
            change.setDimes(1);
        }
        if (button.equals("Add Nickel")) {
            change.setNickels(1);
        }

        service.addChange(change);
        return "redirect:displayVendingMachine";
    }
    
    @RequestMapping(value = "/displayVendingMachine", method = RequestMethod.GET)
    public String displayVendingMachine(HttpServletRequest request, Model model) {
        List<Item> itemList = service.getAllItems();
        model.addAttribute("itemList", itemList);
        model.addAttribute("moneyEntered", service.getChange().getValueOfChange().toString());
        model.addAttribute("errorMessage", request.getParameter("errorMessage"));
        model.addAttribute("selectionString", request.getParameter("selectionString"));
        model.addAttribute("changeToReturn", request.getParameter("changeToReturn"));
        return "vendingmachine";
    }
    
    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String purchaseItem(Model model) {
        try {
            Item item = service.getItem(selectionString);
            Change moneyEntered = service.getChange();
            BigDecimal moneyEnteredBD = moneyEntered.getValueOfChange();
            service.vendItem(item, moneyEnteredBD);
            BigDecimal costOfItem = item.getPrice();
            BigDecimal moneyReturned = moneyEnteredBD.subtract(costOfItem);
            Change change1 = service.makeChange(moneyReturned);
            
            model.addAttribute("changeToReturn", change1.toString());
        } catch (VendingMachineNoItemInventoryException | VendingMachineInsufficientFundsException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
    
        return "redirect:displayVendingMachine";
    
    } 
    
}