package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJSketcher extends SuperObject{
    public OBJSketcher() {
        name = "Sketcher";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/OBJSketchers.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
