package nl.playdnd.character;

import nl.playdnd.dasic.DasicAI;
import nl.playdnd.global.FaceTo;

/**
 * Hobgoblin.java
 *
 * A hobgoblin is intended to be a weak monster. It is also the Foolish
 * Consistency of Little Minds.
 */
public class Goblin extends DnDCharacter {

  
    private final int HEALTH_MULTIPLIER = 10;

    public Goblin() {
        stats.name = "Goblin";
        this.myWeapon = new Weapon("Cast Iron Skillet", 6);
        this.myArmor = new Armor("Trash Can Lid for a Breastplate, Suspended with Bungee Cords", 6);
        stats.setHealth((int) ((Math.random() * HEALTH_MULTIPLIER) + 5));
        
        stats.faceTo(FaceTo.SOUTH);

        createElement();

    }


    @Override
    public void interpret() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interpret'");
    }

    @Override
    public String getNorthImagePath() {
        return "src/main/resources/eye.png";  
    }

    @Override
    public String getEastImagePath() {
        return "src/main/resources/eye.png";      }

    @Override
    public String getSouthImagePath() {
        return "src/main/resources/eye.png";      }

    @Override
    public String getWestImagePath() {
        return "src/main/resources/eye.png";      }
}