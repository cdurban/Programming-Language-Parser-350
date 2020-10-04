
package UrbanHomework4;

import java.util.Scanner;

public class Main {

    public static int index;

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

    public static boolean isSet(String line){
        int leftCount = 0, rightCount = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '{')
                leftCount++;
            if (line.charAt(i) == '}')
                rightCount++;
        }
        if (leftCount == rightCount){
            if (set(line))
                return index == line.length();
        }
        else return false;
        return false;
    }

    public static boolean set(String line){
        if (line.charAt(index) == '{'){
            index++;
            if (line.charAt(index) == '}') {
                index++;
                return true;
            }
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

    public static boolean list(String line){
        if (line.charAt(index) == '}')
            return true;
        else if (head(line)) {
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

    public static boolean head(String line) {
        if (line.charAt(index) == '{') {
            if (set(line)) {
                return index != line.length();
            }
        } else if (line.charAt(index) == 'n') {
            index++;
            return true;
        }
        return false;
    }

    public static boolean tail(String line){
        if (line.charAt(index) == '}')
            return true;
        else if (line.charAt(index) == ','){
            index++;
            if (head(line))
                return tail(line);
            else return false;
        }
        else return false;
    }
}