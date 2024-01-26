package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Query;
import service.Csv;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditorController implements Initializable {
    @FXML
    private Button openFileButton, updateButton;
    @FXML
    private TextField filePath, table, attribute, selectedColumns, filter;
    @FXML
    private ChoiceBox<String> filterChoiceBox;

    private final String[] options = {"In", "Equal", "Greater than", "Less than", "Greater than or equal", "Less than or equal", "Not equal", "Between", "Like"};
    private String choice = "In";
    Query query;
    private String tabId;
    FileChooser fileChooser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.openFileButton.requestFocus();
        this.query = new Query();
        System.out.println("tabid in editor:" + this.tabId);
        filterChoiceBox.getItems().addAll(options);

        this.query.setId(this.tabId);
        this.query.setWhere(false);
        MainController.addQueryList(this.query);
    }

    @FXML
    public void onOpenFileClicked() throws NullPointerException {
        System.out.println("pen file clicked "+this.query.getId());

        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
            MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setQueryArray(Csv.CsvToString(selectedFile.getAbsolutePath()));
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
        MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setSelect(stringBuilder);
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
        MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setFrom(stringBuilder);

    }

    public void where(String column, String filter){
        StringBuilder stringBuilder;
        if(column.isEmpty() || filter.isEmpty()){
            stringBuilder = new StringBuilder();
        }
        else{
            stringBuilder = new StringBuilder();
            if(MainController.getQueryListElement(Integer.parseInt(this.query.getId())).isWhere()){
                stringBuilder.append(" AND ");
            }

            if(!MainController.getQueryListElement(Integer.parseInt(this.query.getId())).isWhere()){
                stringBuilder.append("WHERE ");
                MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setWhere(true);
            }
            stringBuilder.append(column);

            System.out.println("in where " + this.choice);

            switch (this.choice) {
                case "Equal" -> stringBuilder.append(" = ");
                case "Greater than" -> stringBuilder.append(" > ");
                case "Less than" -> stringBuilder.append(" < ");
                case "Greater than or equal" -> stringBuilder.append(" >= ");
                case "Less than or equal" -> stringBuilder.append(" <= ");
                case "Not equal" -> stringBuilder.append(" <> ");
                case "Between" -> stringBuilder.append(" BETWEEN ");
                case "Like" -> stringBuilder.append(" LIKE ");
                default -> stringBuilder.append(" IN ");
            }
            stringBuilder.append(filter);
        }
        MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setFilter(stringBuilder);
    }

    public void searchInCsv( String columnFilter, ArrayList<ArrayList<String>> queryArray){
        StringBuilder stringBuilder = new StringBuilder();
        final int[] i = {0};

        if(MainController.getQueryListElement(Integer.parseInt(this.query.getId())).isWhere()){
            stringBuilder.append("AND ");
        }

        if(!MainController.getQueryListElement(Integer.parseInt(this.query.getId())).isWhere()){
            stringBuilder.append("WHERE ");
            MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setWhere(true);
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

        MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setQueryArrayString(stringBuilder);
    }

    public void update(){
        getFilterChoice();
        System.out.println(this.choice);
        select(selectedColumns.getText());
        from(table.getText());
        where(attribute.getText(), filter.getText());



        searchInCsv(attribute.getText(), MainController.getQueryListElement(Integer.parseInt(this.query.getId())).getQueryArray() );
    }


    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public void getFilterChoice(){
        this.choice = filterChoiceBox.getValue();
    }


}
