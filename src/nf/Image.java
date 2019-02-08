package nf;
import com.pixelmed.dicom.AttributeList;
import  com.pixelmed.dicom.ImageToDicom;

public abstract class Image {
    protected String numArchivage;


    public Image (String numArchivage){

        this.numArchivage= numArchivage;
    }
    public Image(){

    }

    public abstract void rotation();
    public abstract void contraste();
    public abstract void eclaircissement();
    public abstract void inversion();
    public abstract void retournement();

}
