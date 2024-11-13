package nl.playdnd.character;

import static nl.playdnd.global.Settings.TILESIZEX;
import static nl.playdnd.global.Settings.TILESIZEY;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.AlphaComposite;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.arena.BattleMap;
import nl.playdnd.dasic.DasicAI;
import nl.playdnd.global.FaceTo;
import nl.playdnd.global.Util;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Setter
@Getter
public abstract class DnDCharacter extends InlineValues implements DnDEntityMoveable {

    protected Armor myArmor;
    private int level;
    protected Weapon myWeapon;

    protected Point position; // Store the character's position on the map
    protected JPanel element;
    protected java.awt.Color color;
    protected DasicAI dasicAI;

    protected String imageS, imageE, imageN, imageW;

    public static BattleMap battleMap;

    private final Scanner keyboardInput = new Scanner(System.in);

    public DnDCharacter() {
        setFaceTo(FaceTo.SOUTH);
        setDasicAI(initDasic());
        setHealth((int) ((Math.random()) + 25));
    }


    public class JPanel1 extends JPanel {        
    
        public JPanel1() {
            setBackground(new Color(100,100,100,50));
        }
        
        public void paintComponent(Graphics g) {
             // The following statement ensures that
             // the background color will be applied
             // as well as other preparations for 
             // doing graphics.
     
             super.paintComponent(g);
             // If you have other graphics
             // code, add it here.

             String file = "";
             switch (faceTo) {
                 case EAST : file = getEastImagePath();
                 break;
                 case SOUTH : file = getSouthImagePath();
                 break;
                 case WEST : file = getWestImagePath();
                 break;
                 default : file = getNorthImagePath();
             }
            // BufferedImage image = null;
             BufferedImage image = new BufferedImage(TILESIZEX, TILESIZEY,BufferedImage.TYPE_INT_ARGB);
             try {
                 image = ImageIO.read(new File(file));
             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
   
             Graphics2D g2 = (Graphics2D) g.create();
             //g2.setBackground(new Color(0,0,0,0));
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);


//              AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.5F);
             //g2.setComposite(ac);
            // g2.rotate(Math.toRadians(0), TILESIZEX / 2, TILESIZEX / 2);
             g2.drawImage(image, 0, 0, TILESIZEX, TILESIZEY, null);
          
             g2.dispose();

       }
    
    }

    public void createElement() {

        JPanel1 element = null;
        element = new JPanel1();
        element.setSize(TILESIZEX, TILESIZEY);
        //element.setBackground(new Color(0,0,0,1));
        setElement(element);

    }
    public void createElementOLD() {

        JPanel element = null;
        element = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0,0,0,0));
                super.paintComponent(g);
                String file = "";
                switch (faceTo) {
                    case EAST : file = getEastImagePath();
                    break;
                    case SOUTH : file = getSouthImagePath();
                    break;
                    case WEST : file = getWestImagePath();
                    break;
                    default : file = getNorthImagePath();
                }
               // BufferedImage image = null;
                BufferedImage image = new BufferedImage(TILESIZEX, TILESIZEY,BufferedImage.TYPE_INT_ARGB);
                try {
                    image = ImageIO.read(new File(file));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
      
                Graphics2D g2 = (Graphics2D) g.create();
                //g2.setBackground(new Color(0,0,0,0));
//                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);


  //              AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.5F);
                //g2.setComposite(ac);
               // g2.rotate(Math.toRadians(0), TILESIZEX / 2, TILESIZEX / 2);
                g2.drawImage(image, 0, 0, TILESIZEX, TILESIZEY, null);
             
                g2.dispose();
            }
        };

        element.setSize(TILESIZEX, TILESIZEY);
        //element.setBackground(new Color(0,0,0,1));
        setElement(element);
    }



    @Override
    public int attack() {
        int damage = (int) ((Math.random() * myWeapon.getAttackPower()));
        return damage;
    }

    @Override
    public int defend() {
        int defense = (int) ((Math.random() * myArmor.getDefenseLevel()));
        return defense;
    }

    @Override
    public void printStats() {
        System.out.println("Name: " + getName() +
                "\nHealth: " + getHealth() +
                "\nWeapon: " + myWeapon.getName() +
                "\nArmor: " + myArmor.getName());
    }

    public String getWeaponName() {
        return myWeapon.getName();
    }

    public String getArmorName() {
        return myArmor.getName();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void attack(DnDEntityMoveable enemy) {
        System.out.println(this.name + " attacks " + enemy.getName() + "!");
        int damage = getStrength() / 2;
        enemy.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        if (getHealth() <= 0) {
            System.out.println(this.name + " has fallen!");
        } else {
            System.out.println(this.name + " has " + getHealth() + " hit points remaining.");
        }
    }

    @Override
    public void heal(int healAmount) {
        setHealth(getHealth() + healAmount);
        System.out.println(this.name + " heals for " + healAmount + " hit points!");
    }

    @Override
    public void levelUp() {
        this.level++;
        setHealth(getHealth() + 10);
        System.out.println(this.name + " has leveled up to level " + this.level + "!");
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public JPanel getElement() {
        return element;
    }

    @Override
    public void setElement(JPanel element) {
        this.element = element;
    }


    @Override
    public void interpret() {
        dasicAI.interpret(this);
    }

    @Override
    public void move (int distance) {
        
        System.out.println("facing "+getFaceTo());

        for (int d = 0; d<distance; d++) {

            Point newPos = getPosition();
            if (getFaceTo() == FaceTo.NORTH) { 
                newPos=new Point(getPosition().x,getPosition().y - 1);
            }
            if (getFaceTo() == FaceTo.EAST) { 
                newPos=new Point(getPosition().x+1,getPosition().y );
             }
             if (getFaceTo() == FaceTo.SOUTH) { 
                newPos=new Point(getPosition().x,getPosition().y + 1);
            }
            if (getFaceTo() == FaceTo.WEST) { 
                newPos=new Point(getPosition().x - 1,getPosition().y );
             }
             if (battleMap.getCharacterAt(newPos.x, newPos.y) == null) { battleMap.moveCharacter(this,newPos);}
             Util.sleep(500);
        }
     
    }

    @Override
    public void faceTo (FaceTo faceTo) {
        System.out.println("SET "+faceTo);
        setFaceTo(faceTo) ;
    }
}