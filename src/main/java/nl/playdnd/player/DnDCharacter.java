package nl.playdnd.player;

import java.awt.Point;

import javax.swing.JPanel;

import nl.playdnd.dasic.Dasic;

public interface DnDCharacter extends DnDEntity {

    /**
     * All characters have the ability to attack and cause damage to another
     * character. The amount of damage is
     * determined by a formula that factors the attackPower of the weapon used.
     *
     * @return The amount of damage caused during the attack
     */
    int attack();

    /**
     * All characters can defend against attacks from other characters. Depending on
     * the defenseLevel of their
     * armor, they can block a portion of the attack or cause the attacker to miss
     * entirely.
     *
     * @return The amount to reduce the damage caused by an attacker. If the damage
     *         is reduced to zero, the attack
     *         is a "miss."
     */
    int defend();

    /**
     * Print a list of statistics for the character. This is similar to "toString()"
     * except only select fields
     * are returned.
     */
    void printStats();

    void attack(DnDCharacter enemy);

    void takeDamage(int damage);

    void heal(int healAmount);

    void levelUp();

    Dasic getDasic();
    // java.awt.Color getColor();

}
