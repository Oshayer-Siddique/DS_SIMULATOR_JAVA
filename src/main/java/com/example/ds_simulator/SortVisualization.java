package com.example.ds_simulator;

import javafx.scene.layout.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.Arrays;
import java.util.Random;

public class SortVisualization {
    private JFrame jf;
    private int len = 50;
    private int spd = 15;
    private int current = -1;
    private int check = -1;
    private boolean sorting = false;
    private boolean shuffled = true;

    private final int SIZE = 1000;
    private int[] list;
    private final Random random = new Random();

    private final String[] algorithms = {"Bubble Sort", "Insertion Sort", "Selection Sort", "Quick Sort", "Merge Sort", "Heap Sort"};
    private final String[] algInfo = {
            "Best: O(n), Worst: O(n^2), Avg: O(n^2)",
            "Best: O(n), Worst: O(n^2), Avg: O(n^2)",
            "Best: O(n^2), Worst: O(n^2), Avg: O(n^2)",
            "Best: O(n log n), Worst: O(n^2), Avg: O(n log n)",
            "Best: O(n log n), Worst: O(n log n), Avg: O(n log n)",
            "Best: O(n log n), Worst: O(n log n), Avg: O(n log n)"
    };
    private int curAlg = 0;

    private JPanel tools = new JPanel();
    private GraphCanvas canvas;
    private JLabel delayL = new JLabel("DELAY:");
    private JLabel msL = new JLabel(spd + " ms");
    private JLabel sizeL = new JLabel("SIZE:");
    private JLabel lenL = new JLabel(len + "");

    private JButton sortB = new JButton("Sort");
    private JButton shuffleB = new JButton("Shuffle");
    private JButton resetB = new JButton("Reset");
    private JButton backB = new JButton("Back");
    private Button button = new Button();






    private JSlider delay = new JSlider(JSlider.HORIZONTAL, 1, 1000, spd);
    private JSlider size = new JSlider(JSlider.HORIZONTAL, 10, 100, len);
    private JComboBox<String> algoSelection = new JComboBox<>(algorithms);

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(SortVisualization::new);
//    }

    public SortVisualization() {
        jf = new JFrame();
        jf.setTitle("Sorting Visualization");
        jf.setSize(1080, 720);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);



//        button.setSize(50, 50);
//
//        // Load the background image
//        String imagePath = getClass().getResource("/images/offRed.jpg").toString();
//
//        Image image = new javafx.scene.image.Image(imagePath);
//
//        // Create a BackgroundImage
//        BackgroundImage backgroundImage = new BackgroundImage(image,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                new BackgroundSize(50, 50, false, false, false, false)
//        );
//
//        // Set the BackgroundImage to the Button
//        button.setBackground(new Background(backgroundImage));

        // Add the button to the existing ToolBar
//        tools.add(button);

        addButtonWithImage(tools);

        // Add the toolBar to the frame




        list = new int[len];
        generateRandomArray();

        canvas = new GraphCanvas();
        canvas.setBounds(40, 100, SIZE, SIZE - 400);

        tools.setLayout(null);
        tools.setBounds(0, 0, 1080, 100);
        tools.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        delay.setBounds(180, 10, 150, 50);
        size.setBounds(480, 10, 150, 50);
        delayL.setBounds(130, 10, 80, 45);
        msL.setBounds(340, 10, 80, 45);
        sizeL.setBounds(440, 10, 80, 45);
        lenL.setBounds(640, 10, 80, 45);

        sortB.setBounds(780, 10, 80, 35);
        shuffleB.setBounds(780, 55, 80, 35);
        resetB.setBounds(880, 10, 80, 35);
        backB.setBounds(880, 55, 80, 35);
        algoSelection.setBounds(350, 55, 120, 40);


