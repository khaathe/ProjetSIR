package nf;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
            "rupy",
            "Andrews",
            "Rupy",
            Profession.SECRETAIRE
        );

        Patient patient = new Patient (
                "983250865",
                "565694949",
                "Heissler",
                "Claire",
                new GregorianCalendar(1997, 10,7)
        );

        ArrayList<AbstractImage> listImage = new ArrayList<>();
        Image i = new Image("08470296");
        try {
            i.setImage(new File( "D:\\ProjetSIR\\ProjetTIS4\\jpg\\brain\\brain1_0000.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        listImage.add(i);
        listImage.add(i);
        listImage.add(i);
        listImage.add(i);
        listImage.add(i);
        listImage.add(i);
        listImage.add(i);
        listImage.add(i);

        String cr = "";
        try {
            Scanner scanner = new Scanner( new File("src/nf/compteRendu.txt"));
            while (scanner.hasNextLine()) { cr+= scanner.nextLine() + "\n"; }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CompteRendu compteRendu = new CompteRendu("08470296",cr);

        Examen examen = new Examen(
                new GregorianCalendar(),
                "08470296",
                TypeExamen.SCANNER,
                patient,
                personnelServiceRadio,
                ServiceHosp.PNEUMOLOGIE,
                listImage,
                compteRendu
        );

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new ExamenPrinter(examen));
        if (job.printDialog()){
            try {
                job.print();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        /*JFrame frame = new JFrame();
        JPanel main = new JPanel();
        JPanel south = new JPanel();
        AbstractImage image = new PGM (Examen.generateNumArchivage());
        try {
            image.setImage(new File("D:\\ProjetSIR\\ProjetTIS4\\pgm\\brain\\brain1_0000.pgm"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton rotation_left = new JButton("rotation_left");
        JButton rotation_right = new JButton("rotation_right");
        JButton numerisation = new JButton("numerisation");
        ImagePanel img = new ImagePanel(image.getImage());
        rotation_left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image.setRotation(AbstractImage.ROTATE_RIGHT);
                img.setImg(image.getImage());
                img.repaint();
            }
        });
        rotation_right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image.setRotation(AbstractImage.ROTATE_LEFT);
                img.setImg(image.getImage());
                img.repaint();
            }
        });
        numerisation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedImage buff = new Numeriseur("000").run().getImage();
                image.setImage(buff);
                img.setImg(buff);

            }
        });
        south.add(numerisation);
        south.add(rotation_left);
        south.add(rotation_right);
        main.setLayout(new BorderLayout());
        main.add(south, BorderLayout.SOUTH);
        main.add(img, BorderLayout.CENTER);
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);*/
    }

}

