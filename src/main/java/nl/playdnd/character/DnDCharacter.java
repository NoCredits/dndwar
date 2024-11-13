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
import nl.playdnd.dasic.interpreter.SourceCode;
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
//public abstract class DnDCharacter extends InlineValues implements DnDEntityMoveable {
    public abstract class DnDCharacter implements DnDEntityMoveable {

    protected Armor myArmor;
    private int level;
    protected Weapon myWeapon;

    protected Point position; // Store the character's position on the map
    protected JPanel element;
    protected java.awt.Color color;
    protected DasicAI dasicAI;

    private SourceCode sourceCode;
    
    public InlineValues stats;

    protected String imageS, imageE, imageN, imageW;

    public static BattleMap battleMap;

    private final Scanner keyboardInput = new Scanner(System.in);

    public DnDCharacter() {
        sourceCode = new SourceCode(this);
        stats = sourceCode.stats;
        stats.setFaceTo(FaceTo.SOUTH);
        setDasicAI(initDasic());
        stats.setHealth((int) ((Math.random()) + 25));
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
             switch (stats.faceTo) {
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
                "\nHealth: " + stats.getHealth() +
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
        return stats.name;
    }

    public void setName(String name) {
        stats.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void attack(DnDEntityMoveable enemy) {
        System.out.println(stats.name + " attacks " + enemy.getName() + "!");
        int damage = stats.getStrength() / 2;
        enemy.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        stats.setHealth(stats.getHealth() - damage);
        if (stats.getHealth() <= 0) {
            System.out.println(stats.name + " has fallen!");
        } else {
            System.out.println(stats.name + " has " + stats.getHealth() + " hit points remaining.");
        }
    }

    @Override
    public void heal(int healAmount) {
        stats.setHealth(stats.getHealth() + healAmount);
        System.out.println(stats.name + " heals for " + healAmount + " hit points!");
    }

    @Override
    public void levelUp() {
        this.level++;
        stats.setHealth(stats.getHealth() + 10);
        System.out.println(stats.name + " has leveled up to level " + this.level + "!");
    }

    @Override
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public JPanel getElement() {
        return element;
    }

    public void setElement(JPanel element) {
        this.element = element;
    }


    @Override
    public void interpret() {
        dasicAI.interpret(this);
    }


    public void move (int distance) {
        
        System.out.println("facing "+stats.getFaceTo());

        for (int d = 0; d<distance; d++) {

            Point newPos = getPosition();
            if (stats.getFaceTo() == FaceTo.NORTH) { 
                newPos=new Point(getPosition().x,getPosition().y - 1);
            }
            if (stats.getFaceTo() == FaceTo.EAST) { 
                newPos=new Point(getPosition().x+1,getPosition().y );
             }
             if (stats.getFaceTo() == FaceTo.SOUTH) { 
                newPos=new Point(getPosition().x,getPosition().y + 1);
            }
            if (stats.getFaceTo() == FaceTo.WEST) { 
                newPos=new Point(getPosition().x - 1,getPosition().y );
             }
             if (battleMap.getCharacterAt(newPos.x, newPos.y) == null) { battleMap.moveCharacter(this,newPos);}
             Util.sleep(1500);
        }
     
    }

    @Override
    public DasicAI initDasic() {
       return new DasicAI(this);
    }

    
}