package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.Query;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateDomainController implements Initializable {
    FileChooser fileChooser;
    public static Query query;
    @FXML
    public TextField replace, replaceFilter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
