package nl.playdnd.character;

import lombok.Getter;
import lombok.Setter;
import nl.playdnd.global.FaceTo;

@Setter
@Getter
public abstract class InlineValues {

    protected int health;

    protected int strength;

    protected String name;

    protected FaceTo faceTo;


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


    public abstract void move (int distance); 

    public abstract void faceTo (FaceTo faceTo); 
}
