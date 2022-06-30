import javax.swing.ImageIcon;
import java.awt.Image;

public class Player {


    ImageIcon pDefault = new ImageIcon("Assets/player_default.png");
    Debugg debugg = new Debugg();

    int col = 4, row = 2;

    int isMove = -1;
    Image pIcon;
    Map map;

    public Player(Map map) {
        pIcon = pDefault.getImage();
        this.map = map;
    }

    public void move() {
        char __cache__ = ' ';

        for (int r = Map.r, c = Map.c; r > -1 && r < map.bounds01[0] && 
        c > -1 && c < map.bounds01[1]; r -= Map.dr[Map.direct], c -= Map.dc[Map.direct]) {
            if (map.map[r][c] == 'p') {
                debugg.print("arrive???");
                map.map[r][c] = ' ';
                col = c + Map.dc[Map.direct];
                row = r + Map.dr[Map.direct];
                break;
            }
            debugg.print(c - Map.dc[Map.direct] + " " + c);
            // switch (map.map[r - Map.dr[Map.direct]][c - Map.dc[Map.direct]]) {
            //     case 'u': __cache__ = 'u'; break;
            //     default: __cache__ = ' '; break;
            // }

            map.map[r][c] = map.map[r - Map.dr[Map.direct]][c - Map.dc[Map.direct]];
            debugg.print("map.map[r][c]: " +map.map[r][c]);
            
        }
    }
    
    public Image getImage() {return pIcon;}

    public boolean canMove(int direct, int r, int c) {
        return r + Map.dr[direct] > -1 && r + Map.dr[direct] < map.bounds01[0] &&
               c + Map.dc[direct] > -1 && c + Map.dc[direct] < map.bounds01[1] ;
    }

    public boolean canMoveObj(int direct) {
        boolean isValid = true;
        debugg.print("canMoveObj");
        
        for(int r =  row, c = col; r > -1 && r < map.bounds01[0] && 
            c > -1 && c < map.bounds01[1]; r += Map.dr[direct], c += Map.dc[direct]) {

            if (map.map[r][c] == ' ') { 
                Map.r = r;
                Map.c = c;
                break; 
            } 

            else if (map.map[r][c] == 'p') {
                isValid = (canMove(direct, r, c)) ? isValid : false;

                int[] __data_cache__ = {r, c, 'p'};
                Map.coords.add(__data_cache__);
            }

            else if (map.map[r][c] != ' ') {
                isValid = (Block.canMoveObj(direct, map.map[r][c])) ? isValid : false;

                int[] __data_cache__ = {r, c, map.map[r][c]};
                Map.coords.add(__data_cache__);
            }
            
            Map.r = r;
            Map.c = c;
           
        }
        debugg.print("r and c:" + Map.r + " " + Map.c);
        return isValid;
    }
        
}
