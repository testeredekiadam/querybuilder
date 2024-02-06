package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import service.QueryServiceInterface;
import service.SearchQueryServiceImpl;
import service.UpdateQueryServiceImpl;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public MenuItem searchQuery, updateCompanyUser, deleteCompanyUser, insertDomain, deleteDomain, updateMailDomains, importUserFromCSV;
    @FXML
    public Label chooseLabel;
    public VBox editorPane;
    @FXML
    private Button copyButton, displayQueryButton;
    @FXML
    private TextArea query;

    QueryServiceInterface queryService;

    private String queryChoice;


    @FXML
    public void displayQuery() {
        switch (getQueryChoice()){
            case "SearchQuery" -> queryService.displayComponent(this.query, SearchQueryController.queryList);
            case "UpdateQuery" -> queryService.displayComponent(this.query, UpdateCompanyUserController.query);
        }



    }

    public void onCopy(){
        String copyQuery = this.query.getText();

        if(copyQuery != null && !copyQuery.isEmpty()){
            ClipboardContent content = new ClipboardContent();
            content.putString(copyQuery);
            Clipboard.getSystemClipboard().setContent(content);
        }
    }

    public void display(){
        displayQuery();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onSearchQueryEditor(ActionEvent actionEvent) throws NullPointerException{
        setQueryChoice("SearchQuery");
        queryService = new SearchQueryServiceImpl();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SearchQuery.fxml"));
        SearchQueryController controller = new SearchQueryController();
        controller.setQueryService(queryService);
        loader.setController(controller);

        try {

            Parent content = loader.load();

            AnchorPane root = (AnchorPane) content;

            editorPane.getChildren().clear();

            editorPane.getChildren().add(0, content);

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void onUpdateCompanyUserEditor(ActionEvent actionEvent) {
        setQueryChoice("UpdateQuery");
        queryService = new UpdateQueryServiceImpl();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/UpdateCompanyUser.fxml"));
        UpdateCompanyUserController controller = new UpdateCompanyUserController();
        controller.setUpdateQueryService(queryService);
        loader.setController(controller);

        try {

            Parent content = loader.load();

            AnchorPane root = (AnchorPane) content;

            editorPane.getChildren().clear();

            editorPane.getChildren().add(0, content);

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getQueryChoice() {
        return queryChoice;
    }

    public void setQueryChoice(String queryChoice) {
        this.queryChoice = queryChoice;
    }
}
