package nl.playdnd.arena;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import nl.playdnd.character.DnDCharacter;
import nl.playdnd.character.DnDEntity;

public class BattleMapScanner {
    private static final int VISION_RADIUS = 5; // Max vision range
    private static final int FOV_ARC = 90;      // Field of view (in degrees)

    public BattleMapScanner (BattleMap map, DnDCharacter player) {
        // Example player positions and facing angles (in degrees)

        // Example enemy positions
        List<DnDCharacter> enemies = map.getChars();

        // Example map grid (0 = empty, 1 = obstacle)
        DnDEntity[][]  mapGrid = map.getMap();

        // Perform scan for each player
        
            List<Point> detectedEnemies = scanForEnemies(player, enemies, VISION_RADIUS, mapGrid);
            System.out.println("Player at " + player.getPosition() + " facing " + player.getFaceTo() + "° detects enemies at: " + detectedEnemies);
        
    }

    private static List<Point> scanForEnemies(DnDCharacter player, List<DnDCharacter> enemies, int visionRadius, DnDEntity[][] mapGrid) {
                    List<Point> detectedEnemies = new ArrayList<>();
            
                    for (DnDEntity enemy : enemies) {
                // Calculate distance between player and enemy
                System.out.println(enemy.getPosition() + enemy.getName()+" "+player.getFaceArc() + " "+player.getFaceTo());
                double distance = player.getPosition().distance(enemy.getPosition());
    
                // Check if enemy is within the vision radius
                if (distance <= visionRadius) {
                    // Calculate the angle to the enemy and check if it's within the field of view (FOV)
                    double angleToEnemy = calculateAngle(player.getPosition(), enemy.getPosition());
                    
                    if (isWithinArc(player.getFaceArc(), angleToEnemy, FOV_ARC)) {
                        // Check line of sight (LOS)
                        if (lineOfSight(player.getPosition(), enemy.getPosition(), mapGrid)) {
                        detectedEnemies.add(enemy.getPosition());
                    }
                }
            }
        }
        return detectedEnemies;
    }

    private static boolean lineOfSight(Point start, Point end, DnDEntity[][] mapGrid) {
            // Bresenham's Line Algorithm to trace a line from start to end
            int x1 = start.x, y1 = start.y;
            int x2 = end.x, y2 = end.y;
            int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
            int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1;
            int err = dx - dy;
    
         while (true) {
            //     // Check if we hit an obstacle
            //     if (mapGrid[y1][x1] == 0) {
            //     return false; // Line of sight is blocked
            // }

            // Reached the endpoint
            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
        return true; // Line of sight is clear
    }

    private static double calculateAngle(Point from, Point to) {
        // Calculate angle from one point to another
        double deltaX = to.x - from.x;
        double deltaY = to.y - from.y;
        return Math.toDegrees(Math.atan2(deltaY, deltaX));
    }

    private static boolean isWithinArc(int facingAngle, double angleToEnemy, int fovArc) {
        // Normalize angles to [0, 360)
        facingAngle = (facingAngle + 360) % 360;
        angleToEnemy = (angleToEnemy + 360) % 360;

        // Calculate half of the field of view (FOV) arc
        int halfArc = fovArc / 2;

        // Check if the angle to the enemy is within the facing field of view
        return Math.abs(facingAngle - angleToEnemy) <= halfArc ||
               Math.abs(facingAngle - angleToEnemy) >= 360 - halfArc;
    }
}

// Player class to store position and facing angle
class Player {
    Point position;
    int facingAngle;

    public Player(Point position, int facingAngle) {
        this.position = position;
        this.facingAngle = facingAngle;
    }
}
