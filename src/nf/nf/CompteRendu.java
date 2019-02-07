package nf;

public class CompteRendu {
    private PersonnelServiceRadio editeur;
    private String contenu;

    public CompteRendu(String nom, String prenom, Profession profession){
        this.contenu=null;
        this.editeur= new PersonnelServiceRadio(nom,prenom,profession);
    }

    public String ajouterContenu(){
        contenu+="contenu du compte-rendu";
        return contenu;
    }
}
