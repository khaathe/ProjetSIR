package nf;
import com.pixelmed.dicom.AttributeList;
import  com.pixelmed.dicom.ImageToDicom;

import java.awt.geom.AffineTransform;
import java.awt.image.*;

public abstract class Image {
    protected String numArchivage;
    protected BufferedImage image;
    public static final int ROTATE_LEFT = -1;
    public static final int ROTATE_RIGHT = 1;
    public static float BRIGHTER = 1.0f;
    public static float DARKER = -1.0f;


    public Image (String numArchivage){
        this.numArchivage= numArchivage;
    }

    public Image(){

    }

    public void rotation(int sens) {
        int centre = 0;
        switch(sens){
            case ROTATE_LEFT :
                centre=image.getWidth()/2;
                break;
            case ROTATE_RIGHT :
                centre = image.getHeight()/2;
                break;
        }
        AffineTransform t = AffineTransform.getQuadrantRotateInstance(sens, centre, centre);
        AffineTransformOp rotationImage = new AffineTransformOp(t, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage imageRetourner = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
        rotationImage.filter(image, imageRetourner);
        this.image = imageRetourner;
    }

    public void contraste(int contraste) {
        int w=image.getWidth(), h=image.getHeight();
        BufferedImage dst = new BufferedImage(w, h, image.getType());
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){

            }
        }
        image= dst;
    }

    public void eclaircissement(float offset) {
        RescaleOp op = new RescaleOp(1.0f, offset, null);
        BufferedImage eclairci = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        op.filter(image, eclairci);
        this.image = eclairci;
    }

    public void inversion() {
        BufferedImage dst = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        byte[] data = new byte[256];
        for(int i=0; i<256; i++){data[i] = (byte) (255-i);}
        ByteLookupTable table = new ByteLookupTable(0, data);
        LookupOp inversion = new LookupOp(table, null);
        inversion.filter(image, dst);
        image= dst;
    }

    public void retournementVertical() {
        int w = image.getWidth(), h = image.getHeight();
        BufferedImage dst = new BufferedImage(w, h, image.getType());
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                dst.setRGB(j,i, image.getRGB(w-1-j, i));
            }
        }
        image=dst;
    }

    public void retournementHorizontal() {
        int w = image.getWidth(), h = image.getHeight();
        BufferedImage dst = new BufferedImage(w, h, image.getType());
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                dst.setRGB(j,i, image.getRGB(j, h-1-i));
            }
        }
        image=dst;
    }
}
