package com.example.ds_simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu {
    @FXML
    private Label menuTitle;
    @FXML
    private Button sortButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button stackButton;
    @FXML
    private AnchorPane menuBG;

    @FXML
    private void onSortButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("sort-menu.fxml"));
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene=new Scene(fxmlLoader.load(),600,400);
        stage.setScene(scene);
        stage.setTitle("Player 1");
        stage.show();
    }

}