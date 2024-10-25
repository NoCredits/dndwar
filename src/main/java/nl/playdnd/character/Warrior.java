package nl.playdnd.character;

import java.awt.Color;
import java.util.*;
import static nl.playdnd.global.Settings.*;

import javax.swing.JPanel;

import nl.playdnd.dasic.DasicAI;

public class Warrior extends PlayerCharacter {

    public Warrior(String name, int health, int strength) {
        setName(name);
        setHealth(health);
        setStrength(strength);

        JPanel element = new JPanel();
        element.setSize(TILESIZEX, TILESIZEY);
        element.setBackground(Color.red);
        setElement(element);

        System.out.println("Warrior");
        interpret();

        // element = Dasic.getArena().addElement(5, 5);
        // element.setBackground(Color.red);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // element.move(10, 10);
    }

    @Override
    public DasicAI getDasic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDasic'");
    }

    @Override
    public DasicAI initDasic() {
        return new DasicAI("src/main/resources/das/warrior.das");

    }

}
