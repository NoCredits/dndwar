package nl.playdnd.arena;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.character.DnDCharacter;
import nl.playdnd.character.DnDEntity;

import static nl.playdnd.global.Settings.*;

//holds all entities en players
@Getter
@Setter
public class BattleMap {
    private final int width;
    private final int height;
    private DnDEntity[][] map; // A grid to hold characters' MAP positions
    private JPanel[][] elements; // A grid to hold characters' SCREEN positions
    private List<DnDCharacter> chars;
    
    // Constructor to initialize the map with a given width and height
    public BattleMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new DnDEntity[width][height]; // Create an empty grid
        this.elements = new JPanel[width][height];
        this.chars = new ArrayList<DnDCharacter>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ArenaGUI.getInstance().addTile(i, j);

            }
        }
        
    }

    // Method to place a character at a given (x, y) position
    public void placeCharacter(DnDCharacter character, int x, int y) {
        if (isValidPosition(x, y)) {
            map[x][y] = character;
            System.out.println(character.getName() + " is placed at (" + x + ", " + y + ").");
            elements[x][y] = ArenaGUI.getInstance().addElement(x, y, character.getElement());
            character.setElement(elements[x][y]);
            character.setPosition(new Point(x,y));
            chars.add(character);

        } else {
            System.out.println("Invalid position (" + x + ", " + y + ") for " + character.getName());
        }
        
    }

    // Method to move a character to a new position
    public void moveCharacter(DnDEntity character, int oldX, int oldY, int newX, int newY) {
        if (isValidPosition(newX, newY) && map[oldX][oldY] == character) {
            map[oldX][oldY] = null; // Remove character from the old position
            character.getElement().setLocation(new Point(newX * (TILESIZEX + TILESPACINGX), newY * (TILESIZEY + TILESPACINGY)));
            map[newX][newY] = character; // Place character in the new position
           
            System.out.println(character.getName() + " moves from (" + oldX + ", " + oldY + ") to (" + newX + ", " + newY + ").");
        } else {
            System.out.println("Invalid move for " + character.getName());
        }
    }

    // Method to move a character to a new position
    public void moveCharacter(DnDEntity character, int newX, int newY) {
        Point oldPos=new Point(character.getPosition().x,character.getPosition().y);
        if (isValidPosition(newX, newY) && map[oldPos.x][oldPos.y] == character) {
            map[oldPos.x][oldPos.y] = null; // Remove character from the old
                                                                              // position
            character.getElement()
                    .setLocation(new Point(newX * (TILESIZEX + TILESPACINGX), newY * (TILESIZEY + TILESPACINGY)));
            // allElements[oldX][oldY] = Dasic.getArena().addElement(x, y);
            map[newX][newY] = character; // Place character in the new position
            character.setPosition(new Point(newX, newY));
            System.out.println(
                    character.getName() + " moves from ("+oldPos.x+", "+oldPos.y+") to (" + newX + ", " + newY + ").");
        } else {
            System.out.println("Invalid move for " + character.getName());
        }
    }
    public void moveCharacter(DnDEntity character, Point pos) {
        moveCharacter(character, pos.x, pos.y);
    }

    // Method to check if a position is within the map bounds
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    // Print the map with character positions
    public void displayMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[j][i] != null) {
                    System.out.print("[" + map[j][i].getName().charAt(0) + "] "); // Display first letter of
                                                                                  // character's name
                } else {
                    System.out.print("[ ] "); // Empty space
                }
            }
            System.out.println();
        }
    }

    // Get the character at a given position
    public DnDEntity getCharacterAt(int x, int y) {
        if (isValidPosition(x, y)) {
            return map[x][y];
        }
        return null;
    }
}