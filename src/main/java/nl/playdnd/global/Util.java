package nl.playdnd.global;

public class Util {

    public static void sleep (double milliSeconds) {
        try {
            Thread.sleep((long) milliSeconds);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
