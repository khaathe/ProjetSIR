package nf;

public class Main {

    public static void main(String[] args) {
        /*File f = new File("D:\\ProjetSIR\\ProjetTIS4\\series-000001\\image-000050.dcm");
        String numArchivage = Examen.generateNumArchivage();
        String[] regrex = f.getName().split("\\.");
        String extension = regrex[regrex.length-1].toUpperCase();
        AbstractImage image=null;
        switch (extension){
            case "PGM" :
                image = new PGM(numArchivage);
                break;
            case "DCM" :
                image = new Dicom(numArchivage);
                break;
            default :
                image = new Image(numArchivage);
                break;
        }
        try {
            image.setImage(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        /*ImagePanel ip = null;
        try {
            BasicConfigurator.configure();
            SourceImage dimg = new SourceImage("C:\\Users\\amanr\\Desktop\\case1\\case1\\case1_008.dcm");
            BufferedImage img = dimg.getBufferedImage();
            ip = new ImagePanel(img);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DicomException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame();
        frame.setContentPane(ip);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);*/

        /*JLabel label = new JLabel(String.valueOf(image.getNumInstance()));
        JFrame frame = new JFrame();
        ImagePanel img = new ImagePanel(image.getImage());
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(img, BorderLayout.CENTER);
        frame.getContentPane().add(label, BorderLayout.SOUTH);
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bound = graphicsEnvironment.getMaximumWindowBounds();
        Dimension d = frame.getToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(bound.width, bound.height));
        //frame.setPreferredSize(d);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);*/
        /*Patient patient = new Patient(
                "",
                "565694949",
                "Heissler",
                "Claire",
                new GregorianCalendar(1997, 10, 7)
        );

        ClientHL7 clientHL7 = new ClientHL7();
        clientHL7.connexion("130.190.114.137", 6516);
        library.interfaces.Patient p = new library.interfaces.Patient(Integer.parseInt(patient.getIdPatient()), patient.getNom(), 'N');
        p.setFirstName(patient.getPrenom());
        p.setBirth(patient.getNaissance().getTime());
        clientHL7.admit(p);
        clientHL7.close();*/

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




