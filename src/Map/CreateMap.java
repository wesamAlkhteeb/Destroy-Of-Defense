package Map;

import Arena.Arena;
import MyJson.AllUnit;
import MyUnit.Unit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateMap {
    public final String POSITIONS_RIVER = "position_river";
    public final String SPEED_REDUCED_RIVER = "speed_reduced";
    public final String CAN_PASS_RIVER = "can_pass_river";
    public final String POSITIONS_BRIDGE = "position_bridge";
    public final String CAN_PASS_BRIDGE = "can_pass_bridge";
    public final String POSITIONS_VALLEY = "position_valley";
    public final String CAN_PASS_VALLEY = "can_pass_Valley";
    public final String HEIGHT_MAP = "height map";
    public final String WIDTH_MAP = "width map";
    public final String TYPE_GROUND = "type ground";

    private River river;
    private Bridge bridge;
    private Valley valley;
    private TypeGround ground;
    private JSONObject jsonObject;
    private int width_map , height_map;
    public CreateMap(TypeGround typeGround , int width , int height) {
        river = new River();
        bridge = new Bridge();
        valley = new Valley();
        jsonObject = new JSONObject();
        this.height_map = height;
        this.width_map = width;
        this.ground = typeGround;
    }

    public void addProperties(position p1R, position p2R, int widthR,
                              position p1B, position p2B, int widthB,
                              position p1V, position p2V, int widthV) {
        river.setPosition(p1R, p2R, widthR);
        bridge.setPosition(p1B, p2B, widthB);
        valley.setPosition(p1V, p2V, widthV);
    }

    private HashMap<String, Object> getHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(POSITIONS_RIVER, river.getPositions());
        hashMap.put(SPEED_REDUCED_RIVER, river.getSpeedReduced());
        hashMap.put(CAN_PASS_RIVER, river.isCanPass());
        hashMap.put(POSITIONS_RIVER, bridge.getPositions());
        hashMap.put(CAN_PASS_RIVER, bridge.isCanPass());
        hashMap.put(POSITIONS_RIVER, valley.getPositions());
        hashMap.put(CAN_PASS_RIVER, valley.isCanPass());
        hashMap.put(HEIGHT_MAP , this.height_map);
        hashMap.put(WIDTH_MAP , this.WIDTH_MAP);
        hashMap.put(TYPE_GROUND , this.ground.getTypeOfGround());

        return hashMap;
    }

    public void saveMap(String nameMap) {
        jsonObject.putAll(getHashMap());
        try (FileWriter fileWriter = new FileWriter("src\\Map\\AllMap\\" + nameMap + ".json")) {
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
