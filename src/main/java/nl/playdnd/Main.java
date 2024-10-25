package nl.playdnd;

import nl.playdnd.arena.BattleMap;
import nl.playdnd.arena.ArenaGUI;
import nl.playdnd.dasic.DasicAI;
import nl.playdnd.player.Warrior;
import nl.playdnd.player.Wizard;
import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // Create a battle map
        BattleMap battleMap = new BattleMap(15, 15); // 5x5 grid

        // Dasic.getArena().addElement(3, 3, java.awt.Color);
        Warrior rygar = new Warrior("Rygar", 10, 4);
        Wizard gandalf = new Wizard("Gandalf", 8, 2);

        // Place characters on the map
        battleMap.placeCharacter(rygar, 0, 0); // Rygar starts at (0, 0)
        battleMap.placeCharacter(gandalf, 4, 4); // Gandalf starts at (4, 4)

        // Set their positions
        rygar.setPosition(new Point(0, 0));
        gandalf.setPosition(new Point(4, 4));

        System.out.println("Initial Map:");
        battleMap.displayMap();

        int sx = 2;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // Move Aragorn closer to Gandalf
            battleMap.moveCharacter(rygar, 2 + sx, 2);
            // rygar.setPosition(new Point(2, 2));

            // Move Gandalf closer to Aragorn
            battleMap.moveCharacter(gandalf, 3 + sx, 3);
            // gandalf.setPosition(new Point(3, 3));

            // Display the updated map
            System.out.println("\nAfter Moving:");
            battleMap.displayMap();
            // Wizard casts Fireball on Fighter
            gandalf.attack(rygar);

            // Fighter attacks Wizard
            rygar.attack(gandalf);

            // Wizard casts Lightning Bolt on Fi

        } while (sx++ < 10);

    }

}
