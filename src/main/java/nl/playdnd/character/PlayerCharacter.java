package nl.playdnd.character;

import java.awt.Color;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.dasic.DasicAI;

@Setter
@Getter
public abstract class PlayerCharacter extends InlineValues implements DnDCharacter {

    private Armor myArmor;
    private int level;
    private Weapon myWeapon;

    protected Point position; // Store the character's position on the map
    protected JPanel element;
    protected java.awt.Color color;
    protected DasicAI dasicAI;

    private final Scanner keyboardInput = new Scanner(System.in);

    public PlayerCharacter() {
        setDasicAI(initDasic());
        setHealth((int) ((Math.random()) + 25));
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
    public void attack(DnDCharacter enemy) {
        System.out.println(this.name + " attacks " + enemy.getName() + "!");
        int damage = getStrength() / 2;
        enemy.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        setHealth(getHealth()- damage);
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