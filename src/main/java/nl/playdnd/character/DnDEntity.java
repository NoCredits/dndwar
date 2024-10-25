package nl.playdnd.character;

import java.awt.Point;

import javax.swing.JPanel;

public interface DnDEntity {

    String getName();

    Point getPosition(); // Return [x, y] coordinates

    void setPosition(Point pos);

    JPanel getElement();

    void setElement(JPanel element);

}
