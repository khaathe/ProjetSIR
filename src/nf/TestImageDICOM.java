package nf;
import com.pixelmed.dicom.ImageToDicom;
import com.pixelmed.dicom.AttributeList;

public class TestImageDICOM{

    public static void main(String[] args){
        String scJpegFilePath = "C:\\Users\\amanr\\Desktop\\jpg\\jpg\\abdomen";
        String newDicomFile = "C:\\Users\\amanr\\Desktop\\jpg\\jpg\\abdomen.dcm";

        try{
            //generate the DICOM file from the jpeg file and the other attributes supplied
            new ImageToDicom(scJpegFilePath,
                    newDicomFile, //path to existing JPEG image

                                "Dupont Jean", //name of the patient
                                "12233117", //patient id
                                "2989873564",//study number
                                "3648763589", // series number
                                "32876348013");
            //now, dump the contents of the DICOM file to the console
            AttributeList list = new AttributeList();
            list.read(newDicomFile);
            System.out.println(list.toString());



        }catch (Exception e){
            e.printStackTrace();
        }
    }


}