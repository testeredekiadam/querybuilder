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
import java.util.ArrayList;

public class MainController {

    @FXML
    private Button copyButton;

    @FXML
    private Button openFileButton;

    @FXML
    private Button displayQueryButton;

    @FXML
    private TextField filePath;

    @FXML
    private TextArea query;

    @FXML
    private TextField table;

    @FXML
    private TextField attribute1;

    @FXML
    private TextField selectedColumns;

    private ArrayList<ArrayList<String>> queryArray = new ArrayList<>();

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
        }
    }

    @FXML
    public void displayQuery(String tableName, String attributeName, String columns) {

        StringBuilder queryString = new StringBuilder();
        select(queryString,columns);
        from(queryString, tableName);
        if(!this.queryArray.isEmpty()) {
            searchInCsv(queryString, attributeName, this.queryArray);
        }

        this.query.setText(queryString.toString());

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

    public void select(StringBuilder stringBuilder, String columns){
        stringBuilder.append("SELECT ");
        if(!columns.isEmpty()) {
            stringBuilder.append(columns);
        }
        else {
            stringBuilder.append("*");
        }
        stringBuilder.append("\n");
    }

    public void from(StringBuilder stringBuilder, String table){
        stringBuilder.append("FROM ");
        if(!table.isEmpty()){
            stringBuilder.append(table);
        }
        else {
            stringBuilder.append(" 'enter table name' ");
        }
        stringBuilder.append("\n");
    }

    public void searchInCsv(StringBuilder stringBuilder, String columnFilter, ArrayList<ArrayList<String>> queryArray){
        final int[] i = {0};
        stringBuilder.
        append("WHERE ")
                .append(columnFilter);

        queryArray.forEach(arrayItem -> {
            if(i[0] > 0){
                stringBuilder.append("AND ")
                        .append(columnFilter);
            }
            stringBuilder.append(" IN ")
                    .append('(')
                    .append(arrayItem.toString().trim(), 1, arrayItem.toString().trim().length() -1)
                    .append(")")
                    .append("\n");

            i[0]++;
        });
    }

    public void display(){
        displayQuery(this.table.getText(), this.attribute1.getText(),this.selectedColumns.getText());
    }


}
