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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class MainMenu {
    @FXML
    private Label menuTitle;
    @FXML
    private Button sortButton;
    @FXML
    private Button offButton;
    @FXML
    private Button stackButton;
    @FXML
    private AnchorPane menuBG;

    @FXML
    private void initialize()
    {    String imageFilePath = Objects.requireNonNull(getClass().getResource("/images/bg4.jpg")).toString();
        Image image = new Image(imageFilePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1080);
        imageView.setFitHeight(720);
        menuBG.getChildren().add(imageView);
        imageView.toBack();

        // Load the image
//        Image img = new Image(getClass().getResource("/images/buttonBG.jpg").toExternalForm());



        String imgFilePath = Objects.requireNonNull(getClass().getResource("/images/file.png")).toString();
        Image img = new Image(imgFilePath);
        // Create a BackgroundImage
        BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false)
        );

        // Set the BackgroundImage to the Button
        sortButton.setBackground(new Background(backgroundImage));




        String imgFilePath1 = Objects.requireNonNull(getClass().getResource("/images/offRed.png")).toString();
        Image img1 = new Image(imgFilePath1);
        // Create a BackgroundImage
        BackgroundImage backgroundImage1 = new BackgroundImage(img1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false)
        );

        // Set the BackgroundImage to the Button
        offButton.setBackground(new Background(backgroundImage1));

    }

    @FXML
    private void onSortButtonClicked(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("data-structure-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        Stage stage = new Stage();
        stage.setTitle("Data Structure Menu");
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    public void onOffButtonClicked(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        // Close the current stage (window)
        stage.close();

    }

    @FXML
    private void onStackButtonClicked()
    {

        try {
            StackVisualization stackVisualization = new StackVisualization();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            // Start the SearchSimulator
//            QueueVisualization v = new QueueVisualization();
//        } catch (Exception e) {
//            e.printStackTrace();
        }

    }
