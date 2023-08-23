package com.company;

import MyJson.AllPlayer;
import MyJson.AllUnit;
import MyUnit.DSUnit;
import MyUnit.Move;
import com.sun.org.apache.bcel.internal.generic.SWAP;
import present.Console;
import present.Game;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
//        DSUnit dsUnit = DSUnit.getInstance();
//        dsUnit.addUnit(1, 2, 2, 2);
//        dsUnit.addUnit(2, 2, 8, 8);
//
//        Move move = new Move();
//        Game game = new Console();
//
//
//
//        while (true){
//            game.printArena();
//            if(new Scanner(System.in).next().charAt(0) == 'a')
//            {
//                System.out.println(move.moveUp(1));
//            }
//
//        }
        System.out.println(AllPlayer.getAllPlayerObject().getRange(1,1));

    }

}


