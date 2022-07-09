public class Map {

    static int[] dc = {0, -1, 0, 1, 0};
    static int[] dr = {-1, 0, 1, 0, 0};
    static int[] starBounds = new int[4];
    static int r = 2, c = 4;
    static int direct = 4;
    static int __mvntCache__ = 4;
    static int blockCount;

    public char[][] map;
    public boolean[][] map_move;

    public Map() { }

    /* loads the active map into an instantiated Map for future use */
    public Map(int mapIndex) {
        map = levelSelect[mapIndex];
        map_move = moveSelect[mapIndex];
        starBounds = starBoundSelect[mapIndex];
        blockCount = blockCountSelect[mapIndex];
        
    }

    
    /** 
     * finds the player coordinates for player initialization.
     * 
     * @param map provides the map that the player is being loaded into
     * 
     * @return int[] returns {row, column} data
     */
    public int[] loadPlayer(char[][] map) {
        int[] pCoords = new int[2];

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[1].length; c++)

                if (map[r][c] == 'p'){

                    pCoords[0] = r;
                    pCoords[1] = c;
                }         
        }

        return pCoords;
    }


    /* these are the internal maps */


    final public char[][] map00 = 
        {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
            {' ', ' ', ' ', ' ', ' ', 'p', 'u', ' ', ' ', ' ', ' '}, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}, 
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

    final public boolean[][] map00_move = new boolean[map00.length][map00[0].length];
    final public int[] map00_starBounds = {30, 31, 32};
    final public int map00_blockCount = 1;

    final public char[][] map01 = 
        {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', 'p', ' ', 'u', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        };

    final public boolean[][] map01_move = new boolean[map01.length][map01[0].length];
    final public int[] map01_starBounds = {10, 15, 20};
    final public int map01_blockCount = 1;

    /* map select array */
    final public char[][][] levelSelect = {
        map00, map01
    };

    final public boolean[][][] moveSelect = {
        map00_move, map01_move
    };

    final public int[][] starBoundSelect = {
        map00_starBounds, map01_starBounds
    };

    final public int[] blockCountSelect = {
        map00_blockCount, map01_blockCount
    };
}