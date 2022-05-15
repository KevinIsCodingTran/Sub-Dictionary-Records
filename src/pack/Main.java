// -----------------------------------------------------
// Assignment 4
// Part 1: Sub-Dictionary Creator
// Written by: Kevin Tran - 40209451
// -----------------------------------------------------
// This assignment is meant to practice using ArrayLists. The program reads through a user-inputted file
// and creates a sub-dictionary out of it.
package pack;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Names and ID: Kevin Tran 40209451
 * COMP249
 * Assignment #4 Part 1
 * Due Date: April 15 2022
 */
public class Main {
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Kevin Tran's Sub-Dictionary Creator!");

        // Prompt user
        // Initialize Scanner and PrintWriter
        String outputFileName = "SubDictionary.txt";
        FilesManipulation filesManip = new FilesManipulation(outputFileName);

        // Reading input file
        ArrayList<String> subDictionary = new ArrayList<>();
        while (filesManip.getInputReader().hasNext()) {
            // Process and append words into array list
            appendFormattedWord(filesManip.getInputReader(), subDictionary);
        }

        // Verify and print list
        subDictionary.trimToSize();
        if (subDictionary.size() == 0) {
            System.out.println("The SubDictionary is empty.");
            filesManip.deleteOutputFile();
        } else {
            printSubDictionary(filesManip.getOutputWriter(), subDictionary);
        }

        // Close files
        filesManip.closeReaderWriter();
        System.out.println("Thank you for using the Sub-Dictionary Creator!");

    }

    /**
     * Process the new word by checking and formatting
     *
     * @param reader
     * @param subDictionary
     */
    public static void appendFormattedWord(Scanner reader, ArrayList<String> subDictionary) {
        String nextWord = reader.next();
        String formattedWord = Word.format(nextWord);

        // Check if exists already
        if (formattedWord != null) {
            for (int i = 0; i < subDictionary.size(); i++) {
                if (subDictionary.get(i).equals(formattedWord)) {
                    return;
                }
            }
            subDictionary.add(formattedWord);
        }
    }

    /**
     * Print list into dictionary format
     *
     * @param writer
     * @param subDictionary
     */
    private static void printSubDictionary(PrintWriter writer, ArrayList<String> subDictionary) {
        writer.println("The document produced this sub-dictionary, which includes " + subDictionary.size() + " entries.");
        // Order array list
        subDictionary.sort(Comparator.naturalOrder());

        char previousLetter = 0;
        for (String word: subDictionary) {
            // Check if it's a different first letter
            char nextLetter = word.charAt(0);
            if (previousLetter != nextLetter) {
                // Change next letter
                previousLetter = nextLetter;
                writer.println("\n" + nextLetter + "\n==");
            }
            writer.println(word);
        }
    }
}
