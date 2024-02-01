package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import models.Join;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinController implements Initializable {
    private String joinId;

    @FXML
    private TextField joinTable;
    @FXML
    private TextField joinPredicate;


    @FXML
    private Button discardButton;

    Join join;

    public void setJoinId(String id){
        this.joinId = id;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.join = new Join();
        this.joinTable.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setTableName(newValue);
        });
        this.joinPredicate.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setOnJoin(newValue);
        });
        EditorController.addJoinList(join);
        this.join.setId(this.joinId);

    }

    @FXML
    public void discardJoin(){
        HBox parentHBox = (HBox)this.discardButton.getParent();
        AnchorPane parentPane = (AnchorPane)parentHBox.getParent();
        FlowPane parentFlow = (FlowPane)parentPane.getParent();
        parentFlow.getChildren().remove(parentPane);
        EditorController.deleteJoinById(this.join.getId());
        //System.out.println("deleted: " + this.join.getId());

    }



}
