package nf;

import ui.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        //java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime()); //récupére un objet date et on recupere le temps en long
        //System.out.println(calendar.get(GregorianCalendar.MONTH)+1);
        try {
            Connexion connexion = new Connexion();

            connexion.connection("3","123");
            ArrayList <DMR> s = connexion.getDMR();
            ArrayList <PersonnelServiceRadio> psr;



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

            Image i = new Image("08470296");
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




            ArrayList<Examen> lesExamsDeClaire = connexion.getExamen(claire);
            System.out.println(lesExamsDeClaire.size());
            for(DMR dmr : s){
                Patient p = dmr.getPatient();
                ArrayList<Examen> listeExamen = connexion.getExamen(p);
                if (listeExamen.size() > 0)
                    System.out.println(p.getNom() + " a eu un exam le : "+ listeExamen.get(0));
            }
            connexion.Disconnection();
*/
        } catch (Exception e){
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        try {
            ImagePanel img = new ImagePanel(ImageIO.read(new File("D:\\image\\cecd818ee636be1e9e531bef6f255b55.jpg")));
            frame.setContentPane(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}

