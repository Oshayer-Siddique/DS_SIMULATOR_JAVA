package com.example.ds_simulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class StackVisualization {

    private JFrame jf;
    private Stack<Integer> stack = new Stack<>();
    private final int RECT_WIDTH = 50;
    private final int RECT_HEIGHT = 30;
    private final int SPACING = 10;
    private final int CANVAS_WIDTH = 800;
    private final int CANVAS_HEIGHT = 675;

    // PANELS
    JPanel tools = new JPanel();
    GraphCanvas canvas;

    // LABELS
    JLabel sizeL = new JLabel("Stack Size: 0");
    JLabel topL = new JLabel("Top: None");

    // BUTTONS
    JButton pushB = new JButton("Push");
    JButton popB = new JButton("Pop");
    JButton backButton = new JButton("<-- Main menu");

    public StackVisualization() {
        // INIT FRAME
        jf = new JFrame();
        jf.setTitle("Stack Visualization");
        jf.setSize(1080, 720);
        jf.setBackground(Color.CYAN);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        // INIT CANVAS
        canvas = new GraphCanvas();
        canvas.setBounds(260, 5, CANVAS_WIDTH, CANVAS_HEIGHT);

        // INIT TOOL PANEL
        tools.setLayout(null);
        tools.setBounds(5, 5, 250, 700);
        tools.setBackground(Color.CYAN);
        tools.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));


        // ADD COMPONENTS TO TOOL PANEL
        sizeL.setBounds(20, 520, 210, 60);
        topL.setBounds(20, 600, 210, 60);
        pushB.setBounds(20, 320, 210, 60);
        popB.setBounds(20, 400, 210, 60);
        backButton.setBounds(20, 20, 210, 60);

        popB.setFont(new Font("Arial", Font.PLAIN, 20));
        pushB.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));

        sizeL.setOpaque(true);
        sizeL.setHorizontalAlignment(SwingConstants.CENTER);
        sizeL.setBackground(Color.pink);

        topL.setOpaque(true);
        topL.setHorizontalAlignment(SwingConstants.CENTER);
        topL.setBackground(Color.pink);

        sizeL.setFont(new Font("Arial", Font.PLAIN, 20));
        topL.setFont(new Font("Arial", Font.PLAIN, 20));

        tools.add(sizeL);
        tools.add(topL);
        tools.add(pushB);
        tools.add(popB);
        tools.add(backButton);

        jf.add(tools);
        jf.add(canvas);

        jf.setVisible(true);

        // ACTION LISTENERS
        pushB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter value to push:");
                if (input != null) {
                    try {
                        int value = Integer.parseInt(input);
                        stack.push(value);
                        updateLabels();
                        repaintCanvas();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter an integer.");
                    }
                }
            }
        });

        popB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!stack.isEmpty()) {
                    stack.pop();
                    updateLabels();
                    repaintCanvas();
                } else {
                    JOptionPane.showMessageDialog(null, "Stack is empty. Nothing to pop.");
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
        sizeL.setText("Stack Size: " + stack.size());
        topL.setText("Top: " + (stack.isEmpty() ? "None" : stack.peek()));
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
            int yPosition = CANVAS_HEIGHT - RECT_HEIGHT - SPACING;

            for (int value : stack) {
                g.setColor(Color.BLUE);
                g.fillRect((getWidth() - RECT_WIDTH) / 2, yPosition, RECT_WIDTH, RECT_HEIGHT);
                g.setColor(Color.YELLOW);
                g.drawRect((getWidth() - RECT_WIDTH) / 2, yPosition, RECT_WIDTH, RECT_HEIGHT);

                if(value/10 == 0)
                    g.drawString(String.valueOf(value), (getWidth() + RECT_WIDTH) / 2 - 28, yPosition + 18);
                else if(value/100 == 0)
                    g.drawString(String.valueOf(value), (getWidth() + RECT_WIDTH) / 2 - 31, yPosition + 18);
                else if(value/1000 == 0)
                    g.drawString(String.valueOf(value), (getWidth() + RECT_WIDTH) / 2 - 34, yPosition + 18);
                else if(value/10000 == 0)
                    g.drawString(String.valueOf(value), (getWidth() + RECT_WIDTH) / 2 - 37, yPosition + 18);
                else if(value/100000 == 0)
                    g.drawString(String.valueOf(value), (getWidth() + RECT_WIDTH) / 2 - 40, yPosition + 18);
                else if(value/1000000 == 0)
                    g.drawString(String.valueOf(value), (getWidth() + RECT_WIDTH) / 2 - 43, yPosition + 18);
                else
                    g.drawString(String.valueOf(value), (getWidth() + RECT_WIDTH) / 2 - 46, yPosition + 18);


                yPosition -= RECT_HEIGHT + SPACING;
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 40));
            g.drawString("Stack Visualization", 250, 50);

        }
    }

    // public static void main(String[] args) {
    // new StackVisualization();
    // }
}
