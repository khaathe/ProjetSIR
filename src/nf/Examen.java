package nf;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Examen {
    private String idExam;
    private GregorianCalendar date;
    private String numArchivage;
    private CompteRendu cr;
    private TypeExamen typeExamen;
    private List<Image> images;
    private PersonnelServiceRadio praticien;
    private ServiceHosp service;


    public Examen(String idExam, GregorianCalendar date, String numArchivage, TypeExamen typeExamen, PersonnelServiceRadio praticien, ServiceHosp service){
        this.setIdExam(idExam);
        this.setDate(date);
        this.setNumArchivage(numArchivage);
        images = new ArrayList<Image>();
        this.setCr(null);
        this.setTypeExamen(typeExamen);
        this.setPraticien(praticien);
        this.setService(service);


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

    public String getIdExam() {
        return idExam;
    }

    public void setIdExam(String idExam) {
        this.idExam = idExam;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getNumArchivage() {
        return numArchivage;
    }

    public void setNumArchivage(String numArchivage) {
        this.numArchivage = numArchivage;
    }

    public CompteRendu getCr() {
        return cr;
    }

    public void setCr(CompteRendu cr) {
        this.cr = cr;
    }

    public TypeExamen getTypeExamen() {
        return typeExamen;
    }

    public void setTypeExamen(TypeExamen typeExamen) {
        this.typeExamen = typeExamen;
    }

    public PersonnelServiceRadio getPraticien() {
        return praticien;
    }

    public void setPraticien(PersonnelServiceRadio praticien) {
        this.praticien = praticien;
    }

    public ServiceHosp getService() {
        return service;
    }

    public void setService(ServiceHosp service) {
        this.service = service;
    }

   /* public void ajouterCR(CompteRendu cr){
        Connexion.ajouterCR(cr);
    }
*/
}
