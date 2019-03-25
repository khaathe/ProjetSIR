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


    public abstract void setImage(File file);


    public AbstractImage(String numArchivage) {
        this.numArchivage = numArchivage;
        if (nbInstanceParNumArchivage.containsKey(numArchivage))
            numInstance = nbInstanceParNumArchivage.get(numArchivage) + 1;
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

    public String getNumArchivage() {
        return numArchivage;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Methode qui realise une rotation a 90 degres de l'image envoye en parametre.
     * Cette methode utilise la valeur du parametre rotation de la clase.
     * La methode utilise la methode quadrantRotate() de la classe AffineTransform.
     * @param src
     *      Image source qui sera modifie
     * @return
     *      Image modifie
     * @see AffineTransform
     */
    public BufferedImage rotation(BufferedImage src) {
        int w = src.getWidth();
        int h = src.getHeight();
        int type = src.getType();
        double anchorx = 0;
        double anchory = 0;
        if (this.getRotation() == ROTATE_RIGHT) {
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


    /**
     * Methode qui modifie le contraste suivant la valeur de l'attribut contraste
     * de la classe. Elle utilise pour cela une convolution avec une matrice.
     * @param src
     *      Image source qui sera modifie
     * @return
     *      Image modifie
     * @see ConvolveOp
     */
    public BufferedImage contraste(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        float coeff = (float) contraste;
        float[] accentuation = {
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 0.0f
        };
        if (contraste > 1) {
            accentuation = new float[]{
                    0.0f, -1.0f * coeff, 0.0f,
                    -1.0f * coeff, 5.0f * coeff, -1.0f * coeff,
                    0.0f, -1.0f * coeff, 0.0f
            };
        }
        ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, accentuation));
        cop.filter(src, dst);
        return dst;
    }


    /**
     * Methode qui modifie la luminosite suivant la valeur de l'attribut luminosite
     * de la classe. La methode utilise pour cela un objet RescaleOP.
     * @param src
     *      Image source qui sera modifie
     * @return
     *      Image modifie
     * @see RescaleOp
     */
    public BufferedImage eclaircissement(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        float scale = 1.0f + ((float) getLuminosite() / 100.0f);
        RescaleOp op = new RescaleOp(scale, 0.0f, null);
        op.filter(src, dst);
        return dst;
    }

    /**
     * Methode qui inverse les couleurs de l'image. Pour cela une LookupOp est utilisee.
     * Cet objet necessite une ByteLookupTable dont les valeurs des indices
     * correspondent aux couleurs et les valeurs du tableau, la nouvelle couleur.
     * Le tableau contient donc les couleurs inverser.
     * @param src
     *      Image source qui sera modifie
     * @return
     *      Image modifie
     * @see  ByteLookupTable
     * @see LookupOp
     */
    public BufferedImage inversion(BufferedImage src) {
        BufferedImage imageInverse = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        byte[] data = new byte[256];
        for (int i = 0; i < 256; i++) {
            data[i] = (byte) (255 - i);
        }
        ByteLookupTable table = new ByteLookupTable(0, data);
        LookupOp inversion = new LookupOp(table, null);
        inversion.filter(src, imageInverse);
        return imageInverse;
    }

    /**
     * Methode qui retourne horizontalement une image.
     * La methode va parcourir l'image source et affecte chaque pixel de l'image source
     * dans l'image de destination de sorte a obtenir une imge retournee.
     * @param src
     *      Image source qui sera modifie
     * @return
     *      Image modifie
     */
    public BufferedImage retournementHorizontal(BufferedImage src) {
        int w = src.getWidth();
        int h = src.getHeight();
        BufferedImage imageRetourner = new BufferedImage(w, h, image.getType());
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                imageRetourner.setRGB(j, i, src.getRGB(j, h - 1 - i));
            }
        }
        return imageRetourner;
    }

    /**
     * Methode qui modifie l'attribut rotation suivant le sens envoyer
     * en parametre et la valeur de rotation actuelle.
     * @param sens
     *      Sens dans lequel la rotation se fait
     */
    public void setRotation(int sens) {
        if (sens == ROTATE_LEFT) {
            if (this.getRotation() == ROTATE_LEFT)
                this.rotation = ROTATE_LEFT;
            else if (this.getRotation() == ROTATE_RIGHT)
                this.rotation = NO_ROTATE;
            else
                this.rotation = sens;
        }
        else if (sens == ROTATE_RIGHT) {
            if (this.getRotation() == ROTATE_LEFT)
                this.rotation = NO_ROTATE;
            else if (this.getRotation() == ROTATE_RIGHT)
                this.rotation = ROTATE_RIGHT;
            else
                this.rotation = sens;
        }
    }

    public void setContraste(int contraste) {
        this.contraste = contraste;
    }

    public void setLuminosite(int luminosite) {
        this.luminosite = luminosite;
    }

    public void setInverser() {
        this.inverser = !(this.isInverser());
    }

    public void setRetourner() {
        this.retourner = !(this.isRetourner());
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    /**
     * Methode qui retourne l'image de l'attibut image modifie suivant les parametres
     * de rotation, luminosite, inversion et retournement.
     * @return
     *      Image modifie
     */
    public BufferedImage getImage() {
        BufferedImage newImage = this.image;
        if (this.getRotation() != NO_ROTATE)
            newImage = rotation(newImage);
        newImage = eclaircissement(newImage);
        newImage = contraste(newImage);
        if (this.isInverser())
            newImage = inversion(newImage);
        if (isRetourner())
            newImage = retournementHorizontal(newImage);
        return newImage;
    }

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
