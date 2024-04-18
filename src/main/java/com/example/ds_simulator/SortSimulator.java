package com.example.ds_simulator;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SortSimulator {

    @FXML
    private TextField inputPlace;
    @FXML
    private Button nextNumberButton;
    @FXML
    private Button startButton;
    @FXML
    private Button simulateButton;
    @FXML
    private AnchorPane sortVisualizePane;

    private double currentX = 100;
    private Circle dot;

    ArrayList<Integer> sortArray = new ArrayList<>();
    ArrayList<Rectangle> rectangles = new ArrayList<>();

    @FXML
    void onNextButtonClicked() {
        String userInput = inputPlace.getText();
        System.out.println(userInput);

        try {
            int height = Integer.parseInt(userInput);

            Rectangle rectangle = new Rectangle(15, height);
            rectangle.setFill(Color.RED);
            rectangle.setLayoutX(currentX);
            rectangle.setLayoutY(400 - height);

            sortVisualizePane.getChildren().add(rectangle);
            rectangles.add(rectangle);

            currentX += 20;
            inputPlace.clear();

            sortArray.add(height);

        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid integer.");
        }
    }

    @FXML
    void onStartButtonClicked() {
        //bubbleSort();
        simulateButton.setVisible(true);
        simulateButton.setDisable(false);
        dot = new Circle();
        dot.setCenterX(110);
        dot.setCenterY(410);
        dot.setRadius(5);
        dot.setFill(Color.LIME);
        sortVisualizePane.getChildren().add(dot);
    }

    private int bubblei=0, bubblej=0;
    @FXML
    void onSimulateButtonClicked()
    {
        if (dot != null) {
            sortVisualizePane.getChildren().remove(dot);
        }

        bubbleSort();
        dot = new Circle();
        dot.setCenterX(110 + bubblej*20);
        dot.setCenterY(410);
        dot.setRadius(5);
        dot.setFill(Color.LIME);
        sortVisualizePane.getChildren().add(dot);

    }

    // Function to perform bubble sort with visualization
    void bubbleSort() {
        int n = sortArray.size();

        if(bubblei == n-1)
        {
            simulateButton.setDisable(true);
        }
        else if(bubblej < n-bubblei-1)
        {

            if (sortArray.get(bubblej) > sortArray.get(bubblej + 1)) {

                int temp = sortArray.get(bubblej);
                sortArray.set(bubblej, sortArray.get(bubblej + 1));
                sortArray.set(bubblej + 1, temp);

                TranslateTransition left = new TranslateTransition(Duration.seconds(2),rectangles.get(bubblej));
                TranslateTransition right = new TranslateTransition(Duration.seconds(2),rectangles.get(bubblej+1));

                left.setByX(20);
                right.setByX(-20);


                left.play();
                right.play();

                Rectangle tempRec = rectangles.get(bubblej);
                rectangles.set(bubblej, rectangles.get(bubblej+1));
                rectangles.set(bubblej+1,tempRec);
            }


            bubblej++;
            if(bubblej == n-bubblei-1)
            {
                bubblei++;
                bubblej = 0;
            }
        }

    }





}
