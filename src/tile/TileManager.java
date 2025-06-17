package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        this.getTilesImage();
        this.loadMap("maps/map01.txt");
    }

    public void getTilesImage(){
          try {
              //Load the sprites
              tiles[0] = new Tile();
              tiles[0].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grass.png")));

              tiles[1] = new Tile();
              tiles[1].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/wall.png")));

              tiles[2] = new Tile();
              tiles[2].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/water.png")));
          }catch (IOException e){
              e.printStackTrace();
          }
    }
    public void draw(Graphics g2d){
         int col = 0;
         int row = 0;
         int x = 0;
         int y = 0;

         while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
             int tileNum = mapTileNum[col][row];
             g2d.drawImage(tiles[tileNum].img, x, y, gamePanel.TileSize, gamePanel.TileSize, null);
             x += gamePanel.TileSize;
             col++;
             if(col == gamePanel.maxScreenCol){
                 col = 0;
                 x = 0;
                 y += gamePanel.TileSize;
                 row++;
             }
         }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            int col = 0;
            int row = 0;

            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
                String line = br.readLine();

                while(col < gamePanel.maxScreenCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    //save the num tile in the map
                    mapTileNum[col][row] = num;
                    col++;
                }
                 if(col == gamePanel.maxScreenCol){
                     col = 0;
                     row++;
                 }
            }
            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
