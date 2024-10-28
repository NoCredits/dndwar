package nl.playdnd.character;

import nl.playdnd.dasic.DasicAI;

/**
 * Dragon.java
 *
 * A Dragon is intended to be a powerful monster, with a lot of treasure.
 */
class Dragon extends DnDCharacter {
    
    private final int HEALTH_MULTIPLIER = 25;

    public Dragon() {
        this.name = "Dragon";
        this.myWeapon = new Weapon("Snoutful of Flames", 10);
        this.myArmor = new Armor("Enchanted Hide", 10);
        setHealth( (int) ((Math.random() * HEALTH_MULTIPLIER) + 20));
        
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