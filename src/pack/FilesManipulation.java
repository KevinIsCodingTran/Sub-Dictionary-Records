// -----------------------------------------------------
// Assignment 4
// Part 1 & 2: Sub-Dictionary Creator & Linked List
// Written by: Kevin Tran - 40209451
// -----------------------------------------------------
// This class is a helper to easily open and close files, Scanners and PrintWriters. It is both used in
// part 1 and 2 of assignment 4
package pack;

import java.io.*;
import java.util.Scanner;

/**
 * Names and ID: Kevin Tran 40209451
 * COMP249
 * Assignment #4 Part 1 & 2
 * Due Date: April 15 2022
 */
public class FilesManipulation {
    private Scanner inputReader;
    private PrintWriter outputWriter;
    private String inputFileName;
    private String outputFileName;

    /**
     * Default constructor
     */
    public FilesManipulation() {
        Scanner inputReader = null;
        PrintWriter outputWriter = null;
        String inputFileName = "";
        String outputFileName = "";
    }

    /**
     * Parameterized constructor
     *
     * @param outputFileName
     */
    public FilesManipulation(String outputFileName) {
        this.outputFileName = outputFileName;
        this.inputFileName = promptInputName(true);
        initializeReaderWriter(this.inputFileName, this.outputFileName);
    }

    /**
     * Get input reader
     *
     * @return Scanner
     */
    public Scanner getInputReader() {
        return this.inputReader;
    }

    /**
     * Get print writer
     *
     * @return PrintWriter
     */
    public PrintWriter getOutputWriter() {
        return this.outputWriter;
    }

    /**
     * Get input file name
     *
     * @return String
     */
    public String getInputFileName() {
        return this.inputFileName;
    }

    /**
     * Get output file name
     *
     * @return String
     */
    public String getOutputFileName() {
        return this.outputFileName;
    }

    /**
     * Prompt for file name and check if exists
     *
     * @param correctInput
     * @return String
     */
    public String promptInputName(boolean correctInput) {
        Scanner kb = new Scanner(System.in);
        boolean validInput = false;
        String fileName = "";
        int tries = 1;

        do {
            if (tries > 5) {
                System.out.println("You have exceeded the numbers of tries. \nThe program will now end.");
                System.exit(0);
            }
            else if (tries != 1) {
                System.out.println("Wrong input. Please try again.");
            }

            // Prompt user
            System.out.println("\nPlease enter the file name you wish to create a sub-dictionary for, including its extension (xxx.txt.):  ");
            fileName = kb.nextLine();

            if (!fileName.contains(".")) {
                validInput = false;
                System.out.println("Please add the file's extension.");
                tries++;
            }
            else if (correctInput) {
                // Check if the file exists
                File input = new File(fileName);
                if (!input.exists() || !input.canRead()) {
                    System.out.println("The file entered does not exists or cannot be read.");
                    tries++;
                } else {
                    validInput = true;
                }
            }
        } while (!validInput);
        kb.close();
        return fileName;
    }

    /**
     * Initialize Scanner and Writer
     *
     * @param input
     * @param output
     */
    public void initializeReaderWriter(String input, String output) {
        if (input != "" && output != "") {
            if (!initializeReader(input)) {
                System.out.println("The program will now end.");
                System.exit(0);
            }
            if (!initializeWriter(output)) {
                System.out.println("The program will now end.");
                System.exit(0);
            }
        }
    }

    /**
     * Initialize reader
     *
     * @param fileName
     * @return boolean
     */
    public boolean initializeReader(String fileName) {
        try {
            this.inputReader = new Scanner(new FileInputStream(fileName));
            System.out.println("Scanner successfully opened");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error opening Scanner with: " + fileName + "\n" + e.getMessage());
            return false;
        }
    }

    /**
     * Initialize writer
     *
     * @param fileName
     * @return boolean
     */
    public boolean initializeWriter(String fileName) {
        try {
            this.outputWriter = new PrintWriter(new FileOutputStream(fileName));
            System.out.println("PrintWriter successfully opened");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Error opening PrintWriter with: " + fileName + "\n" + e.getMessage());
            return false;
        }
    }

    /**
     * Close reader and writer
     *
     * @return boolean
     */
    public boolean closeReaderWriter() {
        if (inputReader != null && outputFileName != "") {
            inputReader.close();
            outputWriter.flush();
            outputWriter.close();
            return true;
        } else { return false; }
    }

    /**
     * Delete output file
     * @return boolean
     */
    public boolean deleteOutputFile() {
        File output = new File(this.outputFileName);
        if (output.exists()) {
            output.delete();
            return true;
        } else { return false; }

    }


}
