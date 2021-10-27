/*
 * Chase Urban
 * Programming Language Parser
 * COMPSCI 350
 */
package UrbanHomework4;

import java.util.Scanner;

public class Main {

    // Index to traverse through the input.
    public static int index;

    /**
     * This program acts as a parser for a theorhetical programming language, created for
     * my Organization of Programming Languages class. Takes an input, and determines if
     * the input is valid based on a predertemined context-free grammar.
     */
    public static void main(String[] args) {
        System.out.println("Enter a set to be parsed:");
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String line = in.nextLine();
            index = 0;
            if (isSet(line))
                System.out.println("Valid!");
            else
                System.out.println("Invalid!");
            System.out.println("Enter a set to be parsed:");
        }
    }

    /**
     * Determines if input String is a set based on the following context-free-grammar.
     *
     * set -> { list }
     * list -> head tail | ε
     * head -> number | set
     * tail -> , head tail | ε
     *
     * Begins by ensuring there are an equal number of left and right braces. If there
     * are not, the string can not grammatically be a set. If the counts are equal,
     * passes String onto set(String line)
     *
     * @param line - the string to be tested as a set
     * @return whether the string is a set, dependent on called functions
     */
    public static boolean isSet(String line){
        int leftCount = 0, rightCount = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{')
                leftCount++;
            if (line.charAt(i) == '}')
                rightCount++;
        }
        // If counts are equal, and if the line contains a set, returns if the index and 
        // the line length are equal.
        if (leftCount == rightCount){
            if (set(line))
                return index == line.length();
        }
        else return false;
        return false;
    }

    /**
     * Once we know that the number of braces of each direction are equal, we can go on to 
     * test if the contents of each set of braces is a list. A list can be a head and a tail,
     * or nothing.
     *
     * @param line - the string to be tested as a set
     * @return whether a set is detected 
     */
    public static boolean set(String line){
        if (line.charAt(index) == '{'){
            index++;
            // Determines an empty set, which is a valid set
            if (line.charAt(index) == '}') {
                index++;
                return true;
            }
            // Otherwise, if the contents are a list, and the next character is a '}', valid.
            else if (list(line)){
                if (line.charAt(index) == '}'){
                    index++;
                    return true;
                }
                else return false;
            }
        }
        else return false;
        return false;
    }

    /**
     * Once we know that the number of braces of each direction are equal, we can go on to 
     * test if the contents of each set of braces is a list. A list can be a head and a tail,
     * or nothing.
     *
     * @param line - the string to be tested as a list
     * @return whether a list is detected 
     */
    public static boolean list(String line){
        if (line.charAt(index) == '}')
            return true;
        else if (head(line)) {
            // If a head is detected, then if a tail is detected, it is a list. If the next
            // character is a '}' means it is the end of a list.
            if (index != line.length()) {
                if (tail(line)) {
                    return line.charAt(index) == '}';
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    /**
     * Tests if the string contains a head, which is either a number (in this situation, 
     * pre-scanned and normalized as 'n'), or a set.
     * 
     * @param line - the string to be tested as a head
     * @return whether a head is detected 
     */
    public static boolean head(String line) {
        // If the character at the index is a '{', and if the next item is a set, returns 
        // true if the index is not at the end of the line.
        if (line.charAt(index) == '{') {
            if (set(line)) {
                return index != line.length();
            }
        // Otherwise, if the character at the index is an n, returns true.
        } else if (line.charAt(index) == 'n') {
            index++;
            return true;
        }
        // Otherwise, returns false.
        return false;
    }

    /**
     * Tests if the string contains a tail, which is defined as either a head and tail,
     * or nothing.
     * 
     * @param line - the string to be tested as a tail
     * @return whether a tail is detected 
     */
    public static boolean tail(String line){
        // If the end of a line has been reached, returns true.
        if (line.charAt(index) == '}')
            return true;
        // Otherwise, if a comma is detected, followed by another head and tail, valid.
        else if (line.charAt(index) == ','){
            index++;
            if (head(line))
                return tail(line);
            else return false;
        }
        else return false;
    }
}
