public class Main {
    public static void main(String[] args) {
        // Chargement du fichier CSV
        World w = new World("src/Data/aeroports.csv");
       // System.out.println("Chemin absolu du fichier : " + w.getAbsolutePath());

        // Affichage du nombre d'aéroports chargés
        System.out.println("Found "+w.getList().size()+" airports.");

        // Recherche de l'aéroport le plus proche des coordonnées (2.316, 48.866) (Paris)
        Aeroport paris = w.findNearest(48.866, 2.316);

        System.out.println("Nearest airport : " + paris);

        // Recherche de l'aéroport par code IATA ("CDG")
        Aeroport cdg = w.findByCode("CDG");
        System.out.println("Aeroport with code CDG: " + cdg);

        double distance = w.distance(2.333, 48.866, paris);
        System.out.println(paris);
        System.out.println("Distance to Paris: " + distance + " km");

        double distanceCDG = w.distance(2.413, 48.893, cdg);
        System.out.println(cdg);
        System.out.println("Distance to CDG: " + distanceCDG + " km");
    }
}