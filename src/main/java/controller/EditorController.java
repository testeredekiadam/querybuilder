package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Join;
import models.Query;
import service.Csv;
import service.QueryComponents;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditorController implements Initializable {
    @FXML
    private Button openFileButton, updateButton, joinButton;
    @FXML
    private TextField filePath, table, attribute, selectedColumns, filter;
    @FXML
    private ChoiceBox<String> filterChoiceBox;
    @FXML
    private FlowPane joinPane;

    private final String[] options = {"In", "Equal", "Greater than", "Less than", "Greater than or equal", "Less than or equal", "Not equal", "Between", "Like"};
    private String choice;
    Query query;
    private String tabId;
    FileChooser fileChooser;

    private static ArrayList<Join> joinList;

    private static int joinId = 0;

    public int getJoinId() {
        return joinId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.openFileButton.requestFocus();
        this.query = new Query();
        joinList = new ArrayList<>();
        filterChoiceBox.getItems().addAll(options);

        this.query.setId(this.tabId);
        this.query.setWhere(false);
        MainController.addQueryList(this.query);
    }

    @FXML
    public void onOpenFileClicked() throws NullPointerException {

        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null){
            this.filePath.setText(selectedFile.getAbsolutePath());
            MainController.getQueryListElement(Integer.parseInt(this.query.getId())).setCsvArray(Csv.CsvToString(selectedFile.getAbsolutePath()));
        }
    }

    public void update(){
        getFilterChoice();
        QueryComponents.selectComponent(this.query, selectedColumns.getText());
        QueryComponents.fromComponent(this.query, table.getText());
        QueryComponents.whereComponent(this.query, this.choice, attribute.getText(), filter.getText());
        QueryComponents.searchInCsv(this.query, attribute.getText(), MainController.getQueryListElement(Integer.parseInt(this.query.getId())).getCsvArray());
        QueryComponents.joinComponent(this.query, joinList);

    }


    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public void getFilterChoice(){
        this.choice = filterChoiceBox.getValue();
    }

    public void addJoin(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Join.fxml"));
        JoinController joinController = new JoinController();
        joinController.setJoinId(String.valueOf(joinId));
        loader.setController(joinController);
        try {
            Parent content = loader.load();
            this.joinPane.getChildren().add(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        joinId++;
    }

    public static void deleteJoinById(String id){
        joinList.removeIf(join -> join.getId().equals(id));
        joinId--;
    }

    public static void addJoinList(Join join){
        joinList.add(join);
    }

}
