/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner userInput = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public int readInt(String prompt) {

        System.out.println(prompt);
        String userChoice = userInput.nextLine();
        int userNum = Integer.parseInt(userChoice);
        return userNum;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int userNum;
        String userChoice;

        do {
            System.out.println(prompt);
            userChoice = userInput.nextLine();
            userNum = Integer.parseInt(userChoice);
        } while (userNum < min || userNum > max);
        return userNum;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        prompt = userInput.nextLine();
        return prompt;
    }

    @Override
    public float readFloat(String prompt) {

        System.out.println(prompt);
        String userChoice = userInput.nextLine();
        float userNum = Float.parseFloat(userChoice);
        return userNum;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float userNum;
        String userChoice;

        do {
            System.out.println(prompt);
            userChoice = userInput.nextLine();
            userNum = Float.parseFloat(userChoice);
        } while (userNum < min || userNum > max);
        return userNum;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String userChoice = userInput.nextLine();
        double userNum = Double.parseDouble(userChoice);
        return userNum;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double userNum;
        String userChoice;

        do {
            System.out.println(prompt);
            userChoice = userInput.nextLine();
            userNum = Double.parseDouble(userChoice);
        } while (userNum < min || userNum > max);
        return userNum;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        String userChoice = userInput.nextLine();
        long userNum = Long.parseLong(userChoice);
        return userNum;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long userNum;
        String userChoice;

        do {
            System.out.println(prompt);
            userChoice = userInput.nextLine();
            userNum = Long.parseLong(userChoice);
        } while (userNum < min || userNum > max);
        return userNum;
    }
    
}