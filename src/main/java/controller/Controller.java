package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class Controller {

    @FXML
    TextField filePath;

    @FXML
    Button openFileButton;

    public void initialize(){
        Platform.runLater(() -> this.openFileButton.requestFocus());
    }

    public void onOpenFileClicked() throws NullPointerException{
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
        }

    }

}
