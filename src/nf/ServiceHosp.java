package nf;

public  enum ServiceHosp {
    CARDIOLOGIE(""),
    UROLOGIE("urologie"),
    GENICO_OBSTETRIE(""),
    DERMATOLOGIE(""),
    PSYCHIATRIE(""),
    NEUROLOGIE("neurologie"),
    PNEUMOLOGIE("");

    private String name;

    private ServiceHosp (String name){
        this.name = name;
    }

    public String toString (){ return this.name; }
}