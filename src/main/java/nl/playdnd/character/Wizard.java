package nl.playdnd.character;

import java.awt.Color;
import static nl.playdnd.global.Settings.*;

import javax.swing.JPanel;

import nl.playdnd.dasic.DasicAI;

public class Wizard extends PlayerCharacter {

    public Wizard(String name, int health, int strength) {
        setName(name);
        setHealth(health);
        setStrength(strength);

        JPanel element = new JPanel();
        element.setSize(TILESIZEX, TILESIZEY);
        element.setBackground(Color.blue);
        setElement(element);

        System.out.println("Wizard");
        interpret();

        try {
            Thread.sleep(100);
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
