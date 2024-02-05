package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


import service.QueryComponents;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public MenuItem searchQuery, updateCompanyUser, deleteCompanyUser, insertDomain, deleteDomain, updateMailDomains, importUserFromCSV;
    @FXML
    private Button copyButton, displayQueryButton;
    @FXML
    private TextArea query;

    private String queryChoice;


    @FXML
    public void displayQuery() {

        QueryComponents.displayComponent(this.query, SearchQueryController.queryList);

    }

    public void onCopy(){
        String copyQuery = this.query.getText();

        if(copyQuery != null && !copyQuery.isEmpty()){
            ClipboardContent content = new ClipboardContent();
            content.putString(copyQuery);
            Clipboard.getSystemClipboard().setContent(content);
        }
        //System.out.println("Copy Clicked");
    }

    public void display(){
        displayQuery();
    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onChooseEditor(ActionEvent actionEvent) {

        MenuItem item = (MenuItem) actionEvent.getTarget();

        switch (item.getId()){
            default -> System.out.println(item.getId());
        }


    }

    public void querySelect(){}

}
