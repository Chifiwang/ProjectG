public class Map {
    public int playerCoords;
    public Map() { }
    public char[] map;

    public Map(char[] Loadedmap) {
        getPlayerCoords(Loadedmap);
        map = Loadedmap;
        
    }

    final public int[] MAP_TEST_COORD_GET = {9, 5};
    public char[] map_test = 
        {
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 
            ' ', ' ', ' ', ' ', 'p', ' ', ' ', ' ', ' ', 
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 
            ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '
        };

    public void getPlayerCoords(char[] map) {
        for(int i = 0; i < map.length; i++) {
            if( map[i] == 'p') {this.playerCoords = i;}
        }
    }
    
    


}
