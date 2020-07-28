package Group2.Classes;

public class Film implements Comparable {

    int filmID;
    int genreID;
    String title;
    int year;
    String country;

    public Film(int filmID, int genreID, String title, int year, String country) {
        this.filmID = filmID;
        this.genreID = genreID;
        this.title = title;
        this.year = year;
        this.country = country;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContry() {
        return country;
    }

    public void setContry(String contry) {
        this.country = contry;
    }

    public void print() {
        System.out.format("%-7d%-8d%-20s%-5s%7s\n", filmID, genreID, title, year, country);
    }

    @Override
    public int compareTo(Object f) {
        if (this.getFilmID() > ((Film) f).getFilmID()) {
            return 1;
        } else {
            return -1;
        }
    }
}
