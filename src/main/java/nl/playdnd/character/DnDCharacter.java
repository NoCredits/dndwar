package nl.playdnd.character;

import static nl.playdnd.global.Settings.TILESIZEX;
import static nl.playdnd.global.Settings.TILESIZEY;

import java.awt.Color;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.dasic.DasicAI;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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

    private final Scanner keyboardInput = new Scanner(System.in);

    public DnDCharacter() {
        setFace(FaceTo.SOUTH);
        setDasicAI(initDasic());
        setHealth((int) ((Math.random()) + 25));
    }


    public void createElement() {

        JPanel element = null;
        element = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String file = "";
                switch (face) {
                    case EAST : file = getEastImagePath();
                    break;
                    case SOUTH : file = getSouthImagePath();
                    break;
                    case WEST : file = getWestImagePath();
                    break;
                    default : file = getNorthImagePath();
                }
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(file));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
      
                Graphics2D g2 = (Graphics2D) g;
                Rectangle square = new Rectangle(0, 0, TILESIZEX, TILESIZEY);

                g2.draw(square);
                g2.rotate(Math.toRadians(0), TILESIZEX / 2, TILESIZEX / 2);
                // Composite originalComposite = g2.getComposite();
                // AlphaComposite alphaComposite =  AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
                // g2.setComposite(alphaComposite);
                g2.drawImage(image, 0, 0, TILESIZEX, TILESIZEY, this);
                g2.dispose();
                // g2.drawImage(image, -0, -0,TILESIZEX,TILESIZEY,null);
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
    public DasicAI getDasic() {
        return dasicAI;
    }

    @Override
    public void interpret() {
        dasicAI.interpret(this);
    }
}