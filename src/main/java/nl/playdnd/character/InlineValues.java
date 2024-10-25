package nl.playdnd.character;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class InlineValues {

    protected int health;

    protected int strength;

    protected String name;

    public int getValue(String inline) {
        switch (inline){
            case "health" : return health;
            case "strength" : return strength;
            default : return 0;
        }

    }

    public String getStringValue(String inline) {
        switch (inline){
            case "health" : return String.valueOf(health);
            case "strength" : return String.valueOf(strength);
            case "name" : return name;
            default : return "unknown";
        }

    }

}
