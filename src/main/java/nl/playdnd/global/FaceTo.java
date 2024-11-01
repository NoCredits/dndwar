package nl.playdnd.global;

public enum FaceTo {

    NORTH, EAST, SOUTH, WEST;

    public static FaceTo getFace (String face)  {
        for (FaceTo val:FaceTo.values()) {
            if (val.name().equals(face))    return val;
        }
        return null;
    }
}
