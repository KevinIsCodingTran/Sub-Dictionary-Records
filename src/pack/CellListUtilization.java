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
import java.util.StringTokenizer;

/**
 * Names and ID: Kevin Tran 40209451
 * COMP249
 * Assignment #4 Part 2
 * Due Date: April 15 2022
 */
public class CellListUtilization {
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to the program");
        CellList cellsFromFile = new CellList();
        CellList otherList = new CellList();

        // Open scanner for txt file
        final String inputFileName = "Cell_Info.txt";
        FilesManipulation file = new FilesManipulation();
        file.initializeReader(inputFileName);
        Scanner reader = file.getInputReader();

        // Read through text file and add to list
        while (reader.hasNextLine()) {
            CellPhone cell = parseCellphone(reader);
            // Only add if serial number unique
            if (!cellsFromFile.contains(cell.getSerialNum())) {
                cellsFromFile.add(cell);
            }
        }
        cellsFromFile.showContents();

        Scanner kb = new Scanner(System.in);
        boolean end = false;

        // Menu actions
        while (!end) {
            switch(displayMenu(kb)) {
                case 1:
                    System.out.println("You have chosen to search for a serial number.");
                    searchForSerialNumber(cellsFromFile);
                    break;
                case 2:
                    System.out.println("You have chosen to test CellPhone methods.");
                    testCellPhoneMethods();
                    break;
                case 3:
                    System.out.println("You have chosen to test CellList methods.");
                    testCellListMethods(cellsFromFile, otherList);
                    break;
                case 4:
                    System.out.println("You have chosen to test an error. The program will crash.");
                    crash_InsertAtIndex(cellsFromFile, 100);
                    break;
                case 5:
                    System.out.println("You have chosen to test an error. The program will crash.");
                    crash_DeleteFromIndex(cellsFromFile, 100);
                    break;
                case 6:
                    System.out.println("You have chosen to test an error. The program will return.");
                    crash_ReplaceAtIndex(cellsFromFile, 100);
                    break;
                case 0:
                    System.out.println("You have chosen to quit the program.");
                    end = true;
                    break;
                default:
                    System.out.println("You have entered an invalid option. Please try again.");
                    break;
            }
        }
        // Close scanners
        kb.close();
        reader.close();
        System.out.println("Thank you for using the program");
    }

    /**
     * Method to display menu
     *
     * @param kb
     * @return int
     */
    public static int displayMenu(Scanner kb) {
        System.out.println("\nPlease choose an option:" +
                "\n1)\tSearch for a serial number" +
                "\n2)\tTest CellPhone methods" +
                "\n3)\tTest CellList methods" +
                "\n4)\tTest InsertAtIndex out of bounds" +
                "\n5)\tTest DeleteFromIndex out of bounds" +
                "\n6)\tTest ReplaceAtIndex out of bounds" +
                "\n0)\tQuit Program");
        boolean redo = true;
        int option = 0;
        while (redo) {
            try {
                System.out.println("");
                option = kb.nextInt();
                return option;
            } catch (InputMismatchException e) {
                System.out.println("Please try again.");
                // Read rest of line
                kb.nextLine();
            }
        }
        return 0;
    }

    /**
     * Method to parse from String to CellPhone
     *
     * @param reader
     * @return CellPhone
     */
    public static CellPhone parseCellphone(Scanner reader) {
        String line = reader.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(line);
        String[] cellInfo = new String[4];

        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            cellInfo[index] = tokenizer.nextToken();
            index++;
        }
        return new CellPhone(Long.parseLong(cellInfo[0]), cellInfo[1], Integer.parseInt(cellInfo[3]), Double.parseDouble(cellInfo[2]));
    }

    /**
     * Method that looks for a serial number
     *
     * @param cells
     */
    public static void searchForSerialNumber(CellList cells) {
        boolean redo = true;
        int count = 1;
        while (redo) {
            System.out.println("Search #" + count +
                    "\n=================\n");
            System.out.println("Please enter the serial number you are looking for (enter 0 to exit): ");
            long serial = promptSerialNumb(); // Prompt for user

            if (serial == 0) {
                System.out.println("The number of searches is: " + count);
                redo = false;
            } else {
                cells.searchInfo(serial); // Look for serial number in list
            }
            count++;
        }
    }

    /**
     * Method that prompts user for a serial number
     *
     * @return long
     */
    public static long promptSerialNumb() {
        long serialNumb = 0;
        Scanner kb = new Scanner(System.in);

        boolean validNumb = false;
        do {
            try {
                serialNumb = kb.nextLong();
                validNumb = true;
            } catch (InputMismatchException e) { // If wrong input
                System.out.println("The number you have entered is invalid. Please try again.");
                kb.nextLine(); // Read rest of line
            }
        } while (!validNumb);
        return serialNumb;
    }

    /**
     * Method to test methods in CellPhone class
     */
    public static void testCellPhoneMethods() {
        // Parameterized constructor
        System.out.println("Parameterized Cellphone:");
        CellPhone cellphone1 = new CellPhone(420420, "Avocado", 1998, 500);
        System.out.println(cellphone1);
        System.out.print("\n");

        // Copy constructor
        System.out.println("Cellphone's Copy Constructor:");
        CellPhone cellphone2 = new CellPhone(cellphone1, 710710);
        System.out.println(cellphone2);
        System.out.print("\n");

        // Clone
        System.out.println("Cellphone's .clone():");
        CellPhone cellphone3 = cellphone2.clone();
        System.out.println(cellphone3);
        System.out.print("\n");

        // Get serial, brand, price, year
        System.out.println("Accessors testing:");
        System.out.println("Get Serial: " + cellphone1.getSerialNum());
        System.out.println("Get Brand: " + cellphone1.getBrand());
        System.out.println("Get Year: " + cellphone1.getYear());
        System.out.println("Get Price: " + cellphone1.getPrice());
        System.out.print("\n");

        // Set serial, brand, price, year
        System.out.println("Mutators testing:");
        System.out.println("Change Serial to: 656155");
        cellphone3.setSerialNum(656155);
        System.out.println("Change Brand to: Yoplait");
        cellphone3.setBrand("Yoplait");
        System.out.println("Change Year to: 4020");
        cellphone3.setYear(4020);
        System.out.println("Change Price to: 1.00");
        cellphone3.setPrice(1.00);
        System.out.println(cellphone1);
        System.out.print("\n");

        // Equals
        System.out.println("Testing equals method:");
        System.out.println(cellphone1.equals(cellphone2));
        System.out.print("\n");
    }

    /**
     * Method to test methods in CellList class
     */
    public static void testCellListMethods(CellList cellList, CellList otherList) {
        // Methods involving the second list
        System.out.print("Delete from start of empty list: ");
        otherList.deleteFromStart();
        System.out.println("Copying the list of cellphones from the files (copy constructor).");
        otherList = new CellList(cellList);
        System.out.println("\nShowing contents of original list:");
        cellList.showContents();
        System.out.println("\nShowing contents of copy list:");
        otherList.showContents();
        System.out.println("Checking for equality between both lists: " + cellList.equals(otherList));
        System.out.print("\n");

        System.out.println("Deleting node at index 3 in copy list.");
        otherList.deleteFromIndex(3);
        // ShowContents
        otherList.showContents();
        System.out.println("\nCreating a new cellphone of serialNum: 541458, brand: Hello, year 2019, price 85.61:");
        CellPhone cellPhone = new CellPhone(541458, "Hello", 2019, 85.61);
        System.out.println("Cellphone: " + cellPhone);
        System.out.println("Replacing original list's cellphone at index 3:");
        cellList.replaceAtIndex(cellPhone, 3);
        // ShowContents
        cellList.showContents();

        // AddToStart
        System.out.print("\nAdd to start of list:");
        cellList.addToStart(cellPhone);
        cellList.showContents();
        System.out.print("\n");

        // InsertAtIndex
        System.out.print("Insert at Index 4:");
        CellPhone cellPhone2 = new CellPhone(456651, "Elden", 1999, 65.30);
        cellList.insertAtIndex(cellPhone2, 4);
        cellList.showContents();
        System.out.print("\n");

        // DeleteFromIndex
        System.out.print("Delete from Index 3:");
        cellList.deleteFromIndex(3);
        cellList.showContents();
        System.out.print("\n");

        // DeleteFromStart
        System.out.print("Delete from start");
        cellList.deleteFromStart();
        cellList.showContents();
        System.out.print("\n");

        // ReplaceAtIndex
        System.out.print("Replace at Index 6:");
        cellList.replaceAtIndex(cellPhone2, 6);
        cellList.showContents();
        System.out.print("\n");
    }

    /**
     * Method to test exception in InsertAtIndex method
     *
     * @param cells
     * @param index
     */
    public static void crash_InsertAtIndex(CellList cells, int index) {
        System.out.println("Create a cellphone to insert at position " + index + ": \nserialNum: 546867, brand: MeetFresh, year: 2001, price: 716.20");
        CellPhone cellPhone = new CellPhone(546867, "MeetFresh", 2001, 716.20);
        cells.insertAtIndex(cellPhone, index);
    }

    /**
     * Method to test exception in DeleteFromIndex method
     *
     * @param cells
     * @param index
     */
    public static void crash_DeleteFromIndex(CellList cells, int index) {
        System.out.println("Delete node at index: " + index);
        cells.deleteFromIndex(index);
    }

    /**
     * Method to test exception in ReplaceAtIndex method
     *
     * @param cells
     * @param index
     */
    public static void crash_ReplaceAtIndex(CellList cells, int index) {
        System.out.println("Create a cellphone to replace at position " + index + ": \nserialNum: 546867, brand: MeetFresh, year: 2001, price: 716.20");
        CellPhone cellPhone = new CellPhone(546867, "MeetFresh", 2001, 716.20);
        cells.replaceAtIndex(cellPhone, index);
    }

}
