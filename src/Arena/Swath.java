package Arena;

public class Swath {
    private int id_player , id_unit;

    public Swath(int id_player, int id_unit) {
        this.id_player = id_player;
        this.id_unit = id_unit;
    }
    public void setData(int id_player , int id_unit){
        this.id_player = id_player ;
        this.id_unit = id_unit ;
    }

    public int getId_player() {
        return id_player;
    }

    public int getId_unit() {
        return id_unit;
    }


}
