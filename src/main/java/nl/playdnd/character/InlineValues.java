package nl.playdnd.character;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.global.FaceTo;

@Setter
@Getter
public class InlineValues {

    protected int health;

    protected int strength;

    protected String name;

    protected FaceTo faceTo;

    protected int inCombat;


    public <O> Object getValue(String inline) {

        Object value = null;
        var fields = InlineValues.class.getDeclaredFields(); //get all fields

        for (var field : fields) { 
            if (field.getName().equals(inline)) { 
                try {
                    //System.out.println(field.get(this));
                    value = field.get(this); //getValue of field
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }            
        }
        return value;
    }


    public void move (int distance) {}; 

    public void faceTo (FaceTo faceTo) {
        System.out.println("SET "+faceTo);
        setFaceTo(faceTo) ;
    }

    public int getFaceArc() {
        switch (faceTo) {
            case NORTH : return 0;
            case EAST : return 90;
            case SOUTH : return 180;
            case WEST  : return 270;
        }
        return 0;
    }
}