        tools.add(delay);
        tools.add(size);
        tools.add(delayL);
        tools.add(msL);
        tools.add(sizeL);
        tools.add(lenL);
        tools.add(sortB);
        tools.add(shuffleB);
        tools.add(backB);
        tools.add(resetB);
        tools.add(algoSelection);

        jf.add(tools);
        jf.add(canvas);

        jf.setVisible(true);

        sortB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sorting && shuffled) {
                    sorting = true;
                    new Thread(() -> {
                        switch (curAlg) {
                            case 0: bubbleSort(); break;
                            case 1: insertionSort(); break;
                            case 2: selectionSort(); break;
                            case 3: quickSort(0, len - 1); break;
                            case 4: mergeSort(0, len - 1); break;
                            case 5: heapSort(); break;
                        }
                        sorting = false;
                    }).start();
                }
            }
        });

        backB.addActionListener(new ActionListener() { // Back button action listener
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose(); // Close the current window
            }
        });

        resetB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomArray();
                shuffled = true;
                sorting = false;
                current = -1;
                check = -1;
                canvas.repaint();
            }
        });

        shuffleB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleArray();
                shuffled = true;
                sorting = false;
                current = -1;
                check = -1;
                canvas.repaint();
            }
        });

        delay.addChangeListener(e -> {
            spd = delay.getValue();
            msL.setText(spd + " ms");
        });

        size.addChangeListener(e -> {
            len = size.getValue();
            lenL.setText(len + "");
            sorting = false;
            generateRandomArray();
            canvas.repaint();
        });

        algoSelection.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                curAlg = algoSelection.getSelectedIndex();
                JOptionPane.showMessageDialog(null, algInfo[curAlg], algorithms[curAlg] + " Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


        private void addButtonWithImage(JPanel tools) {
            // Load the image
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/offRed.png"));

            // Create a JButton and set the icon
            JButton button = new JButton();
            button.setIcon(icon);
            button.setPreferredSize(new Dimension(50, 50));
            button.setAlignmentX(20);
            button.setAlignmentY(20);
            button.setBorderPainted(false); // Optional: remove the border
            button.setFocusPainted(false);  // Optional: remove the focus border
            button.setContentAreaFilled(false); // Optional: make the background transparent

            // Add the button to the toolBar
            tools.add(button);
            tools.setVisible(true);
        }


    private void generateRandomArray() {
        list = new int[len];
        for (int i = 0; i < len; i++) {
            list[i] = random.nextInt(99) + 2;
        }
    }

    private void shuffleArray() {
        for (int i = 0; i < len; i++) {
            int randIndex = random.nextInt(len);
            int temp = list[i];
            list[i] = list[randIndex];
            list[randIndex] = temp;
        }
    }

    class GraphCanvas extends JPanel {
        public GraphCanvas() {
            setBackground(Color.black);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i = 0; i < len; i++) {
                if (i == current) {
                    g.setColor(Color.RED);
                }else if(check > -1 && i == check)
                {
                    g.setColor(Color.green);
                }
                else {
                    g.setColor(Color.BLUE);
                }

                int barWidth = SIZE / len;
                int barHeight = (list[i] * 5);

                g.fillRect(i * barWidth, SIZE - 420 - barHeight, barWidth, barHeight);
                g.setColor(Color.BLACK);
                g.drawRect(i * barWidth, SIZE - 420 - barHeight, barWidth, barHeight);

                g.setColor(Color.YELLOW);
                String number = String.valueOf(list[i]);
                int stringWidth = g.getFontMetrics().stringWidth(number);
                int x = i * barWidth + (barWidth - stringWidth) / 2;
                int y = SIZE - 420 - barHeight - 5;
                g.drawString(number, x, y);
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(spd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void selectionSort() {
        int i = 0;
        while (i < len && sorting) {
            int sm = i;
            current = i;
            check = i;
            for (int j = i+1; j < len ; j++) {
                if(!sorting)
                    break;
                if (list[j] < list[sm]) {
                    sm = j;

                }
                current = j;
                canvas.repaint();
                sleep();
            }
            if(i != sm)
            {
                int temp = list[sm];
                list[sm] = list[i];
                list[i] = temp;
            }
            i++;
        }
    }

    private void insertionSort() {
        for (int i = 1; i < len; i++) {
            current = i;
            int j = i;
            while (list[j] < list[j-1]  && sorting) {
                int temp = list[j-1];
                list[j - 1] = list[j];
                list[j] = temp;
                check = j;
                canvas.repaint();
                sleep();
                if(j > 1)
                    j--;
            }

        }
    }

    private void bubbleSort() {
        int c = 1;
        boolean noswaps = false;
        while(!noswaps && sorting) {
            current = len-c;
            noswaps = true;
            for(int i = 0; i < len-c; i++) {
                if(!sorting)
                    break;
                if(list[i+1] < list[i]) {
                    noswaps = false;
                    int temp = list[i];
                    list[i] = list[i+1];
                    list[i+1] = temp;
                }
                check = i+1;
                canvas.repaint();
                sleep();
            }
            c++;
        }
    }

    public void quickSort(int lo, int hi) {
        if(!sorting)
            return;
        current = hi;
        if(lo < hi) {
            int p = partition(lo,hi);
            quickSort(lo,p-1);
            quickSort(p+1, hi);
        }
    }

    public int partition(int lo, int hi) {
        int pivot = list[hi];
        int i = lo - 1;
        for(int j = lo; j < hi; j++) {
            check = j;
            if(!sorting)
                break;
            if(list[j] < pivot) {
                i++;
                swap(i,j);
            }
            canvas.repaint();
            sleep();
        }
        swap(i+1,hi);
        canvas.repaint();
        sleep();
        return i+1;
    }

    void merge(int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int [n1];
        int R[] = new int [n2];

        for (int i=0; i<n1; i++) {
            L[i] = list[l + i];
        }
        for (int j=0; j<n2; j++) {
            R[j] = list[m + 1+ j];
        }
        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2 && sorting) {
            check = k;
            if (L[i] <= R[j]) {
                list[k] = L[i];
                i++;
            } else {
                list[k] = R[j];
                j++;
            }
            canvas.repaint();
            sleep();
            k++;
        }

        while (i < n1 && sorting) {
            list[k] = L[i];
            i++;
            k++;
            canvas.repaint();
            sleep();
        }

        while (j < n2 && sorting) {
            list[k] = R[j];
            j++;
            k++;
            canvas.repaint();
            sleep();
        }
    }

    public void mergeSort(int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            current = r;
            mergeSort(l, m);
            mergeSort(m+1, r);

            merge(l, m, r);
        }
    }

    public void heapSort() {
        heapify(len);
        int end = len-1;
        while(end > 0 && sorting) {
            current = end;
            swap(end,0);

            end--;
            siftDown(0,end);
            canvas.repaint();
            sleep();
        }
    }

    public void heapify(int n) {
        int start = iParent(n-1);
        while(start >= 0 && sorting) {
            siftDown(start, n-1);
            start--;
            canvas.repaint();
            sleep();
        }
    }

    public void siftDown(int start, int end) {
        int root = start;
        while(iLeftChild(root) <= end && sorting) {
            int child = iLeftChild(root);
            int swap = root;
            check = root;
            if(list[swap] < list[child]) {
                swap = child;
            } if(child+1 <= end && list[swap] < list[child+1]) {
                swap = child+1;
            } if(swap == root) {
                return;
            } else {
                swap(root,swap);
                check = root;
                root = swap;
            }
            canvas.repaint();
            sleep();
        }
    }

    public int iParent(int i) { return ((i-1)/2); }
    public int iLeftChild(int i) { return 2*i + 1; }


    public void swap(int i1, int i2) {
        int temp = list[i1];
        list[i1] = list[i2];
        list[i2] = temp;
    }
}
