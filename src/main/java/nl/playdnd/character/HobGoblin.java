package nl.playdnd.character;

import nl.playdnd.dasic.DasicAI;

/**
 * Hobgoblin.java
 *
 * A hobgoblin is intended to be a weak monster. It is also the Foolish
 * Consistency of Little Minds.
 */
class Hobgoblin extends DnDCharacter {

  
    private final int HEALTH_MULTIPLIER = 10;

    public Hobgoblin() {
        name = "Hobgoblin";
        this.myWeapon = new Weapon("Cast Iron Skillet", 6);
        this.myArmor = new Armor("Trash Can Lid for a Breastplate, Suspended with Bungee Cords", 6);
        setHealth((int) ((Math.random() * HEALTH_MULTIPLIER) + 5));
        
    }

    @Override
    public DasicAI initDasic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initDasic'");
    }

    @Override
    public void interpret() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interpret'");
    }

    @Override
    public String getNorthImagePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNorthImagePath'");
    }

    @Override
    public String getEastImagePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEastImagePath'");
    }

    @Override
    public String getSouthImagePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSouthImagePath'");
    }

    @Override
    public String getWestImagePath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWestImagePath'");
    }
}