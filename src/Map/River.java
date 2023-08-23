package Map;

import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class River {
    private ArrayList<position> positions;
    private float speedReduced;
    private boolean canPass;

    public River() {
        this.positions = new ArrayList<>();
        this.canPass = true;
    }

    public void setPosition(position p1,position p2,int width) {
        for (int i = p1.getX(); i <= p2.getX(); i++) {
            for (int j = p1.getY(); j < p1.getY()+width ; j++) {
                positions.add(new position(i,j));
            }
        }
    }



    public JSONArray getPositions() {
        JSONArray jsonArray = new JSONArray();
        System.out.println(positions.size());
        for (int i = 0; i < positions.size(); i++) {
            jsonArray.add(positions.get(i).getX() + " " + positions.get(i).getY());
        }
        System.out.println(jsonArray.toString());
        return jsonArray;
    }

    public float getSpeedReduced() {
        return speedReduced;
    }

    public boolean isCanPass() {
        return canPass;
    }


}
