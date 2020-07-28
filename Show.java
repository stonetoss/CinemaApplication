package Group2.Classes;

public class Show implements Comparable{
    int showID;
    int filmID;
    int roomID;
    String date;
    Float price;
    int slot;

    public Show(int showID, int filmID, int roomID, String date, Float price, int slot) {
        this.showID = showID;
        this.filmID = filmID;
        this.roomID = roomID;
        this.date = date;
        this.price = price;
        this.slot = slot;
    }

    public int getShowID() {
        return showID;
    }

    public void setShowID(int showID) {
        this.showID = showID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
    
    public void print(){
        System.out.format("%-11d%-11d%-11d%-15s%-10.1f%-1d\n", showID, filmID, roomID, date, price, slot);
    }
    
    @Override
    public int compareTo(Object i){
        if (this.getShowID() > ((Show) i).getShowID()) {
            return 1;
        } else {
            return -1;
        }
    }
}
