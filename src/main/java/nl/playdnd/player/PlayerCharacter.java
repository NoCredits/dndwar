package nl.playdnd.player;

import java.awt.Color;
import java.awt.Point;
import java.util.Scanner;

import javax.swing.JPanel;

import nl.playdnd.dasic.Dasic;

public abstract class PlayerCharacter implements DnDCharacter {

    private String name;
    private Weapon myWeapon;
    private Armor myArmor;
    private int health;

    private int strength;
    private int level;

    protected Point position; // Store the character's position on the map
    protected JPanel element;
    protected java.awt.Color color;
    protected Dasic dasic;

    private final Scanner keyboardInput = new Scanner(System.in);

    public PlayerCharacter() {
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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
        int damage = this.strength / 2;
        enemy.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            System.out.println(this.name + " has fallen!");
        } else {
            System.out.println(this.name + " has " + this.health + " hit points remaining.");
        }
    }

    @Override
    public void heal(int healAmount) {
        this.health += healAmount;
        System.out.println(this.name + " heals for " + healAmount + " hit points!");
    }

    @Override
    public void levelUp() {
        this.level++;
        this.health += 10; // Example: Fighters get more HP per level
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
    public Dasic getDasic() {
        return dasic;
    }

}