import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Interface extends Application {

    private World world;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Earth Visualization");

        // Création de la scène avec un objet Earth comme groupe racine
        Pane pane = new Pane();
        Scene theScene = new Scene(pane, 800, 600, true); // Vue avec perspective
        theScene.setFill(Color.BLACK);
        world = new World("src/Data/aeroports.csv");
        Earth earth = new Earth(world.getList(), theScene);
        pane.getChildren().add(earth);

        // AnimationTimer pour mettre en rotation la Terre
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double currentAngle = earth.getRy().getAngle();
                earth.getRy().setAngle(currentAngle + 0.05);
            }
        };
        animationTimer.start();
        theScene.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    Point2D texCoord = pickResult.getIntersectedTexCoord();
                    if (texCoord != null) {

                        double x = texCoord.getX();
                        double y = texCoord.getY();


                        double latitude = 100 * (0.5 - y);
                        double longitude = 360 * (x - 0.5);


                        System.out.printf("Latitude: %.2f, Longitude: %.2f%n", latitude, longitude);

                        Aeroport nearestAeroport = findNearestAeroport(latitude, longitude, earth.getAirports());
                        if (nearestAeroport != null) {
                            System.out.println("Aéroport le plus proche : " + nearestAeroport);
                        }
                    } else {
                        System.out.println("Probleme texture.");
                    }
                }
            }
        });


        primaryStage.setScene(theScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Aeroport findNearestAeroport(double latitude, double longitude, List<Aeroport> airports) {
        Aeroport nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Aeroport aeroport : airports) {
            double distance = haversine(latitude, longitude, aeroport.getLatitude(), aeroport.getLongitude());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = aeroport;
            }
        }
        return nearest;
    }

    // Formule de la distance haversine
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // Rayon de la Terre en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}