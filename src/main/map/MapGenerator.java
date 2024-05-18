package main.map;

import java.awt.Color;
import java.awt.Graphics2D;
import main.Mainframe;

public class MapGenerator {
    Mainframe gamePanel;
    Grid[] grids;

    public MapGenerator(Mainframe gamePanel)
    {
        this.gamePanel = gamePanel;
        grids = new Grid[7];
        loadGrid();
    }

    // TODO : GET IMAGE FROM SOURCE!
    private void loadGrid()
    {   
        grids[0] = new Grid(new Color(0,102,0));
        grids[1] = new Grid(new Color(0,204,0));
        grids[2] = new Grid(new Color(51,153,255));
        grids[3] = new Grid(new Color(102,51, 0));
        grids[4] = new Grid(new Color(255,102,102));
        grids[5] = new Grid(new Color(102,0,153));
        grids[6] = new Grid(new Color(102,102,102));
        
    }

    public void drawMap(Graphics2D g)
    {
        for(int i = 0; i < gamePanel.maxRow; i++)
        {
            for(int j = 0; j < gamePanel.maxCol; j++)
            {
                // determining texture
                // offset
                if(i == gamePanel.maxRow-1) g.setColor(grids[6].color);
                // not offset
                else
                {
                    // home area
                    if(j < 2) g.setColor(grids[4].color);
                    // spawn area
                    else if(j > 10) g.setColor(grids[5].color);
                    // deck, grass, and water
                    else
                    {
                        // deck
                        if(i < 2) g.setColor(grids[3].color);
                        // dark / light grass
                        else if(i < 4 || i > 5)
                        {
                            if(i%2 == 0) g.setColor(grids[j%2].color);
                            else g.setColor(grids[(j+1)%2].color);
                        } 
                        // water
                        else g.setColor(grids[2].color);
                    }
                }

                // calculate position
                int x = j*gamePanel.gridSizeX;
                int y = i*gamePanel.gridSizeY;
                
                // TODO : DRAWING IMAGE
                g.fillRect(x, y, gamePanel.gridSizeX, gamePanel.gridSizeY);
            }
        }
    }
}