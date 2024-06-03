package com.example.ds_simulator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;
import java.util.Objects;

public class DataStructureMenu {
    @FXML
    private AnchorPane dsMenu;

    @FXML
    private Label selectText;

    @FXML
    private Label infoText;

    @FXML
    private Button sortButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button stackButton;

    @FXML
    private Button queueButton;

    @FXML
    private Button backButton;

    @FXML
    private Button bstButton;

    @FXML
    private Button offButton;

    @FXML
    private void initialize() {
        String imageFilePath = Objects.requireNonNull(getClass().getResource("/images/bg.jpg")).toString();
        Image image = new Image(imageFilePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1080);
        imageView.setFitHeight(720);
        dsMenu.getChildren().add(imageView);
        imageView.toBack();


        String imgFilePath = Objects.requireNonNull(getClass().getResource("/images/backRed.png")).toString();
        Image img = new Image(imgFilePath);
        // Create a BackgroundImage
        BackgroundImage backgroundImage = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false)
        );

        // Set the BackgroundImage to the Button
        offButton.setBackground(new Background(backgroundImage));

        String imageFile = Objects.requireNonNull(getClass().getResource("/images/bullet01.png")).toString();
        Image image1 = new Image(imageFile);
        ImageView bullet01 = new ImageView(image1);
        bullet01.setFitWidth(50);
        bullet01.setFitHeight(50);
        bullet01.setY(193);
        bullet01.setX(150);
        dsMenu.getChildren().add(bullet01);

        String imageFile2 = Objects.requireNonNull(getClass().getResource("/images/bullet02.png")).toString();
        Image image2 = new Image(imageFile2);
        ImageView bullet02 = new ImageView(image2);
        bullet02.setFitWidth(50);
        bullet02.setFitHeight(50);
        bullet02.setY(263);
        bullet02.setX(150);
        dsMenu.getChildren().add(bullet02);

        String imageFile3 = Objects.requireNonNull(getClass().getResource("/images/bullet03.png")).toString();
        Image image3 = new Image(imageFile3);
        ImageView bullet03 = new ImageView(image3);
        bullet03.setFitWidth(50);
        bullet03.setFitHeight(50);
        bullet03.setY(333);
        bullet03.setX(150);
        dsMenu.getChildren().add(bullet03);

        String imageFile4 = Objects.requireNonNull(getClass().getResource("/images/bullet04.png")).toString();
        Image image4 = new Image(imageFile4);
        ImageView bullet04 = new ImageView(image4);
        bullet04.setFitWidth(50);
        bullet04.setFitHeight(50);
        bullet04.setY(403);
        bullet04.setX(150);
        dsMenu.getChildren().add(bullet04);

        String imageFile5 = Objects.requireNonNull(getClass().getResource("/images/button05.png")).toString();
        Image image5 = new Image(imageFile5);
        ImageView bullet05 = new ImageView(image5);
        bullet05.setFitWidth(50);
        bullet05.setFitHeight(50);
        bullet05.setY(473);
        bullet05.setX(150);
        dsMenu.getChildren().add(bullet05);




    }


    @FXML
    private void onSortButtonClicked(ActionEvent actionEvent) throws IOException {


        try {
            // Start the SearchSimulator
            SortVisualization v = new SortVisualization();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void onSearchButtonClicked(ActionEvent actionEvent) throws IOException {

        try {
            // Start the SearchSimulator
            SearchVisualization v = new SearchVisualization();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void onStackButtonClicked(ActionEvent actionEvent) throws IOException {

        try {
            StackVisualization stackVisualization = new StackVisualization();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void onQueueButtonClicked(ActionEvent actionEvent) throws IOException {

        try {
            QueueVisualization v = new QueueVisualization();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    @FXML
    private void onBSTButtonClicked(ActionEvent actionEvent) throws IOException {
        try {

            BSTVisualization v = new BSTVisualization();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onOffButtonClicked(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        // Close the current stage (window)
        stage.close();

    }

}