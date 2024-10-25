package nl.playdnd.player;

import java.awt.Color;
import java.util.*;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import nl.playdnd.dasic.Dasic;

public class Wizard extends PlayerCharacter {

    public Wizard(String name, int health, int strength) {
        setName(name);
        setHealth(health);
        setStrength(strength);

        JPanel element = new JPanel();
        element.setSize(20, 20);
        element.setBackground(Color.blue);
        setElement(element);

        dasic = new Dasic("src/main/resources/das/wizard.das");
        dasic.interpret();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // element.move(30, 30);
    }

}
