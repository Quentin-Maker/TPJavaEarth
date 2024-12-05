public class Aeroport {
    // Attributs
    private String iata;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    // Constructeur par défaut
    public Aeroport() {
        this.iata = "";
        this.name = "";
        this.country = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    // Constructeur avec paramètres
    public Aeroport(String iata, String name, String country, double latitude, double longitude) {
        this.iata = iata;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter pour le code IATA
    public String getIATA() {
        return iata;
    }

    // Getter pour la latitude
    public double getLatitude() {
        return latitude;
    }

    // Getter pour la longitude
    public double getLongitude() {
        return longitude;
    }

    // Méthode toString pour représenter l'objet sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "Aeroport{" +
                "IATA='" + iata + '\'' +
                ", Name='" + name + '\'' +
                ", Country='" + country + '\'' +
                ", Latitude=" + latitude +
                ", Longitude=" + longitude +
                '}';
    }
}