package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import service.Csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    Button updateFiltersButton;

    @FXML
    Button copyButton;

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

    @FXML
    TextField selectedColumns;

    ArrayList<ArrayList<String>> queryArray = new ArrayList<>();

    @FXML
    public void initialize(){
        Platform.runLater(() -> this.openFileButton.requestFocus()
        );
    }

    @FXML
    public void onOpenFileClicked() throws NullPointerException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
            Csv a = new Csv(selectedFile.getAbsolutePath());
            a.CsvToString();
            this.queryArray = a.getQueryArray();
            displayQuery(this.table1.getText(), this.attribute1.getText(),this.selectedColumns.getText());
        }
    }

    @FXML
    public void displayQuery(String tableName, String attributeName, String columns) {
        final int[] i = {0};

        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT ")
                .append(columns)
                .append("\n")
                .append("FROM ")
                .append(tableName).append("\n")
                .append("WHERE ")
                .append(attributeName);

        this.queryArray.forEach(arrayItem -> {
            if(i[0] > 0){
                queryString.append("AND ")
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
        displayQuery(this.table1.getText(), this.attribute1.getText(),this.selectedColumns.getText());
    }

    public void onCopy(){
        String copyQuery = this.query.getText();

        if(copyQuery != null && !copyQuery.isEmpty()){
            ClipboardContent content = new ClipboardContent();
            content.putString(copyQuery);
            Clipboard.getSystemClipboard().setContent(content);
        }
        System.out.println("Copy Clicked");
    }
}
