package nf;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Image extends AbstractImage {

    public Image (String numArchivage){
        super(numArchivage);
    }

    public void setImage (File file) {
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage());
        }
    }
}
