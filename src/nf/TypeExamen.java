package nf;

public enum TypeExamen {
    RADIOGRAPHIE(""),
    SCANNER("scanner"),
    IRM("irm"),
    ANGIOGRAPHIE(""),
    ECHOGRAPHIE(""),
    MAMMOGRAPHIE(""),
    ECHOENDOSCOPIE("");

    private String name;

    private TypeExamen (String name){
        this.name = name;
    }

    public String toString(){ return this.name;}
}