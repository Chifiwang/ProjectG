import java.util.ArrayList;


public class Map {
    static int[] dc = {0, -1, 0, 1, 0}, dr = {-1, 0, 1, 0, 0};
    static int r = 2, c = 4;
    static ArrayList<int[]> coords = new ArrayList<int[]>();
    static int direct = 4;

    public char[][] map;

    public Map() { }

    public Map(char[][] Loadedmap) {
        map = Loadedmap;
        
    }

    // public void redraw(char[][] map, boolean isValid) {
    //     if (isValid) {
    //         for (int r = 0; r < map.length; r++) {
    //             for (int c = 0; c < (map[r].length); c++) {
    //                 map[r][c] = ' ';
    //             }
    //         }

    //         for (int[] coord : coords) {
    //             if (coord[0] +  dr[direct] > -1 && coord[0] +  dr[direct] < bounds01[0] &&
    //                 coord[1] + dc[direct] > -1 && coord[1] + dc[direct] < bounds01[1])
    //             map[coord[0] +  dr[direct]][coord[1] + dc[direct]] = (char) coord[2]; 
    //         }
    //     }
    // }

    // public ArrayList<Block> initMap() {
    //     ArrayList<Block> blocks = new ArrayList<Block>();

    //     for (int r = 0; r < map.length; r++) {
    //         for (int c = 0; c < (map[r].length); c++) {
    //             if(map[r][c] != ' ' || map[r][c] != 'p') {
    //                 blocks.add(new Block(map[r][c]));
    //             }
    //         }
    //     }


    //     return blocks;
    // }

    final public int[] bounds01 = {5, 9};
    final public char[][] map01 = 
        {
            {' ', ' ', ' ', ' ', 'u', ' ', ' ', ' ', ' '}, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
            {'u', ' ', ' ', ' ', 'p', 'u', ' ', ' ', 'u'}, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
            {' ', ' ', ' ', ' ', 'u', ' ', ' ', ' ', ' '}
        };
}
