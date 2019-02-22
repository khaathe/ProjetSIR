package nf;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        //GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        //java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime()); //récupére un objet date et on recupere le temps en long
        //System.out.println(calendar.get(GregorianCalendar.MONTH)+1);
        try {
            Connexion connexion = new Connexion();
            connexion.connection("robert","joke");
            ArrayList <DMR> s = connexion.getDMR();
            ArrayList <PersonnelServiceRadio> psr;


           PersonnelServiceRadio personnelServiceRadio1 = new PersonnelServiceRadio(
                    "57",
                    "Trouillet",
                    "Juliette",
                    Profession.PH
                    );

           String idMed="93547369";
           connexion.addPersonnelServiceRadio(personnelServiceRadio1);

            PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
                    "93547369",
                    "Dupont",
                    "Marise",
                    Profession.SECRETAIRE_MEDICALE
            );
            Patient claire = new Patient (
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

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

