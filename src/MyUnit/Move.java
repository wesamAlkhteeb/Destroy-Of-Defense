package MyUnit;

import MyJson.AllPlayer;

import java.util.Collections;

public class Move {

    //Up

    public boolean moveUp(int index){
        if(testUp(index)){
            moveWithSortLinkedListUp(index);
            return true;
        }
        return false;
    }

    private boolean testUp(int index){
        int x = getXFromLinkedList(index);
        if(x==1){
            return false;
        }
        int y = getYFromLinkedList(index);
        int size = getSizeFromAllUnit(getID_PFromLinkedList(index) , getID_UFromLinkedList(index));
        x--;

        searchIndex(size,x,y);

        for (int i = fxsearch; i <=lysearch ; i++) {
            if(testUnit(x,y,size,i)&& i != index){
                return false;
            }
        }

        return true;
    }

    private boolean testUnit(int x_move, int y_move, int size_move, int i) {
        for (int j = getXFromLinkedList(i); j <=getXFromLinkedList(i)+getSizeFromAllUnit(getID_PFromLinkedList(i) , getID_UFromLinkedList(i))-1 ; j++) {
            for (int k = getYFromLinkedList(i); k <=getYFromLinkedList(i)+getSizeFromAllUnit(getID_PFromLinkedList(i) , getID_UFromLinkedList(i))-1 ; k++) {
                if (j>=x_move && j<=x_move+size_move-1 && k>y_move && k<=y_move+size_move-1){
                    return true;
                }
            }
        }
        return false;
    }

    private void moveWithSortLinkedListUp(int index){
        int i = index;
        DSUnit.getInstance().getLinkedLists().get(index).minusX();
        while (i>-1){
            if(getXFromLinkedList(i)>getXFromLinkedList(index) ||
                    (getXFromLinkedList(i)==getXFromLinkedList(index)&&
                            getYFromLinkedList(i)>getYFromLinkedList(index))){
                break;
            }
            i--;
        }
        System.out.println(index  + " " +i );
        if(i != -1){
            Collections.swap(DSUnit.getInstance().getLinkedLists() , index ,
                    i);
        }
    }

    //Up Right

    public boolean moveUpLeft(int index){
        if(testUpLeft(index)){
            moveWithSortLinkedListUpLeft(index);
            return true;
        }
        return false;
    }
    private boolean testUpLeft(int index){
        int x = getXFromLinkedList(index);
        int y = getYFromLinkedList(index);
        if(x==1 || y==1){
            return false;
        }
        int size = getSizeFromAllUnit(getID_PFromLinkedList(index) , getID_UFromLinkedList(index));
        x--;

        searchIndex(size,x,y);

        for (int i = fxsearch; i <=lysearch ; i++) {
            if(testUnit(x,y,size,i)&& i != index){
                return false;
            }
        }

        return true;
    }

    private void moveWithSortLinkedListUpLeft(int index){
        int i = index-1;
        DSUnit.getInstance().getLinkedLists().get(index).minusX();
        DSUnit.getInstance().getLinkedLists().get(index).minusY();
        while (i>-1){
            if(getXFromLinkedList(i)>getXFromLinkedList(index) ||
                    (getXFromLinkedList(i)==getXFromLinkedList(index)&&
                            getYFromLinkedList(i)>getYFromLinkedList(index))){
                break;
            }
            i--;
        }
        Collections.swap(DSUnit.getInstance().getLinkedLists() , index , i);
    }




    //from DSUnit
    private int getXFromLinkedList (int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getX();
    }
    private int getYFromLinkedList (int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getY();
    }
    private int getID_PFromLinkedList (int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getId_p();
    }
    private int getID_UFromLinkedList (int index){
        return DSUnit.getInstance().getLinkedLists().get(index).getId_u();
    }

    //A
    private int getSizeFromAllUnit(int id_p , int id_u){
        return AllPlayer.getAllPlayerObject().getSize(id_p,id_u);
    }

    private int maxRadius =0;
    private int fxsearch , lxsearch;
    private int fysearch , lysearch;
    private void searchIndex (int radius , int x , int y){
        fxsearch = 0;
        lxsearch = DSUnit.getInstance().getLinkedLists().size()-1;
        while ((DSUnit.getInstance().getLinkedLists().get(fxsearch).getX() < x-maxRadius &&  fxsearch <lxsearch) ){
            fxsearch++;
        }
        while (DSUnit.getInstance().getLinkedLists().get(lxsearch).getX() > x + radius &&  fxsearch <lxsearch){
            lxsearch--;
        }

        fysearch = fxsearch;
        lysearch = lxsearch;

        while (DSUnit.getInstance().getLinkedLists().get(fysearch).getX() != x && fysearch<lysearch){
            fysearch++;
        }
        while (DSUnit.getInstance().getLinkedLists().get(lysearch).getX() != x&& fysearch<lysearch){
            lysearch--;
        }


        while (DSUnit.getInstance().getLinkedLists().get(fysearch).getY() < y-maxRadius && fysearch <lysearch){
            fysearch++;
        }
        while (  DSUnit.getInstance().getLinkedLists().get(lysearch).getY() > y +radius && fysearch<lysearch){
            lysearch--;
        }
    }

}
