package nl.playdnd.player;

import nl.playdnd.dasic.DasicAI;

public interface DnDCharacter extends DnDEntity {

    int attack();

    int defend();

    void printStats();

    void attack(DnDCharacter enemy);

    void takeDamage(int damage);

    void heal(int healAmount);

    void levelUp();

    DasicAI getDasic();

    DasicAI initDasic();

    void interpret();

}
