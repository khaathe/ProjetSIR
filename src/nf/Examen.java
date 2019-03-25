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

    /**
     * Constructeur par défaut de la classe Examen. Ne prend pas de paramètres et initialise tous les
     *     attributs à des valeurs par défaut à l'exception de la liste d'images et du compte-rendu
     */
    public Examen() {
        date = new GregorianCalendar();
        numArchivage = "";
        patient = new Patient();
        praticien = new PersonnelServiceRadio();
        typeExamen = TypeExamen.UNKNOWN;
        service = ServiceHosp.UNKNOWN;
    }

    /**
     * Constructeur permettant d'initialiser tous les attributs à des valeurs définies dans les paramètres et d'instanciation la liste d'images
     */
    public Examen(GregorianCalendar date, String numArchivage, TypeExamen typeExamen, Patient patient, PersonnelServiceRadio praticien, ServiceHosp service, CompteRendu cr)
        {
            this.date = date;
            this.numArchivage = numArchivage;
            this.typeExamen = typeExamen;
            this.patient = patient;
            this.praticien = praticien;
            this.service = service;
            this.images = new ArrayList<>();
            this.cr = cr;
        }

    /**
     * Constructeur permettant d'initialiser les attributs à des valeurs définies, à l'exception de la liste d'images et du compte-rendu
     */
    public Examen(GregorianCalendar date, String numArchivage, TypeExamen typeExamen, Patient patient, PersonnelServiceRadio praticien, ServiceHosp service)
        {
            this.date = date;
            this.numArchivage = numArchivage;
            this.typeExamen = typeExamen;
            this.patient = patient;
            this.praticien = praticien;
            this.service = service;
        }

    /**
     * Méthode permettant d'ajouter une image à laliste d'image présente dans l'examen.
     * Vérifie que l'image à ajouter n'est pas déjà présente
     * dans la liste. Si elle y est, ne l'ajoute pas, sinon oui.
     * @param image
     * L'image à ajouter
     */
        public void ajouterImage (AbstractImage image){
                if (!images.contains(image)) {
                    images.add(image);
                }
            }

    /**
     *  méthode permettant de générer et renvoyer un numéro d'archivage aléatoire pour l'examen. Utilise la méthode random() pour le générer
     *  aléatoirement et restreint un format de type décimal limité à 15 chiffres, pour être conforme à la base de données
     * @return String
     * Le numéro d'archivage généré
     */
            public static String generateNumArchivage () {
                double random = Math.random();
                random = random * Math.pow(10, 15);
                DecimalFormat df = new DecimalFormat("000000000000000");
                df.setRoundingMode(RoundingMode.HALF_UP);
                return df.format(random);
            }


    /**
     * La méthode toString permet de retourner un affichage de la forme : "Examen du : 1997-05-19" en récupérant la date de l'attribut date.
     * Elle peut être directement appelée dans les interfaces pour faciliter l'affichage de la date de l'examen
     * @return String
     */
            public String toString () {
                return "Examen du : " + new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
            }

            public GregorianCalendar getDate () {
                return date;
            }

            public String getNumArchivage () {
                return numArchivage;
            }

            public CompteRendu getCr () {
                return cr;
            }

            public TypeExamen getTypeExamen () {
                return typeExamen;
            }

            public PersonnelServiceRadio getPraticien () {
                return praticien;
            }

            public ServiceHosp getService () {
                return service;
            }

            public Patient getPatient () {
                return patient;
            }

            public List<AbstractImage> getImages () {
                return images;
            }

    /**
     * Méthode permettant d'ajouter une image de type AbstractImage (prise en paramètre) dans la liste d'images.
     */
            public void addImage (AbstractImage image){
                images.add(image);
            }

            public void setImages (List < AbstractImage > images) {
                this.images = images;
            }


        }


