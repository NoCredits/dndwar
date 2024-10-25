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
        window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));

        window.setSize(780, 560);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setBackground(Color.white);
        arena = new JLayeredPane();
        arena.setLocation(new Point(0, 0));
        arena.setPreferredSize(new Dimension(530, 560));
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

        window.setVisible(true);
    }

    public JPanel addElement(int x, int y, JPanel element) {

        arena.add(element, JLayeredPane.MODAL_LAYER);
        // element.setSize(20, 20);
        element.setLocation(new Point(x * (TILESIZEX + TILESPACINGX), y * (TILESIZEY + TILESPACINGY)));
        return element;
    }

    /**
     * Adds an element to the GUI that represents a robot.
     * 
     * @return The component that will represent a robot.
     */
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
        element.setBackground(EMPTYTILECOLOR);
        element.setLocation(new Point(x * (TILESIZEX + TILESPACINGX), y * (TILESIZEY + TILESPACINGY)));

        return element;
    }

    /**
     * Adds an info box to the GUI that will be used to display the information of a
     * robot.
     * 
     * @param robotName Name of the robot.
     * @return The JTextArea which contains the dynamic information that can be
     *         changed.
     */
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

    /**
     * Removes the given robot or rocket from the GUI.
     * 
     * @param element The reference JPanel object.
     */
    public void removeElement(JPanel element) {
        this.arena.remove(element);
    }

    /**
     * Removes the given info box of the robot from the GUI.
     * 
     * @param info the reference JTextArea object.
     */
    public void removeElement(JTextArea info) {
        Container infoArea = info.getParent();
        infoArea.setVisible(false);
        this.arena.remove(infoArea);
    }

    /**
     * Set the turn number in the scoreboard.
     * 
     * @param turn
     */
    public void updateTurns(int turn) {
        this.turns.setText(String.format("turn: %d / 5000", turn));
    }

    /**
     * The GUI function that opens a popup window declaring the winner robot of the
     * battle.
     */
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
}