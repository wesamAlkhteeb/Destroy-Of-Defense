package Map;

import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Valley {
    private ArrayList<position> positions ;
    private boolean canPass;

    public Valley() {
        this.positions = new ArrayList<>();
        this.canPass = false;
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
        for (int i = 0; i < positions.size(); i++) {
            jsonArray.add(positions.get(i).getX() + " " + positions.get(i).getY());
        }
        return jsonArray;
    }
    public boolean isCanPass() {
        return canPass;
    }
}
