package nl.playdnd.player;

import java.awt.Color;
import java.util.*;

import javax.swing.JPanel;

import nl.playdnd.dasic.Dasic;

public class Warrior extends PlayerCharacter {

    public Warrior(String name, int health, int strength) {
        setName(name);
        setHealth(health);
        setStrength(strength);

        JPanel element = new JPanel();
        element.setSize(20, 20);
        element.setBackground(Color.red);
        setElement(element);

        dasic = new Dasic("src/main/resources/das/hellos.das");
        dasic.interpret();

        // element = Dasic.getArena().addElement(5, 5);
        // element.setBackground(Color.red);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // element.move(10, 10);
    }

}
