package Group2.ClassLists;

import Group2.Classes.Film;
import Group.Main.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

public class FilmList extends Vector<Film> {

    Scanner sc = new Scanner(System.in);
    Utility u = new Utility();
    int MAX_fid = 0;

    public FilmList() {
        super();
    }

    public void addFromFile(String fName) throws FileNotFoundException, IOException {
        try {
            File f = new File(fName);
            if (!f.exists()) {
                return;
            }
            try (FileReader fr = new FileReader(f); BufferedReader bf = new BufferedReader(fr)) {
                String details;
                while ((details = bf.readLine()) != null) {
                    // take info from file
                    StringTokenizer stk = new StringTokenizer(details, "\t");
                    int fid = Integer.parseInt(stk.nextToken());
                    if (fid > MAX_fid) {
                        MAX_fid = fid;
                    }
                    int gid = Integer.parseInt(stk.nextToken());
                    String title = stk.nextToken();
                    int year = Integer.parseInt(stk.nextToken());
                    String country = stk.nextToken();

                    // create a film
                    Film ftmp = new Film(fid, gid, title, year, country);
                    // add film to list
                    this.add(ftmp);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    public void saveToFile(String fName) {
        if (this.isEmpty()) {
            System.out.println("Empty list!!");
            return;
        }
        try {
            File f = new File(fName);
            try (FileWriter fw = new FileWriter(f)) {
                for (Film x : this) {
                    fw.append(x.getFilmID() + "\t" + x.getGenreID() + "\t" + x.getTitle() + "\t" + x.getYear() + "\t" + x.getContry() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void addNewFilm() throws FileNotFoundException, IOException {
        try {

            // print genre list
            u.displayGenres();
            // choose genre
            int newGenreID = Utility.getInt("Genre ID", 1, 11);
            System.out.println();

            // print country list
            u.displayCountries();
            // input country code
            boolean valid = true;
            String newCountryCode;
            int pos = -1;
            do {
                System.out.print("Country code (3 letters): ");
                newCountryCode = sc.nextLine().toUpperCase();
                valid = newCountryCode.matches("^[a-zA-Z]{3}$");
                pos = this.checkCoutryCode(newCountryCode);

                if (!valid) {
                    System.out.println("3 LETTERS ONLY!!");
                } else if (pos < 0) {
                    System.out.println("Code does not exist!!");
                }
            } while (!valid || pos < 0);

            // choose name & year
            System.out.print("Enter title (required): ");
            String newTitle = sc.nextLine();
            int newYear = Utility.getInt("Enter year (1000 – 3000)", 1000, 3000);
            System.out.println();

            // append new film
            Film newFilm = new Film((MAX_fid + 1), newGenreID, newTitle, newYear, newCountryCode);
            this.add(newFilm);
            saveToFile("data\\Films.txt");

        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    public void display() {
        if (this.isEmpty()) {
            System.out.println("Empty list!!");
            return;
        }
        Collections.sort(this);
        System.out.format("\n%-7s%-8s%-20s%-5s%7s\n", "FilmID", "GenreID", "Title", "Year", "Country");
        this.forEach((i) -> {
            i.print();
        });
    }

    public int checkCoutryCode(String aCode) throws FileNotFoundException, IOException {
        File f = new File("data\\Countries.txt");
        FileReader fr = new FileReader(f);
        BufferedReader bf = new BufferedReader(fr);
        String details;
        while ((details = bf.readLine()) != null) {
            StringTokenizer stk = new StringTokenizer(details, "\t");
            String code = stk.nextToken();
            if (code.equals(aCode)) {
                return 1;
            }
            String name = stk.nextToken();
        }
        return -1;
    }
}
