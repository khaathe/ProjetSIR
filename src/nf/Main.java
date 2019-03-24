package nf;

import java.util.GregorianCalendar;

public class Main {


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
        hl7.sendMessage(examen, HL7.ADMIT_PATIENT);
    }
}