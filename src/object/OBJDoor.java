package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJDoor extends SuperObject{
    public OBJDoor() {
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/OBJDoor.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
