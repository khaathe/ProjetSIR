package nf;

public enum TypeExamen {
    UNKNOWN("inconu"),
    RADIOGRAPHIE("radiographie"),
    SCANNER("scanner"),
    IRM("irm"),
    ANGIOGRAPHIE("angiographie"),
    ECHOGRAPHIE("echographie"),
    MAMMOGRAPHIE("mammographie"),
    ECHOENDOSCOPIE("echoendoscopie");

    private String name;

    TypeExamen(String name){
        this.name = name;
    }

    @Override
    public String toString(){ return this.name;}
}