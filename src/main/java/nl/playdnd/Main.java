package nl.playdnd;

import nl.playdnd.arena.BattleMap;
import nl.playdnd.character.FaceTo;
import nl.playdnd.character.Warrior;
import nl.playdnd.character.Wizard;
import nl.playdnd.arena.ArenaGUI;
import nl.playdnd.dasic.DasicAI;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // Create a battle map
        BattleMap battleMap = new BattleMap(15, 15); // 5x5 grid

        // Dasic.getArena().addElement(3, 3, java.awt.Color);
        Warrior rygar = new Warrior("Rygar", 10, 4);
        System.out.println();
        Wizard gandalf = new Wizard("Gandalf", 8, 2);

        // Place characters on the map
        battleMap.placeCharacter(rygar, 0, 0); // Rygar starts at (0, 0)
        battleMap.placeCharacter(gandalf, 4, 4); // Gandalf starts at (4, 4)

        // Set their positions
        rygar.setPosition(new Point(0, 0));
        gandalf.setPosition(new Point(4, 4));

        // System.out.println("Initial Map:");
        // battleMap.displayMap();

        int sx = 1;
        while ( sx++ < 2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            rygar.setFace(FaceTo.EAST);
            //rygar.getElement().repaint();
            battleMap.moveCharacter(rygar, 2 + sx, 2);
            battleMap.moveCharacter(gandalf, 3 + sx, 3);

            System.out.println("\nAfter Moving:");
            //battleMap.displayMap();
            gandalf.attack(rygar);

            rygar.attack(gandalf);


        } 

    }

}
