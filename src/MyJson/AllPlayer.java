package MyJson;

import MyUnit.Unit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sun.awt.windows.WingDings;
import sun.plugin.javascript.JSObject;

import java.io.*;
import java.util.HashMap;

public class AllPlayer {
    public final static String PLAYER_ID = "id" ;
    public final static String PLAYER_NAME = "name" ;
    public final static String PLAYER_MONEY = "money";
    public final static String PLAYER_TEAM = "team";
    public final static String PLAYER_TEAM_ATTACK = "att";
    public final static String PLAYER_TEAM_DEFENSE = "def";
    public final String PATH_PLAYER = "src\\MyJson\\AllPlayerJson\\";
    public final String PATH_UNIT_PLAYER = "src\\MyJson\\AllPlayer_UnitJson\\";
    private static int id_player ;
    private static int id_unit ;
    private JSONObject jsonObject ;
    private final JSONParser jsonParser ;
    private final float  money = 3000;
    private static AllPlayer allPlayer;


    private AllPlayer (){
        jsonObject = new JSONObject();
        jsonParser = new JSONParser();
        id_player=0;
        id_unit=0;
    }

    public static AllPlayer getAllPlayerObject (){
        if(allPlayer == null ){
            allPlayer = new AllPlayer();
        }
        return allPlayer;
    }

    public void addPlayer(String name , TeamPlayer teamPlayer){
        id_player++;
        id_unit=0;
        addInfoPlayerToJsonObject(name , teamPlayer);
        jsonPush(this.PATH_PLAYER + id_player+".json");

    }

    private void addInfoPlayerToJsonObject(String name , TeamPlayer teamPlayer){
        jsonObject.clear();
        HashMap<String , String> json = new HashMap<>();
        json.put(this.PLAYER_ID ,String.valueOf( id_player));
        json.put(this.PLAYER_NAME , name);
        json.put(this.PLAYER_MONEY , String.valueOf(this.money));
        json.put(this.PLAYER_TEAM , teamPlayer==TeamPlayer.att ?PLAYER_TEAM_ATTACK
                 : PLAYER_TEAM_DEFENSE);
        jsonObject.putAll(json);
    }

    private void jsonPush(String path){
        try (FileWriter fileWriter = new FileWriter(path)){
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public JSONObject getJsonPlayerObject(int id_p){
        jsonObject.clear();
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader(PATH_PLAYER+id_p+".JSON")){
            jsonObject = (JSONObject) parser.parse(fileReader);
        }catch (Exception e){
            System.out.println(e);
        }
        jsonObject.put(PLAYER_TEAM ,
                jsonObject.get(PLAYER_TEAM).equals(PLAYER_TEAM_ATTACK) ? TeamPlayer.att:TeamPlayer.def);
        jsonObject.put(PLAYER_MONEY ,Float.parseFloat(String.valueOf(jsonObject.get(PLAYER_MONEY))));
        return jsonObject;
    }


    public void addUnitToPlayer( JSONObject new_unit){
        id_unit++;
        this.jsonObject = new_unit;
        jsonPush(this.PATH_UNIT_PLAYER + id_player + "_" + id_unit + ".json");
    }


    public String [] getUnitsGame(){
        File file = new File(PATH_UNIT_PLAYER);
        String a [] = file.list();
        for (int i = 0; i < a.length; i++) {
            a[i] = a[i].substring(0,a[i].length()-5);
        }
        return a;
    }




    public JSONObject getUnitFromJsonObject(int id_p , int id_u){
        jsonObject.clear();
        try {
            Object o = jsonParser.parse(new FileReader(this.PATH_UNIT_PLAYER+ id_p+"_"+ id_u+".json"));
            jsonObject = (JSONObject)o;
        } catch (ParseException | IOException e) {
            System.out.println(e);
        }
        return jsonObject;
    }

    public void editInformationPlayer(int id_player , float money){
        this.jsonObject.clear();
        jsonObject = getJsonPlayerObject(id_player);
        jsonObject.put(PLAYER_MONEY , String.valueOf(money));
        jsonObject.put(PLAYER_TEAM , jsonObject.get(PLAYER_TEAM)==TeamPlayer.att?PLAYER_TEAM_ATTACK:PLAYER_TEAM_DEFENSE);
        jsonPush(this.PATH_PLAYER + id_player+".json");
    }

    // I want

    public int getSize(int id_p , int  id_u){
        return Integer.parseInt(String.valueOf(getUnitFromJsonObject(id_p,id_u).get(AllUnit.SIZE)));
    }

    public int getRange(int id_p , int  id_u){
        return Integer.parseInt(String.valueOf(getUnitFromJsonObject(id_p,id_u).get(AllUnit.ATTACK_RANGE)));
    }
}
