package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import models.Query;
import service.Csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    @FXML
    private Button copyButton, displayQueryButton;

    @FXML
    private TextArea query;
    @FXML
    private TabPane tabPane;

    private static final ArrayList<Query> queryList = new ArrayList<>();

    @FXML
    public void displayQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        if(query != null){

            for (Query item : queryList) {
                item.setSubBase(new StringBuilder());
                item.setFooter(new StringBuilder());
                stringBuilder.append(item.display());
                item.setWhere(false);
            }
            this.query.setText(stringBuilder.toString());
        }

    }

    public void onCopy(){
        String copyQuery = this.query.getText();

        if(copyQuery != null && !copyQuery.isEmpty()){
            ClipboardContent content = new ClipboardContent();
            content.putString(copyQuery);
            Clipboard.getSystemClipboard().setContent(content);
        }
        System.out.println("Copy Clicked");
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
        Tab newTab= new Tab("Tab "+(queryList.size()+1));
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Editor.fxml"));
            Parent content = loader.load();
            newTab.setContent(content);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        tabPane.getTabs().add(newTab);
    }


}
