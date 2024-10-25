package nl.playdnd.character;

import java.awt.Point;

import javax.swing.JPanel;

import nl.playdnd.dasic.DasicAI;

public abstract class NonPlayerCharacter  extends InlineValues implements DnDCharacter {

    protected String name;
    protected Weapon myWeapon;
    protected Armor myArmor;
    
    protected int gold;

    
    private int level;
    private Point position; // Store the character's position on the map

    private JPanel element;
    protected java.awt.Color color;
    protected DasicAI dasicAI;
    // These are used in the calculations to autogenerate the values for their
    // respective fields
    private final int ATTACK_MODIFIER = 4;
    private final int DEFENSE_MODIFIER = 1;

    NonPlayerCharacter() {
        
    }

    @Override
    public int attack() {
        int damage = (int) ((Math.random() * myWeapon.getAttackPower()) + ATTACK_MODIFIER);
        return damage;
    }

    @Override
    public int defend() {
        int defense = (int) ((Math.random() * myArmor.getDefenseLevel()) + DEFENSE_MODIFIER);
        return defense;
    }

    @Override
    public void printStats() {
        System.out.println("Name: " + getName() +
                "\nHealth: " + getHealth() +
                "\nGold: $" + getGold() +
                "\nWeapon: " + myWeapon.getName() +
                "\nArmor: " + myArmor.getName());
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeaponName() {
        return myWeapon.getName();
    }

    public String getArmorName() {
        return myArmor.getName();
    }


    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

}