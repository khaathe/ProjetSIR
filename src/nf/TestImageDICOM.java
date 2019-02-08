package nf;
import com.pixelmed.dicom.ImageToDicom;
import com.pixelmed.dicom.AttributeList;

public class TestImageDICOM{

    public static void main(String[] args){
        String scJpegFilePath = "C:\\Users\\amanr\\Desktop\\jpg\\jpg\\abdomen.jpg";
        String newDicomFile = "C:\\Users\\amanr\\Desktop\\jpg\\jpg\\Dupont.dcm";

        try{
            //generate the DICOM file from the jpeg file and the other attributes supplied
            new ImageToDicom(scJpegFilePath,

                                newDicomFile, //path to existing JPEG image

                                "Dupont Jean", //name of the patient
                                "12233115", //patient id
                                "2989873569",//study number
                                "3648763586", // series number
                                "32876348015");
            //now, dump the contents of the DICOM file to the console
            AttributeList list = new AttributeList();
            list.read(newDicomFile);
            System.out.println(list.toString());



        }catch (Exception e){
            e.printStackTrace();
        }
    }


}