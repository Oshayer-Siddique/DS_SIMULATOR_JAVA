package com.example.ds_simulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import java.util.Arrays;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SearchVisualization {
    // FRAME
    private JFrame jf;
    // GENERAL VARIABLES
    private int len = 50;
    private int curAlg = 0;
    private int spd = 15;
    private int searchKey = -1;
    private boolean found = false;
    // GRAPH VARIABLES
    private final int SIZE = 1000;
    private int current = -1;
    private int width = (SIZE) / len;
    // ARRAYS
    private int[] list;
    private String[] algorithms = {"Linear Search", "Binary Search"};
    private String[] algInfo = {"Best Case: O(1)\nWorst Case: O(n)\nAverage: O(n/2)",
            "Best Case: O(1)\nWorst Case: O(log n)\nAverage: O(log n)"};
    // BOOLEANS
    private boolean searching = false;
    private boolean shuffled = true;
    // UTIL OBJECTS
    SearchAlgorithms algorithm = new SearchAlgorithms();
    Random r = new Random();
    // PANELS
    JPanel tools = new JPanel();
    GraphCanvas canvas;
    // LABELS
    JLabel delayL = new JLabel("DELAY :");
    JLabel msL = new JLabel(spd + " ms");
    JLabel sizeL = new JLabel("SIZE :");
    JLabel lenL = new JLabel(len + "");
    JLabel searchKeyL = new JLabel("Search Options -->");
    JLabel searchL = new JLabel("Search types -->");
    // BUTTONS
    JButton searchB = new JButton("Search");
    JButton resetB = new JButton("Reset");
    JButton shuffleB = new JButton("Shuffle");
    JButton backB = new JButton("Back"); // New Back button
    // SLIDERS
    JSlider delay = new JSlider(JSlider.HORIZONTAL, 1, 1000, spd);
    JSlider size = new JSlider(JSlider.HORIZONTAL, 1, 8, 8);
    // COMBO BOXES
    JComboBox<String> algoSelection = new JComboBox<>(algorithms);

//    public static void main(String[] args) {
//        new SearchVisualization();
//    }

    public SearchVisualization() {
        // INIT FRAME
        jf = new JFrame();
        jf.setTitle("Searching Visualization");
        jf.setSize(1080, 720);
        jf.setBackground(Color.CYAN);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        // INIT ARRAY
        list = new int[len];
        generateRandomArray();

        // INIT CANVAS
        canvas = new GraphCanvas();
        canvas.setBounds(40, 100, SIZE, SIZE-400);

        // INIT TOOL PANEL
        tools.setLayout(null);
        tools.setBounds(0, 0, 1080, 100); // Adjusted size to fit all buttons
        tools.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        // ADD COMPONENTS TO TOOL PANEL
        delay.setBounds(180, 10, 150, 50);
        size.setBounds(480, 10, 150, 50);
        delayL.setBounds(130, 10, 80, 45);
        msL.setBounds(340, 10, 80, 45);
        sizeL.setBounds(440, 10, 80, 45);
        lenL.setBounds(640, 10, 80, 45);
        searchKeyL.setBounds(670, 25, 120, 50);
        searchL.setBounds(250,47,150,50);

        searchB.setBounds(780, 10, 80, 35);
        shuffleB.setBounds(780, 55, 80, 35);
        resetB.setBounds(880, 10, 80, 35);
        backB.setBounds(880, 55, 80, 35); // Position the Back button
        algoSelection.setBounds(350, 55, 120, 40);

        tools.add(delay);
        tools.add(searchL);
        tools.add(size);
        tools.add(delayL);
        tools.add(msL);
        tools.add(sizeL);
        tools.add(lenL);
        tools.add(searchKeyL);
        tools.add(searchB);
        tools.add(shuffleB);
        tools.add(resetB);
        tools.add(backB); // Add the Back button
        tools.add(algoSelection);

        tools.setBackground(Color.CYAN);


        jf.add(tools);
        jf.add(canvas);
        jf.setBackground(Color.CYAN);

        jf.setVisible(true);

        // ACTION LISTENERS
        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!searching && shuffled) {
                    searchKey = Integer.parseInt(JOptionPane.showInputDialog("Enter value to search:"));
                    found = false;
                    searching = true;
                    new Thread() {
                        public void run() {
                            switch (curAlg) {
                                case 0:
                                    algorithm.linearSearch(list, searchKey);
                                    break;
                                case 1:
                                    algorithm.binarySearch(list, searchKey);
                                    break;
                            }
                            searching = false;
                        }
                    }.start();
                }
            }
        });

        resetB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomArray();
                shuffled = true;
                current = -1;
                repaintCanvas();
            }
        });

        shuffleB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleArray();
                shuffled = true;
                current = -1;
                repaintCanvas();
            }
        });

        backB.addActionListener(new ActionListener() { // Back button action listener
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose(); // Close the current window
            }
        });

        delay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                spd = delay.getValue();
                msL.setText(spd + " ms");
            }
        });

        size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                int temp = size.getValue();

                switch (temp) {
                    case 1:
                        len = 2;
                        generateRandomArray();
                        break;
                    case 2:
                        len = 4;
                        generateRandomArray();
                        break;
                    case 3:
                        len = 8;
                        generateRandomArray();
                        break;
                    case 4:
                        len = 10;
                        generateRandomArray();
                        break;
                    case 5:
                        len = 20;
                        generateRandomArray();
                        break;
                    case 6:
                        len = 25;
                        generateRandomArray();
                        break;
                    case 7:
                        len = 40;
                        generateRandomArray();
                        break;
                    case 8:
                        len = 50;
                        generateRandomArray();
                        break;
                    case 9:
                        len = 100;
                        generateRandomArray();
                        break;
                    default:
                        len = 50;
                        generateRandomArray();
                        break;
                }
