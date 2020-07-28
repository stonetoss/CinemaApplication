package Group2.ClassLists;

import Group2.Classes.Book;
import Group.Main.Utility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author TranHoangLan
 */
public class BookList extends Vector<Book>{
    
    Scanner sc = new Scanner(System.in);
    int MAX_id = 0, count = 0; // count to calculate price
    Utility u = new Utility();
    String[][] seat = new String[100][100];
    public static final String ANSI_RED_B = "\u001B[41m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_B = "\u001B[42m";
    
    public BookList(){
        super();
    }
    
    public void seat_reset(){
        for (int i=0;i<10;i++)
            for (int j=0;j<10;j++)
                seat[i][j] = "0";
    }
    
    public int addFromString(String s){
        // String -> 1D -> 2D
        char[] array1D = s.toCharArray();
        int k=0, n = 0;
        for (int i=0;i<10;i++)
            for (int j=0;j<10;j++,k++)
                if(array1D[k] == '1'){
                    seat[i][j] = "1";
                    n++;
                }
        return n;  // number of seats
    }
    
    public void removeFromString(String s){
        // String -> 1D -> 2D
        char[] array1D = s.toCharArray();
        int k=0;
        for (int i=0;i<10;i++)
            for (int j=0;j<10;j++,k++)
                if(array1D[k] == '1'){
                    seat[i][j] = "1*";
                }
    }
    
    public void addFromFile(String fName) throws FileNotFoundException, IOException{
        File f = new File(fName);
        if(!f.exists()) return;
        this.seat_reset();
        
        FileReader fr = new FileReader(f);
        BufferedReader bf = new BufferedReader(fr);
        String line;
        while((line = bf.readLine()) != null){
            StringTokenizer stk = new StringTokenizer(line, "\t");
            int book_id = Integer.parseInt(stk.nextToken());
            if(book_id > MAX_id) MAX_id = book_id;
            int show_id = Integer.parseInt(stk.nextToken());
            String name = stk.nextToken();
            String seat = stk.nextToken();
            Float amount = Float.parseFloat(stk.nextToken());
            
            Book b = new Book(book_id, show_id, name, seat, amount);
            this.add(b);
        }
    }
    
    public void book_display(){
        if (this.isEmpty()) {
            System.out.println("Empty list!!");
            return;
        }
        Collections.sort(this);
        System.out.println("\nBooking list:");
        System.out.format("%-11s%-6s%-1s\n", "BookingID", "Name", "Amount");
        this.forEach((i) -> {
            i.print();
        });
    }
    
    public void saveToFile(String fName) throws IOException{
        if (this.isEmpty()) {
            System.out.println("Empty list!!");
            return;
        }
        File f = new File(fName);
        try (FileWriter fw = new FileWriter(f)) {
            for (Book i : this){
                fw.append(i.getId() + "\t" + i.getShowid() + "\t" + i.getName() + "\t" + i.getSeat() + "\t" + i.getAmount() + "\n");
            }
        }
    }
    
    public void seat_print(){
        for (int i=0;i<10;i++){
            for (int j=0;j<10;j++){
                switch (seat[i][j]) {
                    case "1":
                        System.out.print(ANSI_RED_B + " 1 " + ANSI_RESET);
                        break;
                    case "1*":
                        System.out.print(ANSI_GREEN_B + " 1 " + ANSI_RESET);
                        break;
                    default:
                        System.out.print(" " + seat[i][j] + " ");
                        break;
                }
            }
            System.out.println();
        }
    }
    
    public String regSeat(int showid){
        this.seat_reset();
        this.stream().filter((book) -> (book.getShowid() == showid)).forEachOrdered((book) -> {
            this.addFromString(book.getSeat());
        });
        
        // initialize array (make all 0 value)
        char[] array_1D = new char[100];
        for(int i=0;i<100;i++)
            array_1D[i] = '0';
        char[][] array_2D = new char[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++)
                array_2D[i][j] = '0';
        }
        
        int row = -1, col = -1;
        boolean valid = true, next = true;
        do{
            this.seat_print();
            System.out.println("Book a new seat");
            do{
                row = Utility.getInt("Enter row number", 1, 10);
                col = Utility.getInt("Enter column number", 1, 10);
                if("1".equals(seat[row-1][col-1])){
                    valid = false;
                    System.out.println("This seat has been taken! Choose another seat!");
                }
            }
            while(!valid);
            seat[row-1][col-1] = "1*";
            array_2D[row-1][col-1] = '1';
            next = Utility.getYesNo("Book another seat");
        }
        while(next == true);
        this.seat_print();
        // 2D -> 1D -> string
        int tmp_int = 0;
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                array_1D[tmp_int] = array_2D[i][j];
                tmp_int++;
            }
        return String.valueOf(array_1D);
    }
    
    public void bookSeat(ShowList sl) throws IOException{
        while(true){
            String date = Utility.getDate("Enter show date (dd/MM/yyyy)");
            boolean check = sl.getShowInfo(date);
            if(check == false) break;
            int showid = Utility.getInt("Enter show ID", 1, sl.maxID);
            if(sl.check_showID(date, showid)==false){
                System.out.println("Show does not exist!");
                break;
            }
            String name = Utility.getString("Enter customer’s name");
            System.out.println();
            String seat = this.regSeat(showid);
            int n = this.addFromString(seat);
            Float amount = sl.getPrice(showid)*n;
            Book b = new Book((MAX_id+1), showid, name, seat, amount);
            this.add(b);
            break;
        }
    }
    
    public void removeSeat(ShowList sl, BookList bl) throws IOException{
        while(true){
            String date = Utility.getDate("Enter show date (dd/MM/yyyy)");
            boolean check = sl.getShowInfo(date);
            if(check == false) break;
            int showid = Utility.getInt("Enter show ID", 1, sl.maxID);
            System.out.println();
            book_display();
            int bookingid = Utility.getInt("Enter booking ID", 1, bl.MAX_id);
            this.seat_reset();
        this.stream().filter((book) -> (book.getShowid() == showid)).forEachOrdered((book) -> {
            this.addFromString(book.getSeat());
        });
        char[] array_1D = new char[100];
        for(int i=0;i<100;i++)
            array_1D[i] = '0';
        char[][] array_2D = new char[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++)
                array_2D[i][j] = '0';
        }
            System.out.println();
         int checkline=-1, bookingline=-1;
            LineNumberReader reader = null;
        try {
         reader = new LineNumberReader(new FileReader(new File("data\\bookings.txt")));
         while (checkline != bookingid)
         {
            checkline = new Scanner(reader.readLine()).useDelimiter("\\D+").nextInt();
            bookingline++;
         }
        } catch (Exception ex) {
        ex.printStackTrace();
        } finally { 
         if(reader != null){
            try {
               reader.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
        }
        FileInputStream fs= new FileInputStream("data\\bookings.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        for(int i = 0; i < bookingline; ++i)
          br.readLine();
        String s = br.readLine();
        fs.close();
        br.close();
        String [] seats = s.split("\\s+");
        this.removeFromString(seats[3]);
            this.seat_print();
            this.remove(bookingline);
            this.book_display();
            this.seat_reset();
        this.stream().filter((book) -> (book.getShowid() == showid)).forEachOrdered((book) -> {
            this.addFromString(book.getSeat());
        });
            this.seat_print();
            break;
        }
    }
    
}
