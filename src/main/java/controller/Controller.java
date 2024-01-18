package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.Csv;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    TextField filePath;

    @FXML
    TextArea query;

    @FXML
    Button openFileButton;

    @FXML
    TextField table1;

    @FXML
    TextField attribute1;

    ArrayList<ArrayList<String>> queryArray = new ArrayList<>();

    @FXML
    public void initialize(){
        Platform.runLater(() -> this.openFileButton.requestFocus());
    }

    @FXML
    public void onOpenFileClicked() throws NullPointerException, IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
            Csv a = new Csv(selectedFile.getAbsolutePath());
            a.CsvToString();
            this.queryArray = a.getQueryArray();
            displayQuery();
            System.out.println("\n\n\nFirat\n\n\n");
            System.out.println(this.queryArray);
        }
    }

    @FXML
    public void displayQuery() {
        final int[] i = {0};

        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT * \n");
        queryString.append("FROM \n");
        queryString.append("WHERE  \n");
        this.queryArray.forEach(arrayItem -> {
            if(i[0] > 0){
                queryString.append(" AND");
            }
            queryString.append(" IN ");
            queryString.append('(');
            queryString.append(arrayItem.toString().trim(), 1, arrayItem.toString().trim().length() -1);
            queryString.append(")");
            queryString.append("\n");

            i[0]++;
        });

        this.query.setText(queryString.toString());
    }

}
