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
import service.*;


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
            case "UpdateCompanyUserQuery", "DeleteCompanyUserQuery" -> queryService.displayComponent(this.query, UpdateCompanyUserController.query);
            case "InsertDomainQuery", "DeleteDomainQuery" -> queryService.displayComponent(this.query, InsertDeleteController.query);
            case "UpdateDomainQuery" -> queryService.displayComponent(this.query, UpdateDomainController.query);
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
        setQueryChoice("UpdateCompanyUserQuery");
        queryService = new UpdateCompanyUserQueryServiceImpl();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/UpdateCompanyUser.fxml"));
        UpdateCompanyUserController controller = new UpdateCompanyUserController();
        controller.setQueryType("update");
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

    public void onDeleteCompanyUserEditor(ActionEvent actionEvent) {
        setQueryChoice("DeleteCompanyUserQuery");
        queryService = new UpdateCompanyUserQueryServiceImpl();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DeleteCompanyUser.fxml"));
        UpdateCompanyUserController controller = new UpdateCompanyUserController();
        controller.setQueryType("deleteCompany");
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

    public void onInsertDomainEditor(ActionEvent actionEvent) {
        setQueryChoice("InsertDomainQuery");
        queryService = new InsertDeleteService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InsertDelete.fxml"));
        InsertDeleteController controller = new InsertDeleteController();
        controller.setQueryType("insertDomain");
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

    public void onDeleteDomainEditor(ActionEvent actionEvent) {
        setQueryChoice("DeleteDomainQuery");
        queryService = new InsertDeleteService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/InsertDelete.fxml"));
        InsertDeleteController controller = new InsertDeleteController();
        controller.setQueryType("deleteDomain");
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

    public void onUpdateDomainEditor(ActionEvent actionEvent) {
        setQueryChoice("UpdateDomainQuery");
        queryService = new UpdateDomainServiceImpl();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/UpdateDomain.fxml"));
        UpdateDomainController controller = new UpdateDomainController();
        controller.setQueryType("deleteDomain");
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

    public String getQueryChoice() {
        return queryChoice;
    }

    public void setQueryChoice(String queryChoice) {
        this.queryChoice = queryChoice;
    }
}
