package nl.playdnd.character;

import nl.playdnd.dasic.DasicAI;

/**
 * Bugbear.java
 *
 * A Bugbear is intended to be a "medium-strength" monster.
 */
class Bugbear extends DnDCharacter {
    
    private final int HEALTH_MULTIPLIER = 15;

    public Bugbear() {
        name = "Bugbear";
        this.myWeapon = new Weapon("Really Big Fly Swatter", 8);
        this.myArmor = new Armor("Hefty Garbage Bag, with Holes Cut Out for Arms", 4);
        setHealth( (int) ((Math.random() * HEALTH_MULTIPLIER) + 10));
      
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