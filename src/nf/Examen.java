package nf;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Examen {
    private GregorianCalendar date;
    private String numArchivage;
    private CompteRendu cr;
    private TypeExamen typeExamen;
    private List<AbstractImage> images;
    private Patient patient;
    private PersonnelServiceRadio praticien;
    private ServiceHosp service;

    public Examen() {
        date = new GregorianCalendar();
        numArchivage = "";
        patient = new Patient();
        praticien = new PersonnelServiceRadio();
        typeExamen = TypeExamen.UNKNOWN;
        service = ServiceHosp.UNKNOWN;
    }

    public Examen(GregorianCalendar date, String numArchivage, TypeExamen typeExamen, Patient patient, PersonnelServiceRadio praticien, ServiceHosp service, CompteRendu cr) {
        this.date = date;
        this.numArchivage = numArchivage;
        this.typeExamen = typeExamen;
        this.patient = patient;
        this.praticien = praticien;
        this.service = service;
        this.images = new ArrayList<>();
        this.cr = cr;
    }

    public void addImage (AbstractImage image) {
        if (!images.contains(image)) {
            images.add(image);
        }
    }

    public static String generateNumArchivage() {
        double random = Math.random();
        random = random * Math.pow(10, 15);
        DecimalFormat df = new DecimalFormat("000000000000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(random);
    }

    public String toString() {
        return "Examen du : " + new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getNumArchivage() {
        return numArchivage;
    }

    public CompteRendu getCr() {
        return cr;
    }

    public TypeExamen getTypeExamen() {
        return typeExamen;
    }

    public PersonnelServiceRadio getPraticien() {
        return praticien;
    }

    public ServiceHosp getService() {
        return service;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<AbstractImage> getImages() {
        return images;
    }

    public void setImages (List<AbstractImage> images) { this.images = images; }

}
