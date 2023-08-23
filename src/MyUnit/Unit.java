package MyUnit;

import java.util.ArrayList;

public class Unit {
    private float health;
    private float arm;
    private int size;
    private float att_damage , att_freq;
    private int att_range , move_speed;
    private String[] under_att;
    private float price;
    private String type;

    public Unit(){ }

    public Unit(float health, float arm, int size, float att_damage, float att_freq, int att_range, int move_speed, String[] under_att, String type) {
        this.health = health;
        this.arm = arm;
        this.size = size;
        this.att_damage = att_damage;
        this.att_freq = att_freq;
        this.att_range = att_range;
        this.move_speed = move_speed;
        this.under_att = under_att;
        this.type = type;
    }

    public Unit( String type , float health, float arm, float att_damage ,int att_range , float att_freq ,int size,  int move_speed, String[] under_att, float price) {
        this.health = health;
        this.arm = arm;
        this.size = size;
        this.att_damage = att_damage;
        this.att_freq = att_freq;
        this.att_range = att_range;
        this.move_speed = move_speed;
        this.under_att = under_att;
        this.price = price;
        this.type = type;
    }

    public void IntializedUnit(float health , float arm , int size , float att_damage ,
                               float att_freq , int att_range , int move_speed , String[] under_att ,
                               String type) {
        this.health = health;
        this.arm = arm;
        this.size = size;
        this.att_damage = att_damage;
        this.att_freq = att_freq;
        this.att_range = att_range;
        this.move_speed = move_speed;
        this.under_att = under_att;
        this.price = price;
        this.type = type;
    }


    public void IntializedUnit(float health , float arm , int size , float att_damage ,
                               float att_freq , int att_range , int move_speed , String[] under_att ,
                               float price , String type) {
        this.health = health;
        this.arm = arm;
        this.size = size;
        this.att_damage = att_damage;
        this.att_freq = att_freq;
        this.att_range = att_range;
        this.move_speed = move_speed;
        this.under_att = under_att;
        this.price = price;
        this.type = type;
    }

    public float getHealth() {
        return health;
    }

    public float getArm() {
        return arm;
    }

    public int getSize() {
        return size;
    }

    public float getAtt_damage() {
        return att_damage;
    }

    public float getAtt_freq() {
        return att_freq;
    }

    public int getAtt_range() {
        return att_range;
    }

    public int getMove_speed() {
        return move_speed;
    }

    public String[] getUnder_att() {
        return under_att;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }


    //Action

}
