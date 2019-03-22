package nf;

public  enum ServiceHosp {
    UNKNOWN("inconu", 80, "localhost"),
    CARDIOLOGIE("cardiologie", 6516, "localhost"),
    UROLOGIE("urologie", 6516, "localhost"),
    GENICO_OBSTETRIE("genico_obstetrie", 6516, "localhost"),
    DERMATOLOGIE("dermatologie", 6516, "localhost"),
    PSYCHIATRIE("psychiatrie", 6516, "localhost"),
    NEUROLOGIE("neurologie", 6516, "localhost"),
    PNEUMOLOGIE("pneumologie", 6516, "localhost");

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