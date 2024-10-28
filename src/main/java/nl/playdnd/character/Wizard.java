package nl.playdnd.character;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static nl.playdnd.global.Settings.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import nl.playdnd.dasic.DasicAI;

public class Wizard extends DnDCharacter {

    public Wizard(String name, int health, int strength) {
        setName(name);
        setHealth(health);
        setStrength(strength);
        
        System.out.println("Wizard");
        setFace(FaceTo.NORTH);

        createElement();
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
