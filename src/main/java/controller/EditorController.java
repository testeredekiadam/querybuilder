package controller;

import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Query;
import service.Csv;

import java.io.File;
import java.util.ArrayList;

public class EditorController {
    @FXML
    private Button openFileButton, updateButton;

    @FXML
    private TextField filePath, table, attribute, selectedColumns, filterCol, filter;


    @FXML
    public void initialize(){
        Platform.runLater(() -> {
                    this.openFileButton.requestFocus();
                    Query query = new Query();
                    query.setWhere(false);
                    MainController.addQueryList(query);
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
            MainController.getQueryListElement(0).setQueryArray(Csv.CsvToString(selectedFile.getAbsolutePath()));
        }
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
        MainController.getQueryListElement(0).setSelect(stringBuilder);
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
        MainController.getQueryListElement(0).setFrom(stringBuilder);

    }

    public void where(String column, String filter){
        StringBuilder stringBuilder;
        if(column.isEmpty() || filter.isEmpty()){
            stringBuilder = new StringBuilder();
        }
        else{
            stringBuilder = new StringBuilder();
            if(MainController.getQueryListElement(0).isWhere()){
                stringBuilder.append(" AND ");
            }

            if(!MainController.getQueryListElement(0).isWhere()){
                stringBuilder.append("WHERE ");
                MainController.getQueryListElement(0).setWhere(true);
            }
            stringBuilder.append(column).append(" IN ").append(filter);
        }
        MainController.getQueryListElement(0).setFilter(stringBuilder);
    }

    public void searchInCsv( String columnFilter, ArrayList<ArrayList<String>> queryArray){
        StringBuilder stringBuilder = new StringBuilder();
        final int[] i = {0};

        if(MainController.getQueryListElement(0).isWhere()){
            stringBuilder.append("AND ");
        }

        if(!MainController.getQueryListElement(0).isWhere()){
            stringBuilder.append("WHERE ");
            MainController.getQueryListElement(0).setWhere(true);
        }


        stringBuilder
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

        MainController.getQueryListElement(0).setQueryArrayString(stringBuilder);
    }

    public void update(){
        select(selectedColumns.getText());
        from(table.getText());
        where(filterCol.getText(), filter.getText());
        searchInCsv(attribute.getText(), MainController.getQueryListElement(0).getQueryArray() );
    }

}
