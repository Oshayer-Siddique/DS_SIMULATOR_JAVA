package com.example.ds_simulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class QueueVisualization {

    private JFrame jf;
    private Queue<Integer> queue = new LinkedList<>();
    private final int RECT_WIDTH = 40;
    private final int RECT_HEIGHT = 60;
    private final int SPACING = 10;
    private final int CANVAS_WIDTH = 1000;
    private final int CANVAS_HEIGHT = 600;

    // PANELS
    JPanel tools = new JPanel();
    GraphCanvas canvas;

    // LABELS
    JLabel sizeL = new JLabel("Queue Size: 0");
    JLabel frontL = new JLabel("Front: None");
    JLabel rearL = new JLabel("Rear: None");

    // BUTTONS
    JButton enqueueB = new JButton("Enqueue");
    JButton dequeueB = new JButton("Dequeue");
    JButton backButton = new JButton("Back");

    public QueueVisualization() {
        // INIT FRAME
        jf = new JFrame();
        jf.setTitle("Queue Visualization");
        jf.setSize(1080, 720);
        jf.setBackground(Color.CYAN);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        // INIT CANVAS
        canvas = new GraphCanvas();
        canvas.setBounds(40, 100, CANVAS_WIDTH, CANVAS_HEIGHT);

        // INIT TOOL PANEL
        tools.setLayout(null);
        tools.setBounds(5, 0, 1070, 100);
        tools.setBackground(Color.CYAN);
        tools.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        // ADD COMPONENTS TO TOOL PANEL
        sizeL.setBounds(180, 20, 150, 60);
        frontL.setBounds(350, 20, 150, 60);
        rearL.setBounds(520, 20, 150, 60);
        enqueueB.setBounds(730, 20, 120, 60);
        dequeueB.setBounds(860, 20, 120, 60);
        backButton.setBounds(10,20,120,60);

        sizeL.setOpaque(true);
        sizeL.setHorizontalAlignment(SwingConstants.CENTER);
        sizeL.setBackground(Color.pink);

        frontL.setOpaque(true);
        frontL.setHorizontalAlignment(SwingConstants.CENTER);
        frontL.setBackground(Color.pink);

        rearL.setOpaque(true);
        rearL.setHorizontalAlignment(SwingConstants.CENTER);
        rearL.setBackground(Color.pink);

        sizeL.setFont(new Font("Arial", Font.PLAIN, 20));
        frontL.setFont(new Font("Arial", Font.PLAIN, 20));
        rearL.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        enqueueB.setFont(new Font("Arial", Font.PLAIN, 20));
        dequeueB.setFont(new Font("Arial", Font.PLAIN, 20));

        tools.add(sizeL);
        tools.add(frontL);
        tools.add(rearL);
        tools.add(enqueueB);
        tools.add(dequeueB);
        tools.add(backButton);

        jf.add(tools);
        jf.add(canvas);

        jf.setVisible(true);

        // ACTION LISTENERS
        enqueueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter value to enqueue:");
                if (input != null) {
                    try {
                        int value = Integer.parseInt(input);
                        queue.offer(value);
                        updateLabels();
                        repaintCanvas();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter an integer.");
                    }
                }
            }
        });

        dequeueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!queue.isEmpty()) {
                    queue.poll();
                    updateLabels();
                    repaintCanvas();
                } else {
                    JOptionPane.showMessageDialog(null, "Queue is empty. Nothing to dequeue.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() { // Back button action listener
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose(); // Close the current window
            }
        });

    }

    private void updateLabels() {
        sizeL.setText("Queue Size: " + queue.size());
        frontL.setText("Front: " + (queue.isEmpty() ? "None" : queue.peek()));
        rearL.setText("Rear: " + (queue.isEmpty() ? "None" : getRearElement()));
    }

    private int getRearElement() {
        int rear = -1;
        for (int element : queue) {
            rear = element;
        }
        return rear;
    }

    private void repaintCanvas() {
        canvas.repaint();
    }

    class GraphCanvas extends JPanel {

        public GraphCanvas() {
            setBackground(Color.black);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int xPosition = SPACING;

            Boolean isFirst = true;
            for (int value : queue) {

                if(isFirst)
                {
                    g.setColor(Color.RED);
                    isFirst = false;
                }
                else
                    g.setColor(Color.BLUE);


                g.fillRect(xPosition, (getHeight() - RECT_HEIGHT) / 2, RECT_WIDTH, RECT_HEIGHT);
                g.setColor(Color.YELLOW);
                g.drawRect(xPosition, (getHeight() - RECT_HEIGHT) / 2, RECT_WIDTH, RECT_HEIGHT);

                if(value/10 == 0)
                    g.drawString(String.valueOf(value), xPosition + 15, (getHeight() + RECT_HEIGHT) / 2 - 24);
                else if(value/100 == 0)
                    g.drawString(String.valueOf(value), xPosition + 12, (getHeight() + RECT_HEIGHT) / 2 - 24);
                else if(value/1000 == 0)
                    g.drawString(String.valueOf(value), xPosition + 9, (getHeight() + RECT_HEIGHT) / 2 - 24);
                else if(value/10000 == 0)
                    g.drawString(String.valueOf(value), xPosition + 6, (getHeight() + RECT_HEIGHT) / 2 - 24);
                else
                    g.drawString(String.valueOf(value), xPosition + 3, (getHeight() + RECT_HEIGHT) / 2 - 24);
                xPosition += RECT_WIDTH + SPACING;

//                Information text here
//                g.drawString("Operations on the queue will be shown here.", 20, 80);

            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("Queue Visualization", 300, 50);

        }
    }

//    public static void main(String[] args) {
//        new QueueVisualization();
//    }
}

