package nl.playdnd.arena;

import java.awt.*;
import javax.swing.*;

import static nl.playdnd.global.Settings.*;

public class ArenaGUI {
    private final JFrame window;
    private final Box scoreboard;
    // private final JPanel arena;
    private JLayeredPane arena;

    public JLayeredPane getArena() {
        return arena;
    }

    public void setArena(JLayeredPane arena) {
        this.arena = arena;
    }

    private final JTextArea turns;

    /*
     * DEFAULT_LAYER The standard layer, where most components go. This the
     * bottommost layer.
     * PALETTE_LAYER The palette layer sits over the default layer. Useful for
     * floating toolbars and palettes, so they can be positioned above other
     * components.
     * MODAL_LAYER The layer used for modal dialogs. They will appear on top of any
     * toolbars, palettes, or standard components in the container.
     * POPUP_LAYER The popup layer displays above dialogs. That way, the popup
     * windows associated with combo boxes, tooltips, and other help text will
     * appear above the component, palette, or dialog that generated them.
     * DRAG_LAYER When dragging a component, reassigning it to the drag layer
     * ensures that it is positioned over every other component in the container.
     * When finished dragging, it can be reassigned to its normal layer.
     */

    private static ArenaGUI arenaGUI;

    public static ArenaGUI getInstance() {
        if (arenaGUI == null)
            arenaGUI = new ArenaGUI();
        return arenaGUI;
    }

    public ArenaGUI() {
        window = new JFrame("Welkom");
//        window.setUndecorated(true);
//        window.setBackground(new Color(0,0,0,200));

        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
        
        window.setSize(1200, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //    window.setBackground(Color.white);
        arena = new JLayeredPane();
        arena.setBackground(new Color(0,0,0,0));

        arena.setLocation(new Point(0, 0));
        arena.setPreferredSize(new Dimension(800, 800));
        // arena.setBackground(Color.YELLOW);
        arena.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // arena.setLayout(null);

        window.add(arena, JLayeredPane.PALETTE_LAYER);

        // arena.setBackground(Color.BLACK);

        scoreboard = Box.createVerticalBox();
        scoreboard.setPreferredSize(new Dimension(250, 560));
        scoreboard.setBackground(Color.GRAY);
        
        JLabel textArea = new JLabel("Scoreboard");
        scoreboard.add(textArea);

        this.turns = new JTextArea("turn: 0");
        scoreboard.add(turns);
        window.add(scoreboard);

    //    window.setVisible(true);
    }

    public  void setVisible(boolean visible) {
        window.setVisible(visible);
    }


    public JPanel addElement(int x, int y, JPanel element) {

        arena.add(element, JLayeredPane.MODAL_LAYER);
        //window.add(element, JLayeredPane.DRAG_LAYER);
        // element.setSize(20, 20);
        element.setLocation(new Point(x * (TILESIZEX + TILESPACINGX), y * (TILESIZEY + TILESPACINGY)));
        element .repaint();
       //addFog(x, y);
        return element;
    }

    public JPanel addFog(int x, int y) {
        JPanel element = new JPanel();

        element.setSize(TILESIZEX, TILESIZEY);
        element.setBackground(new Color(0,0,0,15)); 
        //element.setOpaque(false);
        //element.setBackground(Color.CYAN);
        element.setLocation(new Point(x * (TILESIZEX + TILESPACINGX), y * (TILESIZEY + TILESPACINGY)));
        arena.add(element, JLayeredPane.DRAG_LAYER);
     
        return element;
    }



    public JPanel addElement(int x, int y, java.awt.Color color) {
        JPanel element = new JPanel();

        arena.add(element, JLayeredPane.MODAL_LAYER);
        element.setSize(TILESIZEX, TILESIZEY);
        element.setBackground(color);
        element.setLocation(new Point(x * (TILESIZEX + TILESPACINGY), y * (TILESIZEX + TILESPACINGY)));
        return element;
    }

    public JPanel addTile(int x, int y) {
        JPanel element = new JPanel();

        arena.add(element, JLayeredPane.DEFAULT_LAYER);
        element.setSize(TILESIZEX, TILESIZEY);
        //element.setBackground(EMPTYTILECOLOR);
        element.setBackground(new Color(30,30,30,150));

        element.setLocation(new Point(x * (TILESIZEX + TILESPACINGX), y * (TILESIZEY + TILESPACINGY)));

        return element;
    }

    public JTextArea addInfoToScoreboard(String title) {
        JPanel infoArea = new JPanel();
        infoArea.setPreferredSize(new Dimension(200, 100));
        infoArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JTextArea _title = new JTextArea(title);
        JTextArea info = new JTextArea();

        infoArea.add(_title);
        infoArea.add(info);
        scoreboard.add(infoArea);
        return info;
    }
    public void removeElement(JPanel element) {
        this.arena.remove(element);
    }
    public void removeElement(JTextArea info) {
        Container infoArea = info.getParent();
        infoArea.setVisible(false);
        this.arena.remove(infoArea);
    }
    public void updateTurns(int turn) {
        this.turns.setText(String.format("turn: %d / 5000", turn));
    }

    public void declareWinner(String nameOfTheWinner) {
        JTextArea winner = new JTextArea(String.format("WINNER: %s", nameOfTheWinner));
        winner.setPreferredSize(new Dimension(200, 100));
        winner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        winner.setBackground(Color.GREEN);

        JFrame winnerDialog = new JFrame();
        winnerDialog.add(winner);
        winnerDialog.setSize(200, 100);
        winnerDialog.setVisible(true);
    }

    /**
     * The GUI function that opens a popup window declaring that the battle ended
     * with a draw.
     */
    public void declareDraw() {
        JTextArea winner = new JTextArea("IT'S A DRAW");
        winner.setPreferredSize(new Dimension(200, 100));
        winner.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        winner.setBackground(Color.GRAY);

        JFrame winnerDialog = new JFrame();
        winnerDialog.add(winner);
        winnerDialog.setSize(200, 100);
        winnerDialog.setVisible(true);
    }

    
    // protected void drawImageTopLeft(Graphics2D g2d) {
    //     g2d = (Graphics2D) g2d.create();
    //     int x = 0;
    //     int y = 0;

    //     g2d.rotate(Math.toRadians(135), image.getWidth() / 2, image.getHeight() / 2);
    //     g2d.drawImage(image, x, y, this);
    //     g2d.dispose();
    // }

    // protected void drawImageBottomRight(Graphics2D g2d) {
    //     g2d = (Graphics2D) g2d.create();
    //     int x = (getWidth() - image.getWidth());
    //     int y = (getHeight() - image.getHeight());

    //     g2d.rotate(Math.toRadians(-45), getWidth() - (image.getWidth() / 2), getHeight() - (image.getHeight() / 2));
    //     g2d.drawImage(image, x, y, this);
    //     g2d.dispose();
    // }

    // protected void drawImageMiddle(Graphics2D g2d) {
    //     g2d = (Graphics2D) g2d.create();
    //     int x = (getWidth() - image.getWidth()) / 2;
    //     int y = (getHeight() - image.getHeight()) / 2;

    //     g2d.rotate(Math.toRadians(45), getWidth() / 2, getHeight() / 2);
    //     g2d.drawImage(image, x, y, this);
    //     g2d.dispose();
    // }
}