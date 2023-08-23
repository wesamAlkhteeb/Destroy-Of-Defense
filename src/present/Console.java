package present;

import Arena.*;
import MyJson.*;
import MyUnit.DSUnit;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Console extends Game{
    private Scanner in ;
    public Console(){
        store = new Store();
        arena = new Arena();
        in = new Scanner(System.in);
        allPlayerObject = AllPlayer.getAllPlayerObject();
        allUnitObject = MyJson.AllUnit.getAllUnitObject();
    }

    @Override
    public void IntializedPlayer() {
        System.out.println("Enter number Player :");
        int number_player = in.nextInt();
        String name;
        for (int i = 0; i < number_player ; i++) {
            System.out.println("Enter your name :");
            name = new Scanner(System.in).next();
            System.out.println("what your team ?? \n1- Attack. \n2- Defense. ");
            int team = in.nextInt();
            team =(team!=1 && team!=2)?1:team;
            allPlayerObject.addPlayer(name ,team==1?TeamPlayer.att:TeamPlayer.def);
            System.out.println("choose Unit ...");
            int n = in.nextInt();
            while ( n!= -1 && n>=1 &&n<=AllUnit.getAllUnitObject().getUnits_in_game().length){
                if (!store.buyUnit(i+1 , n-1)){
                    break;
                }
                n = in.nextInt();
            }
        }
        setPositionUnit();
        arena.showArena();
    }
    public void setPositionUnit(){
        String A[] = AllPlayer.getAllPlayerObject().getUnitsGame();
        for (int i=0 ; i<A.length ; i++){
            String info[] = A[i].split("_",2);
            System.out.println("set position Unit" + info[1] + " own Player" + info[0] );
            int xP = in.nextInt() , yp = in.nextInt();
            while (!DSUnit.getInstance().addUnit(Integer.parseInt(info[0]) , Integer.parseInt(info[1] ), xP , yp)){
                System.out.println("error");
                xP = in.nextInt() ; yp = in.nextInt();
            }
        }
        LinkedList<DSUnit.InfoDS> linkedList =  DSUnit.getInstance().getLinkedLists();
        for (int i = 0; i < DSUnit.getInstance().getLinkedLists().size() ; i++) {
            System.out.println(linkedList.get(i).getX() + " " + linkedList.get(i).getY()  );
        }
    }

    private ArrayList<Info> queue = new ArrayList<>();
    public void printArena() {
        int index =0;
        queue.clear();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 15; j++) {
                if (getXFromDSUnit(index ) == i && getyFromDSUnit(index) == j){
                    int size =  AllPlayer.getAllPlayerObject().getSize(getId_pFromDSUnit(index),getId_uFromDSUnit(index) );
                    if(size>1){
                        queue.add(new Info(i+1,j , 2 , size , index));
                    }
                    j=printWidthUnit(j , getyFromDSUnit(index) ,size, getId_pFromDSUnit(index));
                    index = (index < DSUnit.getInstance().getLinkedLists().size()-1)?index+1:index;
                }else if(queue.size()>0 && queue.get(0).getX() == i && queue.get(0).getY() == j) {
                    int index1 = queue.get(0).getIndex();
                    int size = AllPlayer.getAllPlayerObject().getSize(getId_pFromDSUnit(index1),getId_uFromDSUnit(index1) );
                    if(queue.get(0).getCount() < queue.get(0).getMax()){
                        queue.add(new Info(i+1,j , queue.get(0).getCount()+1 , size , index1));
                    }
                    queue.remove(0);
                    j=printWidthUnit(j , getyFromDSUnit(index1) ,size, getId_pFromDSUnit(index1));
                }
                else {
                    System.out.print("D ");

                }
            }
            System.out.println();
        }
    }

    private int getXFromDSUnit(int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getX();
    }
    private int getyFromDSUnit(int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getY();
    }
    private int getId_pFromDSUnit(int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getId_p();
    }
    private int getId_uFromDSUnit(int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getId_u();
    }

    //to print
    private int printWidthUnit(int j , int yUnit , int size , int id_p){
        for (; j <yUnit + size ; j++) {
            System.out.print(id_p + " ");
        }
        return j-1;
    }
    private class Info {
        int x, y, count, max, index;

        public int getX() {
            return x;
        }


        public int getY() {
            return y;
        }


        public int getCount() {
            return count;
        }

        public Info(int x, int y, int count, int max, int index) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.max = max;
            this.index = index;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getMax() {
            return max;
        }


    }
}
