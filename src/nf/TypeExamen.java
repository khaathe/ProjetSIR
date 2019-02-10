package nf;

public enum TypeExamen {
    RADIOGRAPHIE("radiographie"),
    SCANNER("scanner"),
    IRM("irm"),
    ANGIOGRAPHIE("angiographie"),
    ECHOGRAPHIE("echographie"),
    MAMMOGRAPHIE("mammographie"),
    ECHOENDOSCOPIE("echoendoscopie");

    private String name;

    private TypeExamen (String name){
        this.name = name;
    }

    public String toString(){ return this.name;}
}