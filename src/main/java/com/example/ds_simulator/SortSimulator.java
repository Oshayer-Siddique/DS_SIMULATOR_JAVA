package com.example.ds_simulator;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class SortSimulator {

    @FXML
    private TextField inputPlace;
    @FXML
    private Button nextNumberButton;
    @FXML
    private Button startButton;
    @FXML
    private AnchorPane sortVisualizePane;

    private double currentX = 100;

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
    }

    // Function to perform bubble sort with visualization
//    void bubbleSort() {
//        int n = sortArray.size();
//
//        // Outer loop for bubble sort
//        for (int i = 0; i < n - 1; i++) {
//            // Inner loop for each pass of bubble sort
//            for (int j = 0; j < n - i - 1; j++) {
//                // If the current element is greater than the next element
//                if (sortArray.get(j) > sortArray.get(j + 1)) {
//                    // Swap the numbers in the array
//                    int temp = sortArray.get(j);
//                    sortArray.set(j, sortArray.get(j + 1));
//                    sortArray.set(j + 1, temp);
//
//                    // Swap and animate the rectangles
//                    swapAndAnimateRectangles(rectangles.get(j), rectangles.get(j + 1));
//
//                    // Sleep briefly to allow animation time to play before next iteration
//                    try {
//                        Thread.sleep(500); // Adjust the duration as needed for smooth animation
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//
//    // Swap two rectangles and animate their movement
//    void swapAndAnimateRectangles(Rectangle rect1, Rectangle rect2) {
//        double x1 = rect1.getLayoutX();
//        double x2 = rect2.getLayoutX();
//
//        // Create translate transitions for both rectangles
//        TranslateTransition tt1 = new TranslateTransition(Duration.millis(500), rect1);
//        TranslateTransition tt2 = new TranslateTransition(Duration.millis(500), rect2);
//
//        // Set the transitions to move the rectangles to each other's position
//        tt1.setByX(x2 - x1);
//        tt2.setByX(x1 - x2);
//
//        // Play the transitions
//        tt1.play();
//        tt2.play();
//
//        // Wait for the transitions to complete
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // After the transitions, swap the positions of the rectangles in the list
//        rectangles.set(rectangles.indexOf(rect1), rect2);
//        rectangles.set(rectangles.indexOf(rect2), rect1);
//    }

}
