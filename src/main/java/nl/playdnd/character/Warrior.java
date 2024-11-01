package nl.playdnd.character;

import java.awt.Color;
import java.util.*;
import static nl.playdnd.global.Settings.*;

import javax.swing.JPanel;

import nl.playdnd.dasic.DasicAI;
import nl.playdnd.global.FaceTo;

public class Warrior extends DnDCharacter {

    public Warrior(String name, int health, int strength) {
        setName(name);
        setHealth(health);
        setStrength(strength);

        createElement();
        System.out.println("Warrior");
        setFaceTo(FaceTo.SOUTH);

    }


    @Override
    public DasicAI initDasic() {
        return new DasicAI("src/main/resources/das/warrior.das",this);

    }


    @Override
    public String getNorthImagePath() {
        return "src/main/resources/wizardnorth.png";    
    }    



    @Override
    public String getEastImagePath() {
        return "src/main/resources/wizardeast.png";    
    }


    @Override
    public String getSouthImagePath() {
        return "src/main/resources/wizardsouth.png";
    }


    @Override
    public String getWestImagePath() {
        return "src/main/resources/wizardwest.png";    
    }
    

}
