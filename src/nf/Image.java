package nf;
import com.pixelmed.dicom.AttributeList;
import  com.pixelmed.dicom.ImageToDicom;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public abstract class Image {
    protected String numArchivage;
    private BufferedImage image, original;
    public static final int ROTATE_LEFT = -1;
    public static final int ROTATE_RIGHT = 1;
    public static final int NO_ROTATE = 0;
    private int rotation;
    private int contraste;
    private int luminosite;
    private boolean inverser;
    private boolean retourner;


    public Image (String numArchivage){
        this.numArchivage= numArchivage;
        rotation = NO_ROTATE;
        inverser = false;
        retourner = false;
        contraste = 1;
        luminosite = 0;
    }

    public void setImage (BufferedImage image) {
        this.original = image;
    }

    protected BufferedImage rotation (BufferedImage src){
        int w = src.getWidth(), h = src.getHeight(), type = src.getType();
        double anchorx = 0, anchory = 0;
        if(this.rotation == ROTATE_RIGHT) {
            anchorx = (double) w / 2.0;
            anchory = (double) w / 2.0;
        } else {
            anchorx = (double) h / 2.0;
            anchory = (double) h / 2.0;
        }
        BufferedImage dst = new BufferedImage(h, w, type);
        AffineTransform transform = new AffineTransform();
        transform.quadrantRotate(this.rotation, anchorx, anchory);
        AffineTransformOp operation = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        operation.filter(src, dst);
        return dst;
    }

    protected BufferedImage contraste(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        float coeff = (float) this.contraste;
        int w=src.getWidth(), h=src.getHeight();
        float[] accentuation = {
                0f, 0f, 0f,
                0.0f, coeff, 0.0f,
                0f, 0f, 0f
        };
        ConvolveOp cop = new ConvolveOp(new Kernel(3,3, accentuation));
        cop.filter(src, dst);
        return  dst;
    }

    protected BufferedImage eclaircissement(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        float scale = 1.0f + ( (float) luminosite/100.0f);
        RescaleOp op = new RescaleOp(scale, 0.0f, null);
        op.filter(src, dst);
        return dst;
    }

    protected BufferedImage inversion(BufferedImage src) {
        BufferedImage inverser = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        byte[] data = new byte[256];
        for(int i=0; i<256; i++){data[i] = (byte) (255-i);}
        ByteLookupTable table = new ByteLookupTable(0, data);
        LookupOp inversion = new LookupOp(table, null);
        inversion.filter(src, inverser);
        return inverser;
    }

    protected BufferedImage retournementVertical(BufferedImage src) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage retourner = new BufferedImage(w, h, image.getType());
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                retourner.setRGB(j,i, src.getRGB(w-1-j, i));
            }
        }
        return retourner;
    }

    protected BufferedImage retournementHorizontal (BufferedImage src) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage retourner = new BufferedImage(w, h, image.getType());
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                retourner.setRGB(j,i, src.getRGB(j, h-1-i));
            }
        }
        return retourner;
    }

    public BufferedImage getImage (){return  this.image;}

    public void setRotation (int sens){
        switch (sens){
            case ROTATE_LEFT :
                if(this.rotation == ROTATE_LEFT)
                    this.rotation = ROTATE_LEFT;
                else if(this.rotation == ROTATE_RIGHT)
                    this.rotation = NO_ROTATE;
                else
                    this.rotation = sens;
                break;
            case ROTATE_RIGHT :
                if(this.rotation == ROTATE_LEFT)
                    this.rotation = NO_ROTATE;
                else if(this.rotation == ROTATE_RIGHT)
                    this.rotation = ROTATE_RIGHT;
                else
                    this.rotation = sens;
                break;
        }
        refresh();
    }

    public void setContraste (int contraste){
        this.contraste = contraste;
        refresh();
    }

    public void setLuminosite (int luminosite){
        this.luminosite = luminosite;
        refresh();
    }

    public void setInverser (){
        this.inverser = !(this.inverser);
        refresh();
    }
    public void setRetourner (){
        this.retourner = !(this.retourner);
        refresh();
    }

    protected void refresh (){
        BufferedImage newImage = this.original;
        if(this.rotation != NO_ROTATE)
            newImage = rotation(newImage);
        newImage = eclaircissement(newImage);
        newImage = contraste(newImage);
        if(this.inverser)
            newImage = inversion(newImage);
        if(retourner)
            newImage = retournementHorizontal(newImage);
        this.image = newImage;
    }

    public  void reset () {image = original;}

    public void save () { original = image;}
}
