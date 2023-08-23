package Arena;

import MyJson.AllPlayer;
import MyJson.AllUnit;
import javafx.util.Pair;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class Arena {
    private Swath arena [][];
    private HashMap<Pair<Integer ,Integer>, Pair<Integer , Integer> > hashMap = new HashMap<>();
    private ZoomIn zoomIn = new ZoomIn(0,0,0,0);

    public Arena (){
        arena = new Swath[10000][10000];
    }
    public Arena (int width_arena , int height_arena){
        arena = new Swath[width_arena][height_arena];
    }

    public boolean addUnitToArena(int id_player ,int id_unit ,int xPosition,int yPosition){
//        JSONObject jsonObject = AllPlayer.getAllPlayerObject().getUnitFromJsonObject(id_player,id_unit);
//        String String_height = String.valueOf( jsonObject.get(AllUnit.HEIGHT));
//        int height = Integer.parseInt(String_height);
//        String String_width = String.valueOf( jsonObject.get(AllUnit.WIDTH));
//        int width = Integer.parseInt(String_width);
//        if (!isAvailableSwath(height,width,xPosition,yPosition)){
//            return false;
//        }
//        intializedSwath(id_player,id_unit,xPosition,yPosition,height,width);
//        zoomIn.editZoom(xPosition,yPosition,height,width);
//        //System.out.println(jsonObject);
        return true;
    }
    private void intializedSwath(int id_player ,int id_unit ,int xPosition,int yPosition,int height , int width){
        for (int i = xPosition; i <= height+xPosition; i++) {
            for (int j = yPosition; j <= width+yPosition; j++) {
                if (arena[i][j] == null)
                    arena[i][j] = new Swath(id_player,id_unit);
                else
                    arena[i][j].setData(id_player,id_unit);
            }
        }
    }
    //مشان عرف اذا فيني اضيف هالوحدة بهلمكان
    private boolean isAvailableSwath(int height , int width ,int xPosition ,int yPosition){
        if( (xPosition<=0 && xPosition>arena.length)||
                (yPosition<=0 && yPosition>arena[0].length) ){
            return false;
        }
        for (int i = xPosition; i <= height+xPosition; i++) {
            for (int j = yPosition; j <= width+yPosition; j++) {
                if (arena[i][j] != null && arena[i][j].getId_player() != 0 && arena[i][j].getId_unit()!=0)
                    return false;
            }
        }
        return true;
    }
    public void clearSwath(int xPosition,int yPosition,int height , int width){
        for (int i = xPosition; i < height+xPosition; i++) {
            for (int j = yPosition; j < width+yPosition; j++) {
                    arena[i][j].setData(0,0);
            }
        }
    }

    // زوم
    private class ZoomIn{
        private int xStart,yStart,xEnd,yEnd;

        public ZoomIn(int xStart, int yStart, int xEnd, int yEnd) {
            this.xStart = xStart;
            this.yStart = yStart;
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }

        public int getxStart() {
            return xStart;
        }

        public int getyStart() {
            return yStart;
        }

        public int getxEnd() {
            return xEnd;
        }

        public int getyEnd() {
            return yEnd;
        }

        public void editZoom(int xPosition, int yPosition, int height , int width){
            if (xPosition<xStart){
                xStart = xPosition - 1;
            }
            if (xPosition+height > xEnd){
                xEnd = xPosition + height + 3;
            }
            if (yPosition < yStart){
                yStart = yPosition -1;
            }
            if (yPosition+width > yEnd){
                yEnd = xPosition + width + 3;
            }
        }
    }


    public void showArena(){

        for (int i = zoomIn.getxStart(); i <zoomIn.getxEnd() ; i++) {
            for (int j = zoomIn.getyStart(); j < zoomIn.getyEnd(); j++) {
                if(arena[i][j] !=null){
                    System.out.print(arena[i][j].getId_player() + " ");
                }else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }

}
