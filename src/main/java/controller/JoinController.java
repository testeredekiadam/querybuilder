package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private TextField joinTable, joinPredicateLeft, joinPredicateRight;
    @FXML
    private Button discardButton;
    @FXML
    private ChoiceBox<String> joinChoiceBox;

    private final String[] options = {"INNER JOIN", "FULL JOIN", "LEFT JOIN", "RIGHT JOIN", "OUTER JOIN"};

    Join join;

    public void setJoinId(String id){
        this.joinId = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.join = new Join();
        joinChoiceBox.getItems().addAll(options);

        this.joinTable.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setTableName(newValue);
        });

        this.joinPredicateLeft.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setPredicateLeft(newValue);
        });

        this.joinPredicateRight.textProperty().addListener((observable, oldValue, newValue) -> {
            this.join.setPredicateRight(newValue);
        });

        this.join.setPredicateRight(this.joinChoiceBox.getValue());

        SearchQueryItemController.addJoinList(join);
        this.join.setId(this.joinId);
    }

    @FXML
    public void discardJoin(){
        HBox parentHBox = (HBox)this.discardButton.getParent();
        AnchorPane parentPane = (AnchorPane)parentHBox.getParent();
        FlowPane parentFlow = (FlowPane)parentPane.getParent();
        parentFlow.getChildren().remove(parentPane);
        SearchQueryItemController.deleteJoinById(this.join.getId());
    }



}
