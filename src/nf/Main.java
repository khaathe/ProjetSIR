package nf;

import library.interfaces.ClientHL7;

import java.util.GregorianCalendar;
public class Main {

    public static void main(String[] args) {
        Patient patient = new Patient(
                "",
                "565694949",
                "Heissler",
                "Claire",
                new GregorianCalendar(1997, 10, 7)
        );

        ClientHL7 clientHL7 = new ClientHL7();
        clientHL7.connexion("130.190.72.43", 50000);
        library.interfaces.Patient p = new library.interfaces.Patient(Integer.parseInt(patient.getIdPatient()), patient.getNom(), 'N');
        p.setFirstName(patient.getPrenom());
        p.setBirth(patient.getNaissance().getTime());
        clientHL7.admit(p);
        clientHL7.close();

       /* PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
            "rupy",
            "Andrews",
            "Rupy",
            Profession.SECRETAIRE
        );

        List<AbstractImage> listImage = new ArrayList<>();
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

        frame.setVisible(true);*/
    }
}




