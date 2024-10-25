package nl.playdnd.arena;

import java.awt.Point;

import javax.swing.JPanel;

import nl.playdnd.dasic.Dasic;
import nl.playdnd.player.DnDEntity;

public class BattleMap {
    private final int width;
    private final int height;
    private DnDEntity[][] mapGrid; // A grid to hold characters' positions
    private JPanel[][] allElements; // A grid to hold characters' positions

    // Constructor to initialize the map with a given width and height
    public BattleMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.mapGrid = new DnDEntity[width][height]; // Create an empty grid
        this.allElements = new JPanel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Dasic.getArena().addTileElement(i, j);

            }
        }
    }

    // Method to place a character at a given (x, y) position
    public void placeCharacter(DnDEntity character, int x, int y) {
        if (isValidPosition(x, y)) {
            mapGrid[x][y] = character;
            System.out.println(character.getName() + " is placed at (" + x + ", " + y + ").");
            // allElements[x][y] = Dasic.getArena().addElement(x, y, Color.blue);
            allElements[x][y] = Dasic.getArena().addElement(x, y, character.getElement());
            character.setElement(allElements[x][y]);
        } else {
            System.out.println("Invalid position (" + x + ", " + y + ") for " + character.getName());
        }
    }

    // Method to move a character to a new position
    public void moveCharacter(DnDEntity character, int oldX, int oldY, int newX, int newY) {
        if (isValidPosition(newX, newY) && mapGrid[oldX][oldY] == character) {
            mapGrid[oldX][oldY] = null; // Remove character from the old position
            character.getElement().setLocation(new Point(newX * 21, newY * 21));
            // allElements[oldX][oldY] = Dasic.getArena().addElement(x, y);
            mapGrid[newX][newY] = character; // Place character in the new position
            System.out.println(
                    character.getName() + " moves from (" + oldX + ", " + oldY + ") to (" + newX + ", " + newY + ").");
        } else {
            System.out.println("Invalid move for " + character.getName());
        }
    }

    // Method to move a character to a new position
    public void moveCharacter(DnDEntity character, int newX, int newY) {
        if (isValidPosition(newX, newY) && mapGrid[character.getPosition().x][character.getPosition().y] == character) {
            mapGrid[character.getPosition().x][character.getPosition().y] = null; // Remove character from the old
                                                                                  // position
            character.getElement().setLocation(new Point(newX * 21, newY * 21));
            // allElements[oldX][oldY] = Dasic.getArena().addElement(x, y);
            mapGrid[newX][newY] = character; // Place character in the new position
            character.setPosition(new Point(newX, newY));
            System.out.println(
                    character.getName() + " moves from () to (" + newX + ", " + newY + ").");
        } else {
            System.out.println("Invalid move for " + character.getName());
        }
    }

    // Method to check if a position is within the map bounds
    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    // Print the map with character positions
    public void displayMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mapGrid[j][i] != null) {
                    System.out.print("[" + mapGrid[j][i].getName().charAt(0) + "] "); // Display first letter of
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
            return mapGrid[x][y];
        }
        return null;
    }
}