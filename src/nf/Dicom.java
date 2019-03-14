package nf;

import java.io.File;

public class Dicom extends AbstractImage {

    public Dicom (String numArchivage){
        super(numArchivage);
    }

    @Override
    public void setImage(File file) throws Exception {
        /*DicomInputStream dis = new DicomInputStream(file);
        Attributes object2 = dis.readDataset(-1, -1);
        String value2 = object2.toString(Tag.FileMetaInformationVersion, Tag.AcquisitionContextDescription);
        Scanner scan = new Scanner(value2);
        Pattern instancePattern = Pattern.compile("^\\(0020,0013\\)\\s+.+\\s+\\[(\\d+)");
        while( scan.hasNextLine()){
            String line = scan.nextLine();
            Matcher matcher = instancePattern.matcher(line);
            if (matcher.find())
                this.numInstance = Integer.parseInt(matcher.group(1));
        }
        BasicConfigurator.configure();
        Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
        ImageReader reader = iter.next();
        DicomImageReadParam param1 = (DicomImageReadParam) reader.getDefaultReadParam();
        ImageInputStream in = ImageIO.createImageInputStream(file);
        reader.setInput(in, false);
        image = reader.read(0, param1);
        reader.dispose();*/
    }
}
