package Group2.Classes;


public class Book implements Comparable{
    int id;
    int showid;
    String name;
    String seat;
    Float amount;

    public Book(int id, int showid, String name, String seat, Float amount) {
        this.id = id;
        this.showid = showid;
        this.name = name;
        this.seat = seat;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
        this.showid = showid;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
    
    public void print(){
        System.out.format("%-11d%-6s%-1.1f\n", id, name, amount);
    }
    
    @Override
    public int compareTo(Object i){
        if (this.getId() > ((Book) i).getId()) {
            return 1;
        } else {
            return -1;
        }
    }
}
