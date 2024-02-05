package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import models.SelectQuery;
import service.QueryComponents;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public MenuItem searchQuery, updateCompanyUser, deleteCompanyUser, insertDomain, deleteDomain, updateMailDomains, importUserFromCSV;
    @FXML
    private Button copyButton, displayQueryButton;
    @FXML
    private TextArea query;
    @FXML
    private TabPane tabPane;
    private String queryChoice;

    private static final ArrayList<SelectQuery> queryList = new ArrayList<>();
    public static int tabId=0;

    public static String getTabId() {
        return String.valueOf(tabId);
    }


    @FXML
    public void displayQuery() {

        QueryComponents.displayComponent(this.query, queryList);

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

    public static void addQueryList(SelectQuery query){
        queryList.add(query);
    }

    public static SelectQuery getQueryListElement(int index){
        return queryList.get(index);
    }

    public void onAddNewTab(){
        Tab newTab = new Tab("Query Tab");
        newTab.setId(String.valueOf(tabId));

        System.out.println(newTab.getId());
        newTab.setOnClosed((Event t) -> {
            removeByTabId(newTab.getId());
            tabId--;
            }
        );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SearchQueryItem.fxml"));
        EditorController editorController = new EditorController();
        editorController.setTabId(String.valueOf(tabId));
        loader.setController(editorController);
        try {

            Parent content = loader.load();
            newTab.setContent(content);

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        tabPane.getTabs().add(newTab);
        tabId++;
        this.tabPane.getSelectionModel().select(newTab);

    }

    private void removeByTabId(String tabId){
        queryList.removeIf(query -> query.getId().equals(tabId));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onAddNewTab();
        this.tabPane.getTabs().get(0).setClosable(false);
        this.tabPane.getTabs().get(1).setClosable(false);
        this.tabPane.getSelectionModel().select(1);
    }

    public void onChooseEditor(ActionEvent actionEvent) {

        MenuItem item = (MenuItem) actionEvent.getTarget();

        switch (item.getId()){
            default -> System.out.println(item.getId());
        }


    }

    public void querySelect(){}

}
