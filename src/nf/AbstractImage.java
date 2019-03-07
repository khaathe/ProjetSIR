package nf;

import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.util.HashMap;

public abstract class AbstractImage {
    public static final int ROTATE_LEFT = -1;
    public static final int ROTATE_RIGHT = 1;
    public static final int NO_ROTATE = 0;
    protected static HashMap<String, Integer> nbInstanceParNumArchivage = new HashMap<>();
    protected String numArchivage;
    protected String annotation;
    protected BufferedImage image;
    protected int numInstance;
    private int rotation;
    private int contraste;
    private int luminosite;
    private boolean inverser;
    private boolean retourner;


    public abstract void setImage (File file) throws Exception;


    public AbstractImage(String numArchivage){
        this.numArchivage = numArchivage;
        if (nbInstanceParNumArchivage.containsKey(numArchivage))
            numInstance = nbInstanceParNumArchivage.get(numArchivage)+1;
        else
            numInstance = 1;
        nbInstanceParNumArchivage.put(numArchivage, numInstance);
        this.image = null;
        setRotation(NO_ROTATE);
        inverser = false;
        retourner = false;
        setContraste(1);
        luminosite = 0;
        annotation = "";
    }

    public static int getNoRotate() {
        return NO_ROTATE;
    }

    public String getNumArchivage (){ return  numArchivage;}

    public void setImage (BufferedImage image) {
        this.image = image;
    }

    public BufferedImage rotation (BufferedImage src){
        int w = src.getWidth(), h = src.getHeight(), type = src.getType();
        double anchorx = 0, anchory = 0;
        if(this.getRotation() == ROTATE_RIGHT) {
            anchorx = (double) h / 2.0;
            anchory = (double) h / 2.0;
        } else {
            anchorx = (double) w / 2.0;
            anchory = (double) w / 2.0;
        }
        BufferedImage dst = new BufferedImage(h, w, type);
        AffineTransform transform = new AffineTransform();
        transform.quadrantRotate(this.getRotation(), anchorx, anchory);
        AffineTransformOp operation = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        operation.filter(src, dst);
        return dst;
    }

    public BufferedImage contraste(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        float coeff = (float) contraste;
        float[] accentuation = {
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f
        };
        if (contraste > 1){
            accentuation = new float[] {
                    0.0f, -1.0f*coeff, 0.0f,
                    -1.0f*coeff, 5.0f*coeff, -1.0f*coeff,
                    0.0f, -1.0f*coeff, 0.0f
            };
        }
        ConvolveOp cop = new ConvolveOp(new Kernel(3,3, accentuation));
        cop.filter(src, dst);
        return  dst;
    }

    public BufferedImage eclaircissement(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        float scale = 1.0f + ( (float) getLuminosite() /100.0f);
        RescaleOp op = new RescaleOp(scale, 0.0f, null);
        op.filter(src, dst);
        return dst;
    }

    public BufferedImage inversion(BufferedImage src) {
        BufferedImage inverser = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        byte[] data = new byte[256];
        for(int i=0; i<256; i++){data[i] = (byte) (255-i);}
        ByteLookupTable table = new ByteLookupTable(0, data);
        LookupOp inversion = new LookupOp(table, null);
        inversion.filter(src, inverser);
        return inverser;
    }

    public BufferedImage retournementVertical(BufferedImage src) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage retourner = new BufferedImage(w, h, image.getType());
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                retourner.setRGB(j,i, src.getRGB(w-1-j, i));
            }
        }
        return retourner;
    }

    public BufferedImage retournementHorizontal (BufferedImage src) {
        int w = src.getWidth(), h = src.getHeight();
        BufferedImage retourner = new BufferedImage(w, h, image.getType());
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                retourner.setRGB(j,i, src.getRGB(j, h-1-i));
            }
        }
        return retourner;
    }

    public void setRotation (int sens){
        switch (sens){
            case ROTATE_LEFT :
                if(this.getRotation() == ROTATE_LEFT)
                    this.rotation = ROTATE_LEFT;
                else if(this.getRotation() == ROTATE_RIGHT)
                    this.rotation = NO_ROTATE;
                else
                    this.rotation = sens;
                break;
            case ROTATE_RIGHT :
                if(this.getRotation() == ROTATE_LEFT)
                    this.rotation = NO_ROTATE;
                else if(this.getRotation() == ROTATE_RIGHT)
                    this.rotation = ROTATE_RIGHT;
                else
                    this.rotation = sens;
                break;
        }
    }

    public void setContraste (int contraste){
        this.contraste = contraste;
    }

    public void setLuminosite (int luminosite){
        this.luminosite = luminosite;
    }

    public void setInverser (){
        this.inverser = !(this.isInverser());
    }

    public void setRetourner (){
        this.retourner = !(this.isRetourner());
    }

    public void setAnnotation (String annotation) { this.annotation = annotation; }

    public BufferedImage getImage (){
        BufferedImage newImage = this.image;
        if(this.getRotation() != NO_ROTATE)
            newImage = rotation(newImage);
        newImage = eclaircissement(newImage);
        newImage = contraste(newImage);
        if(this.isInverser())
            newImage = inversion(newImage);
        if(isRetourner())
            newImage = retournementHorizontal(newImage);
        return newImage;
    }

    public void save () { image = getImage();}

    public int getNumInstance() {
        return numInstance;
    }

    public void setNumInstance(int numInstance) {
        this.numInstance = numInstance;
    }

    public String getAnnotation() {
        return annotation;
    }

    public int getRotation() {
        return rotation;
    }

    public int getContraste() {
        return contraste;
    }
    public boolean getRetourner(){ return isRetourner(); }

    public int getLuminosite() {
        return luminosite;
    }

    public boolean isInverser() {
        return inverser;
    }

    public boolean isRetourner() {
        return retourner;
    }
}
