package legacy;
/*  
    This code is just because I got annoyed with writing "System.out.println();" every single time lmao
    We can prob expand this for testing other things as well by creating new methods to test more specific
    issues
 */
import java.util.Scanner;

public class Debug {

    private static long __chache__ = 0;

    public static void advance() {
        Scanner advance = new Scanner(System.in);
        
        print(advance.nextLine());

        advance.close();
    }

    public static void print(String s) {
        System.out.println(s);
    }
    public static void print(char c) {
        System.out.println(c);
    }
    public static void print(int i) {
        System.out.println(i);
    }
    public static void print(double d) {
        System.out.println(d);
    }
    public static void print(float f) {
        System.out.println(f);
    }
    public static void print(boolean b) {
        System.out.println(b);
    }

    public static void printMap(char[][] map) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[1].length; j++)
                System.out.print("\'" + map[i][j] + "\'");
            System.out.println();
        }
        System.out.println("");
    }

    public static void logTime(long s) {
        __chache__ = s;
    }

    public static long timeDifference(long s) {
        return s - __chache__;
    }
}
