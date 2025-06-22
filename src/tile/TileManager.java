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
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[11];
        mapTileNum = new int[gamePanel.maxworldCol][gamePanel.maxworldRow];
        this.getTilesImage();
        this.loadMap("maps/world01.txt");
    }

    public void getTilesImage(){
          try {
              //Load the sprites
              tiles[0] = new Tile();
              tiles[0].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grass.png")));

              tiles[1] = new Tile();
              tiles[1].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/wall.png")));
              tiles[1].collision = true;

              tiles[2] = new Tile();
              tiles[2].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/water.png")));
              tiles[2].collision = true;

              tiles[3] = new Tile();
              tiles[3].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/dirt.png")));

              tiles[4] = new Tile();
              tiles[4].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/sand.png")));

              tiles[5] = new Tile();
              tiles[5].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/basictree.png")));
              tiles[5].collision = true;

              tiles[6] = new Tile();
              tiles[6].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/appletree.png")));
              tiles[6].collision = true;

              tiles[7] = new Tile();
              tiles[7].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/dandelion.png")));

              tiles[8] = new Tile();
              tiles[8].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/daisy.png")));

              tiles[9] = new Tile();
              tiles[9].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/woodenplate.png")));

              tiles[10] = new Tile();
              tiles[10].img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/lemontree.png")));
              tiles[10].collision = true;

          }catch (IOException e){
              e.printStackTrace();
          }
    }
    public void draw(Graphics g2d){
         int worldCol = 0;
         int worldRow = 0;

         while(worldCol < gamePanel.maxworldCol && worldRow < gamePanel.maxworldRow){
             int tileNum = mapTileNum[worldCol][worldRow];

             int worldX = worldCol * gamePanel.tileSize;
             int worldY = worldRow * gamePanel.tileSize;
             int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
             int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

             if     (worldX  + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                     && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                     && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                     && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){
                 g2d.drawImage(tiles[tileNum].img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
             }
             worldCol++;
             if(worldCol == gamePanel.maxworldCol){
                 worldCol = 0;
                 worldRow++;
             }
         }
    }

    public void loadMap(String filePath){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));
            int col = 0;
            int row = 0;

            while(col < gamePanel.maxworldCol && row < gamePanel.maxworldRow){
                String line = br.readLine();

                while(col < gamePanel.maxworldCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    //save the num tile in the map
                    mapTileNum[col][row] = num;
                    col++;
                }
                 if(col == gamePanel.maxworldCol){
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
