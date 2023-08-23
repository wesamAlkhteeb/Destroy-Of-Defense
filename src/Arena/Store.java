package Arena;

import MyJson.AllPlayer;
import MyJson.AllUnit;
import MyJson.TeamPlayer;
import org.json.simple.JSONObject;

public class Store {
    private JSONObject jsonObject;

    public Store(){
        jsonObject = new JSONObject();
    }

    public boolean buyUnit(int id_player , int id_unit){
        jsonObject.clear();
        jsonObject = AllPlayer.getAllPlayerObject().getJsonPlayerObject(id_player);
        String Stringmoney = String.valueOf( jsonObject.get(AllPlayer.PLAYER_MONEY));
        float money = Float.parseFloat(Stringmoney);
        jsonObject.clear();
        jsonObject = AllUnit.getAllUnitObject().getDetailUnit(
                AllUnit.getAllUnitObject().getUnits_in_game()[id_unit]);
        String StringPrice = String.valueOf(jsonObject.get(AllUnit.PRICE));
        float price = Float.parseFloat(StringPrice);
        if( money < price ){
            return false;
        }
        money -= price;
        AllPlayer.getAllPlayerObject().addUnitToPlayer(jsonObject);
        jsonObject.clear();
        jsonObject = AllPlayer.getAllPlayerObject().getJsonPlayerObject(id_player);

        jsonObject.put(AllPlayer.PLAYER_MONEY , money);
        AllPlayer.getAllPlayerObject().editInformationPlayer(id_player,money);
        return true;
    }

}
