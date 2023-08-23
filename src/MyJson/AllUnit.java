package MyJson;

import MyUnit.Unit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class AllUnit {
    public static final String MAINBASE = "main base";
    public final String HEALTH = "health";
    public final String ARM = "arm";
    public final static String SIZE = "size";
    public final String ATTACK_Damage = "attack damage";
    public final String ATTACK_FREQ = "attack freq";
    public final static String ATTACK_RANGE = "attack range";
    public final String MOVE_SPEED = "move speed";
    public final String UNDER_ATTACK = "under attack";
    public final static String PRICE = "price";
    public final static String TYPE = "type";

    private final JSONObject jsonObject ;
    private JSONArray jsonArray ;
    private JSONParser jsonParser ;
    private static AllUnit allUnit;
    private String[] units_in_game;

    public final static String TANK = "tank";
    public final static String SOLIDER = "solider";
    public final static String STRUCTURE = "structure";
    public final static String AIRPLANE = "airplane";

    public String[] getUnits_in_game() {
        return units_in_game;
    }

    private AllUnit (){
        jsonObject = new JSONObject();
        jsonArray = new JSONArray();
        jsonParser = new JSONParser();
        units_in_game = this.getUnitsGame();
    }

    public static AllUnit getAllUnitObject(){
        if(allUnit == null){
            allUnit = new AllUnit();
        }
        return allUnit;
    }


    public void addUnitToGame(Unit unit, String nameUnit){

        jsonObject.putAll(getHashMap(unit));
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < unit.getUnder_att().length; i++) {
            jsonArray.add(unit.getUnder_att()[i]);
        }
        jsonObject.put(UNDER_ATTACK , jsonArray);

        try (FileWriter fileWriter = new FileWriter("src\\MyJson\\AllUnitJson\\"+nameUnit+".json")){
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private HashMap<String , Object> getHashMap(Unit unit){
        HashMap<String , Object> hashMap = new HashMap<>();
        hashMap.put(HEALTH,unit.getHealth());
        hashMap.put(ARM,unit.getArm());
        hashMap.put(SIZE ,unit.getSize());
        hashMap.put(ATTACK_Damage ,unit.getAtt_damage());
        hashMap.put(ATTACK_FREQ ,unit.getAtt_freq());
        hashMap.put(ATTACK_RANGE ,unit.getAtt_range());
        hashMap.put(MOVE_SPEED ,unit.getMove_speed());
        hashMap.put(PRICE ,unit.getPrice());
        hashMap.put(TYPE ,unit.getType());
        return hashMap;
    }


    public JSONObject getDetailUnit(String nameUnitee){
        jsonObject.clear();
        Object o = null;
        try {
            FileReader fileReader = new FileReader("src\\MyJson\\AllUnitJson\\"+nameUnitee+".json");
            o = jsonParser.parse(fileReader);
            fileReader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return (JSONObject)o;
    }

    public String [] getUnitsGame(){
        File file = new File("src\\MyJson\\AllUnitJson");
        String a [] = file.list();
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i].substring(0,a[i].length()-5);
        }
        return a;
    }

}
