package fr.financia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Version temporaire avec VBox simple
        VBox root = new VBox();
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getChildren().add(new Label("Bienvenue dans Financia !"));

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Financia - Accueil temporaire");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
