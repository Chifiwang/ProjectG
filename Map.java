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
    static boolean firstGame;

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

    
    /** 
     * takes the given class and puts it into local memory for use in game
     * 
     * @param id provides the class that needs to be loaded
     */
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

            firstGame = save.contains("true");

        } else {
            GameFrame.exitGame();
        }
    }

    /** 
     * rewrites the map class structure data based on the class that 
     * is being updated
     * 
     * @param starsAchived provides the field "starsAchived"s data
     * @param id provides the class structure that needs updating
     * @return String returns the updated class structure
     */
    public static String rewriteMapData(int starsAchived, String id) {
        String newData = Bson.getClass(id);

        newData = newData.replaceAll("true", "false");
        newData = newData.replace(newData.substring(newData.indexOf("\n\t\"starsAchived\" : ") + "\n\t\"starsAchived\" : ".length() - 1, 
                  newData.indexOf("\n\t\"col\"")), " " +Integer.toString(Math.max(starsAchived, 
                  Integer.parseInt(newData.substring(newData.indexOf("\n\t\"starsAchived\" : ") + "\n\t\"starsAchived\" : ".length(), 
                  newData.indexOf("\n\t\"col\""))))));
                  
        return newData;
    }
}