package com.example.ds_simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
    private void initialize()
    {    String imageFilePath = Objects.requireNonNull(getClass().getResource("/images/aaa.jpg")).toString();
        Image image = new Image(imageFilePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);
        menuBG.getChildren().add(imageView);
        imageView.toBack();
    }

    @FXML
    private void onSortButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("sort-menu.fxml"));
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene=new Scene(fxmlLoader.load(),600,400);
        stage.setScene(scene);
        stage.setTitle("Sort");
        stage.show();
    }

}