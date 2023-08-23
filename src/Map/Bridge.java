package Map;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Bridge {
    private ArrayList<position> positions;
    private boolean canPass;
    private int health;

    public Bridge() {
        this.positions = new ArrayList<>();
        this.health = 500;
        this.canPass = isCanPass();
    }

    public void setPosition(position p1,position p2,int width) {
        for (int i = p1.getX(); i <= p2.getX(); i++) {
            for (int j = p1.getY(); j < p1.getY()+width ; j++) {
                positions.add(new position(i,j));
            }
        }
    }

    public void reduceHealth(int damage){
        this.health -=damage;
    }

    public boolean isCanPass(){
        if (health<=0)
            return false;
        return true;
    }

    public JSONArray getPositions() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < positions.size(); i++) {
            jsonArray.add(positions.get(i).getX() + " " + positions.get(i).getY());
        }
        return jsonArray;
    }
}
