package nl.playdnd;

import nl.playdnd.arena.BattleMap;
import nl.playdnd.character.DnDCharacter;
import nl.playdnd.character.Warrior;
import nl.playdnd.character.Wizard;

import java.awt.*;

import nl.playdnd.global.FaceTo;
import nl.playdnd.global.Util;

public class Main {

    public static void main(String[] args) {
        // Create a battle map
        BattleMap battleMap = new BattleMap(15, 15); // 5x5 grid
        DnDCharacter.battleMap = battleMap;

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
        while (sx++ < 2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            rygar.setFaceTo(FaceTo.EAST);
            // rygar.getElement().repaint();
            battleMap.moveCharacter(rygar, 5 + sx, 10);
            battleMap.moveCharacter(gandalf, 3 + sx, 6);

            System.out.println("\nAfter Moving:");
            // battleMap.displayMap();
            gandalf.attack(rygar);
            rygar.attack(gandalf);

        }

        
        System.out.println("");
        rygar.interpret();
        nl.playdnd.global.Util.sleep(1000);
        System.out.println("");
        gandalf.interpret();
        nl.playdnd.global.Util.sleep(1000);
        System.out.println("");
        rygar.interpret();
        Util.sleep(1000);
        System.out.println("");

        gandalf.interpret();

    }


}
