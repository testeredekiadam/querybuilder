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

import models.Query;
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
    private TextField attribute;

    @FXML
    private TextField selectedColumns;

    private ArrayList<Query> queryList = new ArrayList<>();

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
                    this.openFileButton.requestFocus();
                    Query query = new Query();
                    queryList.add(query);
                }
        );
    }

    @FXML
    public void onOpenFileClicked() throws NullPointerException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
            this.queryList.get(0).setQueryArray(Csv.CsvToString(selectedFile.getAbsolutePath()));
        }
    }

    @FXML
    public void displayQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        select(selectedColumns.getText());
        from(table.getText());
        searchInCsv(attribute.getText(), this.queryList.get(0).getQueryArray() );

        for(Query item : this.queryList){
            item.setSubBase(new StringBuilder());
            item.setFooter(new StringBuilder());
            stringBuilder.append(item.display());
        }
        this.query.setText(stringBuilder.toString());

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

    public void select(String columns){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        if(!columns.isEmpty()) {
            stringBuilder.append(columns);
        }
        else {
            stringBuilder.append("*");
        }
        stringBuilder.append("\n");
        this.queryList.get(0).setSelect(stringBuilder);
    }

    public void from(String table){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FROM ");
        if(!table.isEmpty()){
            stringBuilder.append(table);
        }
        else {
            stringBuilder.append(" 'enter table name' ");
        }
        stringBuilder.append("\n");
        this.queryList.get(0).setFrom(stringBuilder);

    }

    public void searchInCsv( String columnFilter, ArrayList<ArrayList<String>> queryArray){
        StringBuilder stringBuilder = new StringBuilder();
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

        this.queryList.get(0).setQueryArrayString(stringBuilder);
    }

    public void display(){
        displayQuery();
    }


}
