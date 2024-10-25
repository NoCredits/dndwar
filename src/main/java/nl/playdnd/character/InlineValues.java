package nl.playdnd.character;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class InlineValues {

    protected int health;

    protected int strength;

    protected String name;

    public int agetValue(String inline) {
        switch (inline) {
            case "health":
                return health;
            case "strength":
                return strength;
            default:
                return 0;
        }

    }

    public String bgetValue(String inline) {
        switch (inline) {
            case "health":
                return String.valueOf(health);
            case "strength":
                return String.valueOf(strength);
            case "name":
                return name;
            default:
                return "unknown";
        }

    }

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
