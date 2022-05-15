// -----------------------------------------------------
// Assignment 4
// Part 1: Sub-Dictionary Creator
// Written by: Kevin Tran - 40209451
// -----------------------------------------------------
// This assignment is meant to practice using ArrayLists. The program reads through a user-inputted file
// and creates a sub-dictionary out of it.
package pack;

/**
 * Names and ID: Kevin Tran 40209451
 * COMP249
 * Assignment #4 Part 1
 * Due Date: April 15 2022
 */
public class Word {
    /**
     * Check for forbidden characters and capitalize word
     * @param word
     * @return String
     */
    public static String format(String word) {
        String formatted = "";

        // Check for numbers
        if (hasNumbers(word)) {
            return null;
        }

        // Check for forbidden characters
        for (int i = 0; i < word.length(); i++) {
            if (!isForbiddenChar(word.charAt(i))) {
                formatted += word.charAt(i);
            } else { break; }
        }

        // For forbidden chars
        if (formatted.length() == 0) {
            return null;
        }
        // Single letter
        else if (formatted.length() == 1) {
            char character = formatted.charAt(0);
            boolean isA = character == 'a' || character == 'A';
            boolean isI = character == 'i' || character == 'I';

            if (!isA && !isI) {
                return null;
            }
        }
        else if (formatted.equalsIgnoreCase("MC")) {
            char square = 178;
            return "MC" + square;
        }

        // Capitalize
        return formatted.toUpperCase();
    }

    /**
     * Check for MC or has numbers
     * @param word
     * @return boolean
     */
    public static boolean hasNumbers(String word) {
        boolean containsMC = word.contains("MC") && word.length() == 3;
        boolean containsNumber = false;

        String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String digit: numbers) {
            if (word.contains(digit)) {
                containsNumber = true;
            }
        }

        if (containsMC) { return false; }
        else if (containsNumber) { return true; }
        else { return false; }
    }

    /**
     * Check whether the char is forbidden
     * @param character
     * @return boolean
     */
    public static boolean isForbiddenChar(char character) {
        boolean lowerLetter = character >= 'a' && character <= 'z';
        boolean upperLetter = character >= 'A' && character <= 'Z';

        return !(lowerLetter || upperLetter);
    }
}
