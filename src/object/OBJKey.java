package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJKey extends SuperObject{

    public OBJKey() {
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/OBJKey.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
