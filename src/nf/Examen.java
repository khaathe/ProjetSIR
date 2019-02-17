package nf;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void setNumArchivage(String numArchivage) {
        this.numArchivage = numArchivage;
    }

    public void setCr(CompteRendu cr) {
        this.cr = cr;
    }

    public void setTypeExamen(TypeExamen typeExamen) {
        this.typeExamen = typeExamen;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setPraticien(PersonnelServiceRadio praticien) {
        this.praticien = praticien;
    }

    public void setService(ServiceHosp service) {
        this.service = service;
    }

    public Patient getPatient() {
        return patient;
    }

    public ArrayList<Image> getImages() {
        return images;
    }
}
