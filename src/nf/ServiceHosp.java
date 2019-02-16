package nf;

public  enum ServiceHosp {
    UNKNOWN("inconu"),
    CARDIOLOGIE("cardiologie"),
    UROLOGIE("urologie"),
    GENICO_OBSTETRIE("genico_obstetrie"),
    DERMATOLOGIE("dermatologie"),
    PSYCHIATRIE("psychiatrie"),
    NEUROLOGIE("neurologie"),
    PNEUMOLOGIE("pneumologie");

    private String name;

    private ServiceHosp (String name){
        this.name = name;
    }

    public String toString (){ return this.name; }
}