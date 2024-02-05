package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import models.SelectQuery;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchQueryController implements Initializable {

    @FXML
    private TabPane tabPane;
    public static int tabId=0;


    public static final ArrayList<SelectQuery> queryList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onAddNewTab();
        this.tabPane.getTabs().get(0).setClosable(false);
        this.tabPane.getTabs().get(1).setClosable(false);
        this.tabPane.getSelectionModel().select(1);
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

    public static void addQueryList(SelectQuery query){
        queryList.add(query);
    }

    public static SelectQuery getQueryListElement(int index){
        return queryList.get(index);
    }

    public static String getTabId() {
        return String.valueOf(tabId);
    }


}
