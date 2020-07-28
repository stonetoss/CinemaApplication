package Group.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Utility {

    static Scanner sc = new Scanner(System.in);

    public static boolean getYesNo(String msg){
        System.out.print(msg + " (y/n)?: ");
        String opt = sc.nextLine();
        
        while(true){
            if("y".equals(opt) || "n".equals(opt) || "Y".equals(opt) || "N".equals(opt))
                break;
            System.out.print("Wrong input!\n" + msg + " (y/n)?: ");
            opt = sc.nextLine();
        }
        switch(opt){
            case "N":
            case "n":
                return false;
            default:
                return true;
        }
    }
    
    public static String getString(String msg){
        String input = "";
        do{
            try{
            System.out.print(msg + ": ");
            input = sc.nextLine();
            }
            catch(Exception e){
                System.out.println("Invalid input!");
            }
        }
        while(input == null);
        return input;
    }
    
    public static int getInt(String msg, int min, int max) {
        System.out.print(msg + ": ");
        int input = Integer.MAX_VALUE;
        try {
            input = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {}

        while (input < min || input > max) {
            System.out.println("Error! Choose a valid option!");
            System.out.print(msg + ": ");
            try {
                input = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {}
        }
        return input;
    }

    public static Float getFloat(String msg, int min, int max) {
        System.out.print(msg + ": ");
        Float input = Float.MAX_VALUE;
        try {
            input = Float.parseFloat(sc.nextLine());
        } catch (NumberFormatException e) {
        }

        while (input < min || input > max) {
            System.out.println("Error! Choose a valid option!");
            System.out.print(msg + ": ");
            try {
                input = Float.parseFloat(sc.nextLine());
            } catch (NumberFormatException e) {}
        }
        return input;
    }

    public static String getDate(String msg){
        String date = "";
        boolean valid = true;
        do{
            try{
                System.out.print(msg + ": ");
                date = sc.nextLine();
                valid = date.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
                StringTokenizer stk = new StringTokenizer(date, "/");
                int day = Integer.parseInt(stk.nextToken());
                int month = Integer.parseInt(stk.nextToken());
                int year = Integer.parseInt(stk.nextToken());
                if(day < 0 || day > 31 || month < 0 || month > 12) {
                    System.out.println("Wrong date format!!");
                    valid = false;
                    continue;
                }
                if(month == 2 && day > 28){
                    System.out.println("Febuary only has 28 days");
                    valid = false;
                } else if(month == 4 || month == 6 || month == 9 || month == 11){
                    if(day > 30){
                        System.out.println("31 days in a 30 day-month error!");
                        valid = false;
                    }
                }
            }
            catch(Exception e){
                System.out.println("Wrong date format!!");
            }
        }
        while(!valid);
        return date;
    }
    
    public void displayGenres() throws IOException{
        File f = new File("data\\Genres.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bf = new BufferedReader(fr);
        System.out.println("List of genres:");
        System.out.println("GenreID    Name");
        String details;
        while ((details = bf.readLine()) != null) {
            StringTokenizer stk = new StringTokenizer(details, "\t");
            int id = Integer.parseInt(stk.nextToken());
            String name = stk.nextToken();
            // print line
            System.out.format("%-10d %-1s\n", id, name);
        }
        bf.close(); bf.close();
    }
    
    public void displayCountries() throws IOException{
        File f = new File("data\\Countries.txt");
        BufferedReader bf;
        try (FileReader fr = new FileReader(f)) {
            bf = new BufferedReader(fr);
            System.out.println("The list of counties:");
            System.out.println("Country code   Country name");
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, "\t");
                String code = stk.nextToken();
                String name = stk.nextToken();
                // print line
                System.out.format("%-15s%-1s\n", code, name);
            }
        }
        bf.close();
    }
    
}
