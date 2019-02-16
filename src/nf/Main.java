package nf;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        //java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime()); //récupére un objet date et on recupere le temps en long
        //System.out.println(calendar.get(GregorianCalendar.MONTH)+1);
        try {
            Connexion connexion = new Connexion();
            connexion.connection("robert","joke");
            ArrayList <DMR> s;
            ArrayList <PersonnelServiceRadio> psr;

           /* PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
                    "57",
                    "Trouillet",
                    "Juliette",
                    Profession.PH
                    );
*/
            /*PersonnelServiceRadio personnelServiceRadio2 = new PersonnelServiceRadio(
                    "Robert",
                    "Amandine",
                    "robert",
                    Profession.PH
            );
            Patient patient1 = new Patient (
                    "3",
                    "Poitevin",
                    "Margaux",
                    new GregorianCalendar(1997, 8,11),
                    "08719859265"
            );*/

            //connexion.addPatient(patient1);
           // connexion.addPersonnelServiceRadio(personnelServiceRadio);
           //connexion.addPersonnelServiceRadio(personnelServiceRadio2);

            psr = connexion.getListePersonnel();
            s=connexion.getDMR();

            for (int i=0; i<s.size();i++){
                System.out.println(s.get(i).getPatient().getPrenom());
            }

            //System.out.println(psr);
            for(int i =0; i<psr.size();i++){
                System.out.println(psr.get(i).getNom());
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        /*PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
                "57",
                "Trouillet",
                "Juliette",
                Profession.PH
        );*/

       /* Patient patient1 = new Patient (
          "3",
                "Poitevin",
                "Margaux",
                new GregorianCalendar(1997, 8,11),
                "08719859265"
        );*/



        /*Patient p = new Patient();
        p.setIdPatient("2");
        Examen ex = new Examen(
                "32765026587469",
                new GregorianCalendar(2018, 2, 5),
                "879880983",
                TypeExamen.SCANNER,
                new PersonnelServiceRadio("Spinicci", "Kevin", "7274289", Profession.PH),
                ServiceHosp.NEUROLOGIE
        );

        Connexion c = new Connexion();
        try {
            c.Connection();
            c.addExamen(ex, p);
            c.Disconnection();
        } catch (Exception e){
            e.printStackTrace();
        }*/
    }

}

