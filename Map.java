import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.stream.Stream;

public class Map {
    ImageIcon tile = new ImageIcon("Assets\\tileBlockNormal.png");
    Image tileImg = tile.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);

    static int[] dc = {0, -1, 0, 1, 0};
    static int[] dr = {-1, 0, 1, 0, 0};
    static int[] starBounds = new int[4];

    static int r = 2, c = 4;
    static int direct = 4;
    static int blockCount;

    public char[][] map;
    public boolean[][] map_move;

    public Map() { }

    /* loads the active map into an instantiated Map for future use */
    public Map(int mapIndex) {
        
        loadSave(Integer.toString(mapIndex));
        
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

    public void loadSave(String id) {

        String save = Bson.getClass(id);

        if(save.length() > 0) {
            int col = Integer.valueOf(save.substring(save.indexOf("col") + 7, save.indexOf("row") - 3));
            int row = Integer.valueOf(save.substring(save.indexOf("row") + 7, save.indexOf("map") - 3));
            String mapString = save.substring(save.indexOf("map") + 8, save.indexOf("starBounds") - 4);

            map = new char[row][col];
            for(int i = 0; i < col*row; i++)
                map[i / col][i % col] = mapString.charAt(i);

            map_move = new boolean[row][col];

            starBounds = Stream.of(save.substring(save.indexOf("starBounds") + 14, save.indexOf("blockCount") - 3).split(", ")).mapToInt(Integer::parseInt).toArray();

            blockCount = Integer.valueOf(save.substring(save.indexOf("blockCount") + 14, save.indexOf("}") - 1));
            
        } else {
            // GameFrame.menu.quitGame();
        }
    }
}