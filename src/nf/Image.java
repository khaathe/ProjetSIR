package nf;

import javax.imageio.ImageIO;
import java.io.File;

public class Image extends AbstractImage {

    public Image (String numArchivage){
        super(numArchivage);
    }

    public void setImage (File file) throws Exception{
        this.image = ImageIO.read(file);
    }
}
