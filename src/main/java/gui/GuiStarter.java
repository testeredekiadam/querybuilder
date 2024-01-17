package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import java.util.Objects;


public class GuiStarter extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/MainScene.fxml")));

/*
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        Label greetings = new Label("Query Builder!");
        greetings.setTextFill(Color.GREEN);
        greetings.setFont(Font.font("Times New Roman", FontWeight.BOLD, 70));

        root.getChildren().add(greetings);
*/

        stage.setTitle("Query Builder");
        stage.setScene(new Scene(root, 700, 700));
        stage.show();
    }

}
