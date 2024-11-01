package nl.playdnd.character;

import nl.playdnd.dasic.DasicAI;

public interface DnDEntityMoveable extends DnDEntity {

    int attack();

    int defend();

    void printStats();

    void attack(DnDEntityMoveable enemy);

    void takeDamage(int damage);

    void heal(int healAmount);

    void levelUp();

    //DasicAI getDasic();

    DasicAI initDasic();

    void interpret();

    String getNorthImagePath();

    String getEastImagePath();
    
    String getSouthImagePath();

    String getWestImagePath();
}
