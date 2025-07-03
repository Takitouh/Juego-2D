package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJChest extends SuperObject{
    public OBJChest() {
        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/OBJChest.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
