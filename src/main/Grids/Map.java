package main.Grids;
import main.Grid;
import main.Tile;
import main.Tiles.*;

public class Map implements Grid {
    Tile[][] land;
    public Map() {
        land = new Tile[6][11];
        for(int i = 0; i < 6; i ++) {
            land[i][0] = new HomeTile();
            land[i][10] = new ZombieTile();
            for(int j = 1; j < 10; j ++) {
                if(i == 2 || i == 3) {
                    land[i][j] = new PoolTile();
                }
                else {
                    land[i][j] = new GrassTile();
                }
            }
        }
    }
}
