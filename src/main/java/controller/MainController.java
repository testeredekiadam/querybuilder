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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.QueryServiceInterface;
import service.SearchQueryServiceImpl;


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

        queryService.displayComponent(this.query, SearchQueryController.queryList);

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
        queryService = null;
    }
}
