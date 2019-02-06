package nf;

public class CompteRendu {
    private PersonnelServiceRadio editeur;
    private String contenu;

    public CompteRendu(String nom, String prenom, String idMedical){
        this.contenu=null;
        this.editeur= new PersonnelServiceRadio(nom,prenom,idMedical);
    }

    public String ajouterContenu(){
        contenu+="contenu du compte-rendu";
        return contenu;
    }
}
