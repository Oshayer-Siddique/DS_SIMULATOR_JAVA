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

public class SortMenu {

    @FXML
    private Button bubbleSortButton;
    @FXML
    private Button selectionSortButton;
    @FXML
    private Button insertionSortButton;
    @FXML
    private Button radixSortButton;
    @FXML
    private AnchorPane sortPane;
    @FXML
    private Label sortMenuText;

    @FXML
    private void initialize()
    {    String imageFilePath = Objects.requireNonNull(getClass().getResource("/images/sortmenu.jpg")).toString();
        Image image = new Image(imageFilePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(600);
        sortPane.getChildren().add(imageView);
        imageView.toBack();
    }

    public Boolean bubble = false;
    public Boolean selection = false;
    public Boolean insertion = false;
    public Boolean radix = false;

    @FXML
    void onBubbleSortClicked(ActionEvent event) {
        openSortSimulator(event, "Bubble");
    }

    @FXML
    void onSelectionSortClicked(ActionEvent event) {
        openSortSimulator(event, "Selection");
    }

    @FXML
    void onInsertionSortClicked(ActionEvent event) {
        openSortSimulator(event, "Insertion");
    }

    @FXML
    void onRadixSortClicked(ActionEvent event) {
        openSortSimulator(event, "Radix");
    }

    void openSortSimulator(ActionEvent event, String sortType) {
        // Update sorting flags based on sortType
        bubble = sortType.equals("Bubble");
        selection = sortType.equals("Selection");
        insertion = sortType.equals("Insertion");
        radix = sortType.equals("Radix");

        try {
            // Load the sort-simulator.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sort-simulator.fxml"));
            Scene newScene = new Scene(loader.load());

            // Get the current stage
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the current stage
            currentStage.setScene(newScene);

            // Optionally, set a new title for the window (if you want to change it)
            currentStage.setTitle("Sort Simulator");

            // Show the new scene
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading sort-simulator.fxml.");
        }
    }
}
