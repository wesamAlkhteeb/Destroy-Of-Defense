package present;

import Arena.*;
import MyJson.AllPlayer;
import MyJson.AllUnit;


public abstract class Game {
    protected Arena arena;
    protected AllUnit allUnitObject;
    protected AllPlayer allPlayerObject;
    protected Store store;

    public abstract void IntializedPlayer();
    public abstract void setPositionUnit();
    public abstract void printArena();

}
