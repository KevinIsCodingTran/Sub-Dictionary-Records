// -----------------------------------------------------
// Assignment 4
// Part 2: Linked List
// Written by: Kevin Tran - 40209451
// -----------------------------------------------------
// This assignment is to practice using LinkedList. This program reads through a list of cellphone information and creates
// a LinkedList with that information.
package pack;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Names and ID: Kevin Tran 40209451
 * COMP249
 * Assignment #4 Part 2
 * Due Date: April 15 2022
 */
public class CellPhone {
    private long serialNum;
    private String brand;
    private int year;
    private double price;

    /**
     * Parameterized Constructor
     *
     * @param serialNum
     * @param brand
     * @param year
     * @param price
     */
    public CellPhone(long serialNum, String brand, int year, double price) {
        this.serialNum = serialNum;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    /**
     * Copy conctructor
     *
     * @param cellphone
     * @param serial
     */
    public CellPhone(CellPhone cellphone, long serial) {
        this.serialNum = serial;
        this.brand = cellphone.brand;
        this.year = cellphone.year;
        this.price = cellphone.price;
    }

    /**
     * Clone method
     *
     * @return CellPhone
     */
    public CellPhone clone() {
        Scanner kb = new Scanner(System.in);
        boolean isValid = false;
        long newSerialNum = 0;
        while (!isValid) {
            try {
                System.out.println("Enter a new serial number for the cellphone to be cloned: ");
                newSerialNum = kb.nextLong();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("You have entered an invalid serial number. Please try again.");
                kb.nextLine();
            }
        }
        return new CellPhone(this, newSerialNum);
    }

    /**
     * Get serial number
     *
     * @return long
     */
    public long getSerialNum() {
        return this.serialNum;
    }

    /**
     * Set serial number
     *
     * @param serialNum
     */
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Get brand
     *
     * @return String
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Set brand
     *
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get year
     *
     * @return int
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Set year
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Get price
     *
     * @return double
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Set price
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Equals method
     *
     * @param objRef
     * @return boolean
     */
    @Override
    public boolean equals(Object objRef) {
        if (this == objRef)
            return  true;
        if (objRef == null)
            return false;
        if (this.getClass() != objRef.getClass())
            return false;

        CellPhone otherRef = (CellPhone) objRef;

        return this.brand.equals(otherRef.brand) && this.year == otherRef.year && this.price == otherRef.price;
    }

    /**
     * Return a String of the cellphone's information
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.serialNum + ": " + this.brand + " " + this.price + "$ " + this.year + "]";
    }

}
