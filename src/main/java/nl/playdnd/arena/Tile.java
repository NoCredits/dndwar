package nl.playdnd.arena;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.character.DnDEntity;

import java.awt.Point;

@Setter
@Getter
public class Tile {


    Point position;
    
    JPanel element;

    DnDEntity entity;

    public Tile(Point position, JPanel element, DnDEntity entity) {
        this.position = position;
        this.element = element;
        this.element.setLocation(position.x, position.y);
        this.entity = entity;
        entity.setPosition(new Point(position.x, position.y));
    }
    

    

}
