package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import models.Query;
import service.QueryComponents;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button copyButton, displayQueryButton;
    @FXML
    private TextArea query;
    @FXML
    private TabPane tabPane;

    private static final ArrayList<Query> queryList = new ArrayList<>();
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

    public static void addQueryList(Query query){
        queryList.add(query);
    }

    public static Query getQueryListElement(int index){
        return queryList.get(index);
    }

    public void onAddNewTab(){
        Tab newTab = new Tab("Query Tab");
        newTab.setId(String.valueOf(tabId));

        //System.out.println("saved: " + tabId);

        System.out.println(newTab.getId());
        newTab.setOnClosed((Event t) -> {
            removeByTabId(newTab.getId());
            //System.out.println(queryList.size());
            //System.out.println("deleted: " + tabId);
            tabId--;
            //System.out.println("New total: " + tabId);
            }
        );

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Editor.fxml"));
        EditorController editorController = new EditorController();
        editorController.setTabId(String.valueOf(tabId));
        loader.setController(editorController);
        try {

            Parent content = loader.load();
            newTab.setContent(content);
            //System.out.println(newTab.getText());

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
        this.tabPane.getSelectionModel().select(1);
    }
}
