package nf;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        //GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        //java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime()); //récupére un objet date et on recupere le temps en long
        //System.out.println(calendar.get(GregorianCalendar.MONTH)+1);
        try {
            Connexion connexion = new Connexion();
            connexion.connection("kevin","1234abcd");
            ArrayList <DMR> s = connexion.getDMR();
            ArrayList <PersonnelServiceRadio> psr;

            PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
                    "8278375",
                    "Trouillet",
                    "Juliette",
                    Profession.MANIPULATEUR
            );
            Patient patient = new Patient (
                    "983250865",
                    "565694949",
                    "Heissler",
                    "Claire",
                    new GregorianCalendar(1997, 10,7)
                    );
            Patient patient3 = new Patient (
                    "649849949",
                    "264641646",
                    "Mottin",
                    "Laurence",
                    new GregorianCalendar(1968, 9,4)
            );

            Examen e=new Examen(
                    (GregorianCalendar) Calendar.getInstance(),
                    "864994949",
                    TypeExamen.IRM,
                    patient,
                    personnelServiceRadio,
                    ServiceHosp.CARDIOLOGIE,
                    new ArrayList<Image>(),
                    new CompteRendu("864994949"," du texte toi meme tu sais")
            );

           connexion.Disconnection();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

