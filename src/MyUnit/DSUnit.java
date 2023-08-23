package MyUnit;


import MyJson.AllPlayer;
import MyJson.AllUnit;

import java.util.LinkedList;

public class DSUnit {


    private final int widthMap;
    private final int heightMap;

    public static class InfoDS {
        private int x, y, Id_p, Id_u;

        public int getX() {
            return x;
        }

        public void plusX(){
            x++;
        }
        public void plusY(){
            y++;
        }
        public void minusX(){
            x--;
        }
        public void minusY(){
            y--;
        }
        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getId_p() {
            return Id_p;
        }

        public void setId_p(int id_p) {
            Id_p = id_p;
        }

        public int getId_u() {
            return Id_u;
        }

        public void setId_u(int id_u) {
            Id_u = id_u;
        }

        public InfoDS(int x, int y, int id_p, int id_u) {
            this.x = x;
            this.y = y;
            Id_p = id_p;
            Id_u = id_u;
        }
    }
    LinkedList<InfoDS> linkedLists;
    private static DSUnit dsUnit;

    private DSUnit() {
        linkedLists = new LinkedList<>();
        maxRadius = 0;
        this.widthMap = 1000;
        this.heightMap = 1000;

    }

    public LinkedList<InfoDS> getLinkedLists() {
        return linkedLists;
    }

    public static DSUnit getInstance() {
        if (dsUnit == null) {
            dsUnit = new DSUnit();
        }
        return dsUnit;
    }

    // Operation Add
    public boolean addUnit (int id_p , int  id_u , int xP , int yP){
        if (linkedLists.size()==0  ){
            if((xP+Integer.parseInt(String.valueOf(AllPlayer.getAllPlayerObject().getUnitFromJsonObject(
                    id_p , id_u).get(AllUnit.SIZE))) > heightMap || yP + Integer.parseInt(String.valueOf(AllPlayer.getAllPlayerObject().getUnitFromJsonObject(
                    id_p , id_u).get(AllUnit.SIZE))) > widthMap)){
                return false;
            }else {
                linkedLists.add(new InfoDS(xP, yP, id_p, id_u));
                return true;
            }
        }
        searchIndex(Integer.parseInt(String.valueOf(AllPlayer.getAllPlayerObject().getUnitFromJsonObject(id_p,id_u).get(AllUnit.SIZE))),
                xP ,yP);
        for (int i = fxsearch; i <=lxsearch ; i++) {
            if (!testAdd( xP ,  yP ,
                    Integer.parseInt(String.valueOf(AllPlayer.getAllPlayerObject().getUnitFromJsonObject(
                    id_p , id_u).get(AllUnit.SIZE) ))  , i )){
                return false;
            }
        }
        int index_add_list=fxsearch;
        for (int i = fxsearch; i <=lxsearch ; i++) {
            if(linkedLists.get(i).getX()<xP){
                index_add_list = i+1;
            }
            if (linkedLists.get(i).getX()==xP && linkedLists.get(i).getY()<yP){
                index_add_list = i+1;
            }
        }
        if (maxRadius < Integer.parseInt(String.valueOf(AllPlayer.getAllPlayerObject().getUnitFromJsonObject(id_p,id_u).get(AllUnit.SIZE)))){
            maxRadius = Integer.parseInt(String.valueOf(AllPlayer.getAllPlayerObject().getUnitFromJsonObject(id_p,id_u).get(AllUnit.SIZE)));
        }
        linkedLists.add(index_add_list , new InfoDS(xP , yP , id_p , id_u));

        return true;
    }

    private boolean testAdd( int xP, int yP,int sizeAdd, int index) {
        if (xP+sizeAdd > heightMap || yP + sizeAdd > widthMap || xP<1 ||yP<1){
            return false;
        }

        int sizeUnitInList = Integer.parseInt(String.valueOf(AllPlayer.getAllPlayerObject().getUnitFromJsonObject(
                linkedLists.get(index).getId_p() , linkedLists.get(index).getId_u() ).get(AllUnit.SIZE) ));
        for (int i = linkedLists.get(index).getX(); i <= linkedLists.get(index).getX() + sizeUnitInList-1; i++) {
            for (int j = linkedLists.get(index).getY(); j <= linkedLists.get(index).getY() + sizeUnitInList-1; j++) {

                if(i>=xP && i<= xP+sizeAdd-1 && j>=yP && j<=yP+sizeAdd-1){
                    return false;
                }
            }
        }

        return true;
    }

    private int maxRadius =0;
    private int fxsearch , lxsearch;
    private int fysearch , lysearch;

    private void searchIndex (int radius , int x , int y){
        fxsearch = 0;
        lxsearch = linkedLists.size()-1;
        while ((linkedLists.get(fxsearch).getX() < x-maxRadius &&  fxsearch <lxsearch) ){
            fxsearch++;
        }
        while (linkedLists.get(lxsearch).getX() > x + radius &&  fxsearch <lxsearch){
            lxsearch--;
        }

        fysearch = fxsearch;
        lysearch = lxsearch;

        while (linkedLists.get(fysearch).getX() != x && fysearch<lysearch){
            fysearch++;
        }
        while (linkedLists.get(lysearch).getX() != x&& fysearch<lysearch){
            lysearch--;
        }


        while (linkedLists.get(fysearch).getY() < y-maxRadius && fysearch <lysearch){
            fysearch++;
        }
        while (  linkedLists.get(lysearch).getY() > y +radius && fysearch<lysearch){
            lysearch--;
        }
    }
}