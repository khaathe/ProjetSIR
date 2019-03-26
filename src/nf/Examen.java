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
     * Constructeur par defaut de la classe Examen. Ne prend pas de parametres et initialise tous les
     *     attributs a des valeurs par defaut a l'exception de la liste d'images et du compte-rendu
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
     * Constructeur permettant d'initialiser tous les attributs a des valeurs definies dans les parametres et d'instanciation la liste d'images
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
     * Constructeur permettant d'initialiser les attributs a des valeurs definies, a l'exception de la liste d'images et du compte-rendu
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
     * Methode permettant d'ajouter une image a laliste d'image presente dans l'examen.
     * Verifie que l'image a ajouter n'est pas deja presente
     * dans la liste. Si elle y est, ne l'ajoute pas, sinon oui.
     * @param image
     * L'image a ajouter
     */
        public void ajouterImage (AbstractImage image){
                if (!images.contains(image)) {
                    images.add(image);
                }
            }

    /**
     *  methode permettant de generer et renvoyer un numero d'archivage aleatoire pour l'examen. Utilise la methode random() pour le generer
     *  aleatoirement et restreint un format de type decimal limite a 15 chiffres, pour etre conforme a la base de donnees
     * @return String
     * Le numero d'archivage genere
     */
            public static String generateNumArchivage () {
                double random = Math.random();
                random = random * Math.pow(10, 15);
                DecimalFormat df = new DecimalFormat("000000000000000");
                df.setRoundingMode(RoundingMode.HALF_UP);
                return df.format(random);
            }


    /**
     * La methode toString permet de retourner un affichage de la forme : "Examen du : 1997-05-19" en recuperant la date de l'attribut date.
     * Elle peut etre directement appelee dans les interfaces pour faciliter l'affichage de la date de l'examen
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
     * Methode permettant d'ajouter une image de type AbstractImage (prise en parametre) dans la liste d'images.
     */
            public void addImage (AbstractImage image){
                images.add(image);
            }

            public void setImages (List < AbstractImage > images) {
                this.images = images;
            }


        }


