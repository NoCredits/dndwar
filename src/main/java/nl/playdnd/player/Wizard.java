package nl.playdnd.player;

import java.awt.Color;
import java.util.*;
import static nl.playdnd.global.Settings.*;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import nl.playdnd.dasic.DasicAI;
import nl.playdnd.dasic.interpreter.Variables;

public class Wizard extends PlayerCharacter {

    public Wizard(String name, int health, int strength) {
        setName(name);
        setHealth(health);
        setStrength(strength);

        JPanel element = new JPanel();
        element.setSize(TILESIZEX, TILESIZEY);
        element.setBackground(Color.blue);
        setElement(element);

        dasicAI.interpret();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // element.move(30, 30);
    }

    @Override
    public DasicAI initDasic() {
        dasicAI = new DasicAI("src/main/resources/das/wizard.das");
        return dasicAI;
    }

}