//                len = size.getValue();
                lenL.setText(len + "");
                width = SIZE / len;
//                generateRandomArray();
                repaintCanvas();
            }
        });

        algoSelection.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    curAlg = algoSelection.getSelectedIndex();
                    JOptionPane.showMessageDialog(null, algInfo[curAlg], algorithms[curAlg] + " Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }

    private void generateRandomArray() {
        for (int i = 0; i < len; i++) {
            list[i] = r.nextInt(99) + 2;
        }
    }

    private void shuffleArray() {
        for (int i = 0; i < len; i++) {
            int randIndex = r.nextInt(len);
            int temp = list[i];
            list[i] = list[randIndex];
            list[randIndex] = temp;
        }
    }

    private void repaintCanvas() {
        canvas.repaint();
    }

    class GraphCanvas extends JPanel {

        public GraphCanvas() {
            setBackground(Color.black);
        }

//        private final double widthScale = 1; // Adjust this factor to change bar width
//        private final double heightScale = 5.0; // Adjust this factor to change bar height
        private final int fontSize = 10; // Adjust this value to change the font size

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Font font = new Font("Arial", Font.PLAIN, fontSize);
            g.setFont(font);

            for (int i = 0; i < len; i++) {
                if (i == current) {
                    g.setColor(Color.RED);
                }
                else {
                    g.setColor(Color.BLUE);
                }

                int barWidth = width;
                int barHeight = (list[i] * 5);

                g.fillRect(i * barWidth , SIZE - 420 - barHeight, barWidth, barHeight);

                // Set color for the border
                g.setColor(Color.BLACK);
                g.drawRect(i * barWidth , SIZE - 420 - barHeight, barWidth, barHeight);

                // Draw the number on top of the bar
                g.setColor(Color.YELLOW);
                String number = String.valueOf(list[i]);
                int stringWidth = g.getFontMetrics().stringWidth(number);
                int x = i * barWidth  + (barWidth - stringWidth) / 2; // Adjusting x position to center the number
                int y = SIZE - 420 - barHeight - 5;
                g.drawString(number, x, y);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Search Visualization", 375, 40);

        }
    }

    class SearchAlgorithms {
        public void linearSearch(int[] arr, int key) {
            for (int i = 0; i < arr.length; i++) {
                current = i;
                repaintCanvas();
                sleep();
                if (arr[i] == key) {
                    found = true;
                    searchKey = i;
                    repaintCanvas();
                    break;
                }
            }
            if(!found){
                JOptionPane.showMessageDialog(null,"Number not found");
                current = -1;
                repaintCanvas();
            }
        }

        public void binarySearch(int[] arr, int key) {
            Arrays.sort(arr); // Binary search requires sorted array
            int left = 0;
            int right = len - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                current = mid;
                repaintCanvas();
                sleep();
                if (arr[mid] == key) {
                    found = true;
                    searchKey = mid;
                    repaintCanvas();
                    break;
                } else if (arr[mid] < key) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if(!found){

                JOptionPane.showMessageDialog(null,"Number not found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                current = -1;
                repaintCanvas();
            }
        }

        private void sleep() {
            try {
                Thread.sleep(spd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
