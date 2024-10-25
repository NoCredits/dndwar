package nl.playdnd;

import nl.playdnd.arena.ArenaGUI;
import nl.playdnd.arena.BattleMap;

import nl.playdnd.dasic.Dasic;
import nl.playdnd.player.Warrior;
import nl.playdnd.player.Wizard;
import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void amain(String[] args) {
        // Create a JFrame (the main window for the application).
        JFrame frame = new JFrame("JLayeredPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create a JLayeredPane to manage the layering of components.
        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane); // Add the JLayeredPane to the JFrame.

        // Create three colored panels to add to the layered pane.
        JPanel panel1 = createColoredPanel(Color.RED, 100, 100, 200, 200);
        JPanel panel2 = createColoredPanel(Color.GREEN, 150, 150, 200, 200);
        JPanel panel3 = createColoredPanel(Color.BLUE, 200, 200, 200, 200);

        // Add the panels to the layered pane with different layer values.
        // The layers determine the stacking order of the panels.
        layeredPane.add(panel1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel2, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(panel3, JLayeredPane.MODAL_LAYER);
        layeredPane.add(panel3, JLayeredPane.POPUP_LAYER);
        layeredPane.add(panel3, JLayeredPane.DRAG_LAYER);

        frame.setVisible(true); // Make the JFrame visible.

        JPanel arena = new JPanel();
        arena.setBackground(Color.BLACK);
        arena.setBounds(150, 150, 30, 30);
        // arena.setLayout(null);
        // arena.setPreferredSize(new Dimension(530, 560));

        // arena.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        layeredPane.add(arena, Integer.valueOf(500));
        System.out.println(JLayeredPane.MODAL_LAYER);

        // JLayeredPane layer = new JLayeredPane();
        // for (int i = 0; i < 7; i++) {
        // JPanel p = new JPanel();
        // p.setBackground(Color.black);
        // p.setBounds(i * 30 + 10, i * 30 + 5, 20, 20);
        // layer.add(p, JLayeredPane.MODAL_LAYER);

        // }
        // arena.add(layer);

    }

    private static JPanel createColoredPanel(Color color, int x, int y, int width, int height) {
        // Create a colored JPanel with specified color and position.
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.setBounds(x, y, width, height);
        return panel;
    }

    /**
     * Runs the interpreter as a command-line app. Takes one argument: a path
     * to a script file to load and run. The script should contain one
     * statement per line.
     * 
     * @param args Command-line arguments.
     */
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
