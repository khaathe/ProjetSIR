package nf;

public  enum ServiceHosp {
    UNKNOWN("inconu", 6517, "localhost"),
    CARDIOLOGIE("cardiologie", 6517, "localhost"),
    UROLOGIE("urologie", 6517, "localhost"),
    GENICO_OBSTETRIE("genico_obstetrie", 6517, "localhost"),
    DERMATOLOGIE("dermatologie", 6517, "localhost"),
    PSYCHIATRIE("psychiatrie", 6517, "localhost"),
    NEUROLOGIE("neurologie", 6517, "localhost"),
    PNEUMOLOGIE("pneumologie", 6517, "localhost");

    private String name;
    private int port;
    private String host;

    ServiceHosp(String name, int port, String host){
        this.name = name;
        this.port = port;
        this.host = host;
    }

    @Override
    public String toString (){ return this.name; }

    public int getPort() { return port; }

    public String getHost() { return  host; }
}