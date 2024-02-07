package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Query;
import service.Csv;
import service.QueryServiceInterface;
import service.UpdateQueryServiceImpl;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCompanyUserController implements Initializable {
    public Button openFileButton, updateButton;
    public TextField filePath, table, modifiedBy, comment4admin, updateItem, updatePredicate, attribute, filter;
    private ChoiceBox<String> filterChoiceBox;
    QueryServiceInterface updateQueryService = new UpdateQueryServiceImpl();
    FileChooser fileChooser;
    public static Query query;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        query = new Query();
        this.openFileButton.requestFocus();
        query.setWhere(false);

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

    public void update(ActionEvent actionEvent) {
        updateQueryService.selectComponent(query, table.getText());
        updateQueryService.fromComponent(query, updateQueryService.standardInfoComponents(modifiedBy.getText(), comment4admin.getText()));
        updateQueryService.updateComponent(query, updateItem.getText(), updatePredicate.getText());

    }

    public void setUpdateQueryService(QueryServiceInterface updateQueryService){
        this.updateQueryService = updateQueryService;
    }

    public Query getQuery() {
        return query;
    }
}
