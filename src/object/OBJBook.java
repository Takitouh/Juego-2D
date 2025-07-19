package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJBook extends SuperObject {
    public OBJBook() {
        name = "Book";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/OBJBook.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
