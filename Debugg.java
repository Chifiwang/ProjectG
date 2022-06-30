/*  
    This code is just because I got annoyed with writing "System.out.println();" every single time lmao
    We can prob expand this for testing other things as well by creating new methods to test more specific
    issues
 */


public class Debugg {
    public void print(String s) {
        System.out.println(s);
    }
    public void print(char c) {
        System.out.println(c);
    }
    public void print(int i) {
        System.out.println(i);
    }
    public void print(double d) {
        System.out.println(d);
    }
    public void print(float f) {
        System.out.println(f);
    }
    public void print(boolean b) {
        System.out.println(b);
    }

    public void printMap(char[][] map) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[1].length; j++)
                System.out.print("\'" + map[i][j] + "\'");
            System.out.println();
        }
        System.out.println("");
    }
}
