package common.io;

/**
 * class with static methods for printing
 */
public interface OutputManager {
    public static void print(Object o){
        System.out.println(o.toString());
    }
    public static void printErr(Object o){
        System.out.println("Err: " + o.toString());
    }
}
