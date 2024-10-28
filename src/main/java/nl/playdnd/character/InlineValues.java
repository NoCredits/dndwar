package nl.playdnd.character;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class InlineValues {

    protected int health;

    protected int strength;

    protected String name;

    protected FaceTo face;


    public <O> Object getValue(String inline) {

        Object value = null;
        var fields = InlineValues.class.getDeclaredFields(); //get all fields

        for (var field : fields) { 
            if (field.getName().equals(inline)) { 
                try {
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

}
