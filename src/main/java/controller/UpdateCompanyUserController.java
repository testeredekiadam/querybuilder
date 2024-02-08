package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Query;
import service.Csv;
import service.QueryServiceInterface;
import service.UpdateCompanyUserQueryServiceImpl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCompanyUserController implements Initializable {
    public Button openFileButton, updateButton;
    public TextField filePath, table, modifiedBy, comment4admin, updateItem, updatePredicate, attribute, filter, csvFilterAttribute;
    QueryServiceInterface updateQueryService;
    FileChooser fileChooser;

    public static Query query;
    private final String[] options = {"In", "Equal", "Greater than", "Less than", "Greater than or equal", "Less than or equal", "Not equal", "Between", "Like"};
    @FXML
    private ChoiceBox<String> filterChoiceBox;
    private String choice;
    private String queryType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        query = new Query();
        query.setQueryType(queryType);
        this.openFileButton.requestFocus();
        query.setWhere(false);
        filterChoiceBox.getItems().addAll(this.options);

    }

    public void onOpenFileClicked(ActionEvent actionEvent) {
        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
            query.setCsvArray(Csv.CsvToString((selectedFile.getAbsolutePath())));
        }
    }

    public void update() {
        setChoice();
        updateQueryService.selectComponent(query, table.getText());

        if (this.queryType.equals("delete")) {
            updateQueryService.fromComponent(query, updateQueryService.standardInfoComponents("delete", modifiedBy.getText(), comment4admin.getText()));
            updateQueryService.updateComponent(query, "", "");
        } else {
            updateQueryService.fromComponent(query, updateQueryService.standardInfoComponents("update", modifiedBy.getText(), comment4admin.getText()));
            updateQueryService.updateComponent(query, updateItem.getText(), updatePredicate.getText());
        }

        updateQueryService.whereComponent(query, choice, attribute.getText(), filter.getText());
        updateQueryService.searchInCsv(query, csvFilterAttribute.getText(), query.getCsvArray());

    }

    public void setQueryService(QueryServiceInterface updateQueryService){
        this.updateQueryService = updateQueryService;
    }

    public Query getQuery() {
        return query;
    }

    public void setChoice() {
        this.choice = filterChoiceBox.getValue();
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
