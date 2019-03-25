package nf;


import library.interfaces.ClientHL7;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

   public static void main(String[] args) throws Exception {
       /* GregorianCalendar g= new GregorianCalendar();
        g.set(1900,03,05);
        c.connection("robertamandine","171197Ar");
       c.disconnection();*/
      GregorianCalendar g = new GregorianCalendar();
      g.set(1900, 03, 05);

      Patient p = new Patient("506", "81", "Dancelme", "Loic", g, "errgkvjbre");
      PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio("111588", "Nom", "Prenom", Profession.PH);
      CompteRendu compteRendu = new CompteRendu("123532", "kibigzukefrjvfejhvdzckjzev");
      List<AbstractImage> listImage = new ArrayList<>();
      Examen examen = new Examen(
              new GregorianCalendar(),
              "08470296",
              TypeExamen.SCANNER,
              p,
              personnelServiceRadio,
              ServiceHosp.UNKNOWN,
              // listImage,
              compteRendu
      );
      Examen examen2 = new Examen(
              new GregorianCalendar(),
              "120384261063482",
              TypeExamen.ANGIOGRAPHIE,
              p,
              personnelServiceRadio,
              ServiceHosp.UNKNOWN,
              new CompteRendu("120384261063482", "")
      );


       /*HL7 hl7=new HL7();
       hl7.sendMessage(examen, HL7.ADMIT_PATIENT);}}
      /*PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(

    public static void main(String[] args) {
       //cette methode ne sert qu'a des fin des tests
        HL7 hl7 = new HL7();
        Patient patient = new Patient(
                "803165326975215",
                "5234136",
                "ROFONE",
                "Mike",
                new GregorianCalendar(1986, 4, 18),
                "H"
        );

        PersonnelServiceRadio personnelServiceRadio = new PersonnelServiceRadio(
                "324626579536519",
                "ANGELO",
                "Michel",
                Profession.PH
        );
        Examen examen = new Examen(
                new GregorianCalendar(),
                "120384261063482",
                TypeExamen.ANGIOGRAPHIE,
                patient,
                personnelServiceRadio,
                ServiceHosp.UNKNOWN,
                new CompteRendu("120384261063482", "")
        );
        hl7.sendMessage(examen, HL7.ADMIT_PATIENT);*/
   }
}
