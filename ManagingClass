package Group.Main;

import Group2.ClassLists.*;
import java.io.IOException;

/**
 *
 * @author TranVu
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();

        int choice = Integer.MAX_VALUE;
        do {
            System.out.println("Workshop 8:\n"
                    + "HE153359, Trần Quang Vũ\n"
                    + "HE150552, Phan Tuấn Minh\n"
                    + "HE150217, Trần Hoàng Lân\n"
                    + "HE153231, Nguyễn Cao Huy Hoàng\n\n"
                    + "1. Register a film\n"
                    + "2. Register a show\n"
                    + "3. Book seats for a show\n"
                    + "0. Exit");
            choice = Utility.getInt("Enter an option", 0, 3);
            switch (choice) {
                case 1:
                    m.regF();
                    m.pause();
                    break;
                case 2:
                    m.regS();
                    m.pause();
                    break;
                case 3:
                    m.bookSeat();
                    m.pause();
                    break;
            }
        } while (choice != 0);
    }

    void regF() throws IOException {
        FilmList film_list = new FilmList();
        film_list.addFromFile("data\\Films.txt");
        film_list.addNewFilm();
        film_list.display();
    }

    void regS() throws IOException {
        ShowList show_list = new ShowList();
        show_list.addFromFile("data\\Shows.txt");
        FilmList film_list = new FilmList();
        film_list.addFromFile("data\\Films.txt");
        show_list.addNewShow(film_list);
        show_list.display();
    }

    void bookSeat() throws IOException{
        BookList book_list = new BookList();
        book_list.addFromFile("data\\bookings.txt");
        ShowList sl = new ShowList();
        sl.addFromFile("data\\Shows.txt");
        book_list.bookSeat(sl);
        book_list.book_display();
        book_list.saveToFile("data\\bookings.txt");
    }
    
    void pause() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } 
        catch (IOException e) {}
    }
}

