import java.util.ArrayList;


public class Map {
    public int playerCoords;
    public Map() { }
    public char[] map;
    ArrayList<Integer> basicCoords = new ArrayList<Integer>();

    public Map(char[] Loadedmap) {
        getPlayerCoords(Loadedmap);
        getBBlockCoords(Loadedmap);
        map = Loadedmap;
        
    }

    final public int[] MAP_TEST_COORD_GET = {9, 5};
    public char[] map_test = 
        {
            ' ', ' ', ' ', ' ', 'u', ' ', ' ', ' ', ' ', 
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 
            'u', ' ', ' ', ' ', 'p', ' ', ' ', ' ', 'u', 
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 
            ' ', ' ', ' ', ' ', 'u', ' ', ' ', ' ', ' '
        };

    public void getPlayerCoords(char[] map) {
        for(int i = 0; i < map.length; i++) {
            if( map[i] == 'p') {this.playerCoords = i;}
        }
    }
    
    public void getBBlockCoords(char[] map) {
        for(int i = 0; i < map.length; i++) {
            if( map[i] == 'u') {this.basicCoords.add(i);}
        }
    }

    public void redraw(char[] map) {
    	for (int i = 0; i < map.length; i++) {
    		if (i == playerCoords) map[i] = 'p';
    		else if (basicCoords.contains(i)) map[i] = 'u';
    		else map[i] = ' ';
    	}
    }
}
