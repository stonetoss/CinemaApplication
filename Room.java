package Group2.Classes;

/**
 *
 * @author TranVu
 */
public class Room {
    
    int roomid;
    String name;
    int row, col;
    String[][] seat = new String[row][col];

    public Room(int roomid, String name, int row, int col) {
        this.roomid = roomid;
        this.name = name;
        this.row = row;
        this.col = col;
    }
    
    public int getRoomid() {
        return roomid;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void resetSeat(){
        for(int i=0;i<row;i++)
            for (int j=0;j<col;j++)
                seat[i][j] = "0";
    }
    
    public void print(){
        System.out.format("%-9d%-11s%-11d%-1d\n", this.getRoomid(), this.getName(), this.getRow(), this.getCol());
    }
    
}

