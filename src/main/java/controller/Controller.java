package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import service.Csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    Button updateFiltersButton;

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
        Platform.runLater(() -> {
            this.openFileButton.requestFocus();
            }
        );
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
            displayQuery(this.table1.getText(), this.attribute1.getText());
        }
    }

    @FXML
    public void displayQuery(String tableName, String attributeName) {
        final int[] i = {0};

        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT * \n")
                .append("FROM ")
                .append(tableName).append("\n")
                .append("WHERE ")
                .append(attributeName);

        this.queryArray.forEach(arrayItem -> {
            if(i[0] > 0){
                queryString.append(" AND ")
                        .append(attributeName);
            }
            queryString.append(" IN ")
                    .append('(')
                    .append(arrayItem.toString().trim(), 1, arrayItem.toString().trim().length() -1)
                    .append(")")
                    .append("\n");

            i[0]++;
        });

        this.query.setText(queryString.toString());
    }

    public void onUpdateFilters() {
        displayQuery(this.table1.getText(), this.attribute1.getText());
    }
}
