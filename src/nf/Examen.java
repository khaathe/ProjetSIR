package nf;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Examen {
    private GregorianCalendar date;
    private String numArchivage;
    private CompteRendu cr;
    private TypeExamen typeExamen;
    private ArrayList<Image> images;
    private  Patient patient;
    private PersonnelServiceRadio praticien;
    private ServiceHosp service;

    public Examen () {
        date = new GregorianCalendar();
        numArchivage = "";
        patient = new Patient();
        praticien = new PersonnelServiceRadio();
        typeExamen = TypeExamen.UNKNOWN;
        service = ServiceHosp.UNKNOWN;
    }



    public Examen(GregorianCalendar date, String numArchivage, TypeExamen typeExamen, Patient patient, PersonnelServiceRadio praticien, ServiceHosp service, ArrayList<Image> images, CompteRendu cr){
        this.date = date;
        this.numArchivage = numArchivage;
        this.typeExamen = typeExamen;
        this.patient = patient;
        this.praticien = praticien;
        this.service = service;
        this.images = images;
        this.cr = cr;
    }

    public Examen(GregorianCalendar date, String numArchivage, TypeExamen typeExamen, Patient patient, PersonnelServiceRadio praticien, ServiceHosp service){
        this.date = date;
        this.numArchivage = numArchivage;
        this.typeExamen = typeExamen;
        this.patient = patient;
        this.praticien = praticien;
        this.service = service;
    }

    public void imprimerCR(){

    }

    public void imprimerImage(){

    }
    public void ajouterImage(Image image){
        if (!images.contains(image)){
            images.add(image);
        }
    }

    public static String generateNumArchivage () {
        double random = Math.random();
        random = random * Math.pow(10, 15);
        DecimalFormat df = new DecimalFormat("000000000000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(random);
    }

    public String toString (){
        String info = "Examen du : " + new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
        return info;
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

    public ArrayList<Image> getImages() {
        return images;
    }
}
