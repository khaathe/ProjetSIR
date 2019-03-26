package nf;



import library.interfaces.ClientHL7;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;


public class Main {




   public static void main(String[] args) throws Exception {

       Connexion c= new Connexion();
       c.connection("rupy","aze");

       PersonnelServiceRadio p= new PersonnelServiceRadio("3","Spinicci","Kevin",Profession.SECRETAIRE);


   }
}
