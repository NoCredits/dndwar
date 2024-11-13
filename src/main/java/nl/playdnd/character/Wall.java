package nl.playdnd.character;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;
import static nl.playdnd.global.Settings.TILESIZEX;
import static nl.playdnd.global.Settings.TILESIZEY;

import nl.playdnd.character.DnDCharacter.JPanel1;

public class Wall implements DnDEntity {

    Point position;
    JPanel element;
    public Wall(Point pos) {
        position = pos;
        JPanel element = null;
        element = new JPanel();
        element.setSize(TILESIZEX, TILESIZEY);
        element.setBackground(new Color(0,0,0,1));

    }

    @Override
    public String getName() {
        return "Wall" ;
    }

    @Override
    public Point getPosition() {
        return position;
    
    }

    @Override
    public JPanel getElement() {
        return element;
    }
    

}
