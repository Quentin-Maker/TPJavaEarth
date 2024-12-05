import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.Scene;

import java.util.List;

public class Earth extends Group {
    private final Sphere sph; // Représentation sphérique de la Terre
    private final double earthRadius = 300; // Rayon de la sphère représentant la Terre
    private final PerspectiveCamera camera; // Caméra pour la visualisation 3D
    private final Rotate ry; // Rotation autour de l'axe Y
    private final List<Aeroport> airports; // Liste des aéroports

    public Earth(List<Aeroport> airports, Scene ihm) {
        // Création de la sphère
        sph = new Sphere(earthRadius);

        // Ajout de la texture avec PhongMaterial
        PhongMaterial material = new PhongMaterial();
        Image earthTexture = new Image("file:/Users/quentinhebert/IdeaProjects/TPJavaQH/src/earth_lights_4800.png"); // Remplacez par le chemin de votre image
        material.setDiffuseMap(earthTexture); // Mapper l'image comme texture
        sph.setMaterial(material); // Associer le matériau à la sphère

        // Rotation autour de l'axe Y
        ry = new Rotate(0, Rotate.Y_AXIS);
        sph.getTransforms().add(ry); // Ajouter la transformation de rotation à la sphère

        // Ajout de la sphère au groupe
        this.getChildren().add(sph);

        // Configuration de la caméra
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000); // Position initiale
        camera.setNearClip(0.1); // Distance minimale pour le rendu
        camera.setFarClip(2000.0); // Distance maximale pour le rendu
        camera.setFieldOfView(35); // Champ de vision
        ihm.setCamera(camera); // Associer la caméra à la scène

        this.airports = airports; // Passer la liste des aéroports
    }

    public List<Aeroport> getAirports() {
        return airports; // Retourner la liste des aéroports
    }

    public Rotate getRy() {
        return ry; // Permet d'accéder à la rotation depuis l'extérieur
    }

    public PerspectiveCamera getCamera() {
        return camera; // Permet d'accéder à la caméra depuis l'extérieur
    }

    public double getRadius() {
        return earthRadius; // Permet d'obtenir le rayon de la sphère
    }
}
