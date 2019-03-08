package nf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) throws Exception {
        /*PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(

            "Andrews",
            "Rupy",
            Profession.SECRETAIRE
        );

        List<AbstractImage> listImage = new ArrayList<>();
        Image i = new Image("08470296");
        try {
*/
            Connexion connexion = new Connexion();

            connexion.connection("3","rupy123");
            Patient patient= new Patient();
            patient = connexion.getPatient("1848515115");
        ArrayList<DMR> dmr= connexion.getDMR();
        System.out.println(dmr.size());
        Patient p=dmr.get(0).getPatient();
       List<Examen> e=connexion.getExamen(p);
        System.out.println(e.get(0).getCr().getCompteRendu());
        String[] words = e.get(0).getCr().getCompteRendu().split("\\s");
        System.out.println(words.length);


/*

           PersonnelServiceRadio personnelServiceRadio1 = new PersonnelServiceRadio(
                    "57",
                    "Trouillet",
                    "Juliette",
                    Profession.PH
                    );

           String idMed="93547369";


            PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
                    "rupy",
                    "Andrews",
                    "Rupy",
                    Profession.SECRETAIRE
            );

            //connexion.addPersonnelServiceRadio(personnelServiceRadio);

            //ArrayList <DMR> s = connexion.getDMR();
            //ArrayList <PersonnelServiceRadio> psr;

            //PersonnelServiceRadio p=connexion.getPersonnelServiceRadio("2");

            //System.out.println(p.getProfession());

            ArrayList<Image> listImage = new ArrayList<>();

            /*Image i = new Image("08470296");
            Image i2 = new Image("864994949");
            Image i3 = new Image("13156212564829");

            i.setImage(ImageIO.read(new File("C:\\Users\\amanr\\Pictures\\jpg\\brain\\brain1_0000.jpg")));
            i2.setImage(ImageIO.read(new File("C:\\Users\\amanr\\Pictures\\jpg\\brain\\brain1_0008.jpg")));
            i3.setImage(ImageIO.read(new File("C:\\Users\\amanr\\Pictures\\jpg\\abdomen\\cor494-i387.jpg")));

            listImage.add(i);
            listImage.add(i2);
            listImage.add(i3);

           // connexion.insertImage(listImage);


            /*Patient claire = new Patient (

                    "983250865",
                    "565694949",
                    "Heissler",
                    "Claire",

                    new GregorianCalendar(1997, 10,7)

            );
            Patient patient3 = new Patient (
                    "6",
                    "9283Y59156906",
                    "Mottin",
                    "Laurence",
                    new GregorianCalendar(1968, 9,4)

            );
            //connexion.addPatient(claire);
            //connexion.addPatient(patient3);
            //System.out.println(patient3);
            Examen e=new Examen(
                    new GregorianCalendar(),
                    "08470296",
                    TypeExamen.SCANNER,
                    patient3,
                    personnelServiceRadio1,
                    ServiceHosp.PNEUMOLOGIE
            );

            //System.out.println(connexion.getPersonnelServiceRadio("93547369"));
                    //+connexion.getPersonnelServiceRadio("93547369").getIdMedical()+" "
                    //+connexion.getPersonnelServiceRadio("93547369").getNom()+" "
            //connexion.getPersonnelServiceRadio("93547369").getProfession());
            connexion.insertExamen(e);
           //connexion.addPersonnelServiceRadio(personnelServiceRadio);
           //connexion.addPersonnelServiceRadio(personnelServiceRadio2);

            //psr = connexion.getListePersonnel();
            //s=connexion.getDMR();




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

        Scanner scan = new Scanner(System.in);
        while ( !scan.nextLine().equals("-") ) { }
        HL7 hl7 = new HL7();
        hl7.sendMessage(examen, HL7.ADMIT_PATIENT);

        /*PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new ExamenPrinter(examen));
        if (job.printDialog()){
            try {
                job.print();
            } catch (Exception e){
                e.printStackTrace();
            }
        }*/
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
        frame.setVisible(true);

    }*/

}}





