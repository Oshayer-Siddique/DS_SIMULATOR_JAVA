package com.example.ds_simulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EtchedBorder;

public class BSTVisualization extends JFrame {

    private BST bst;

    private JPanel controlPanel;
    private JPanel treePanel;
    private JTextField valueField;
    private List<Integer> searchPath = new ArrayList<>();
    private Integer foundNode = null;

    public BSTVisualization() {
        bst = new BST();
        initializeUI();
    }

    private void initializeUI() {



        setTitle("BST Visualization");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.CYAN);
        valueField = new JTextField(10);
        controlPanel.setLayout(new FlowLayout());  // Use FlowLayout for controlPanel
        controlPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        JButton insertButton = new JButton("Insert");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");
        JButton inOrderButton = new JButton("In-order");
        JButton preOrderButton = new JButton("Pre-order");
        JButton postOrderButton = new JButton("Post-order");
        JButton backButton = new JButton("Back");

        Font buttonFont = new Font("Arial", Font.BOLD, 20);

        insertButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        inOrderButton.setFont(buttonFont);
        preOrderButton.setFont(buttonFont);
        postOrderButton.setFont(buttonFont);
        backButton.setFont(buttonFont);

        controlPanel.add(backButton);
        controlPanel.add(new JLabel("Value: "));
        controlPanel.add(valueField);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);
        controlPanel.add(searchButton);
        controlPanel.add(inOrderButton);
        controlPanel.add(preOrderButton);
        controlPanel.add(postOrderButton);

        add(controlPanel, BorderLayout.NORTH);  // Add controlPanel to the NORTH of the frame

        treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g);
            }
        };
        treePanel.setBackground(Color.BLACK);
        add(treePanel, BorderLayout.CENTER);  // Add treePanel to the CENTER of the frame

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInsert();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDelete();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });

        inOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTraversal("inOrder");
            }
        });

        preOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTraversal("preOrder");
            }
        });

        postOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTraversal("postOrder");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void handleInsert() {
        try {
            int value = Integer.parseInt(valueField.getText());
            bst.insert(value);
            treePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    private void handleDelete() {
        try {
            int value = Integer.parseInt(valueField.getText());
            bst.delete(value);
            treePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    private void handleSearch() {
        try {
            int value = Integer.parseInt(valueField.getText());
            searchPath.clear();
            foundNode = bst.search(value, searchPath);
            if (foundNode == null) {
                JOptionPane.showMessageDialog(this, "Value not found in the BST.");
            }
            treePanel.repaint();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    private void handleTraversal(String type) {
        List<Integer> values = new ArrayList<>();
        switch (type) {
            case "inOrder":
                bst.inOrderTraversal(bst.root, values);
                break;
            case "preOrder":
                bst.preOrderTraversal(bst.root, values);
                break;
            case "postOrder":
                bst.postOrderTraversal(bst.root, values);
                break;
        }
        JOptionPane.showMessageDialog(this, type + ": " + values.toString());
    }

    private void drawTree(Graphics g) {
        int startX = getWidth() / 2;
        int startY = 50;
        int[] parentX = new int[100];
        int[] parentY = new int[100];
        drawNode(g, bst.root, startX, startY, 0, 0, parentX, parentY);
    }

    private void drawNode(Graphics g, BST.Node node, int x, int y, int level, int parentX, int[] parentXArray, int[] parentYArray) {
        if (node == null) return;
        int radius = 20;
        int gap = 200 / (level + 1);

        if (level > 0) {
            g.drawLine(parentXArray[level - 1], parentYArray[level - 1], x, y);
        }

        if (foundNode != null && node.value == foundNode) {
            g.setColor(Color.RED); // Change color to red if node is found during search
        } else if (searchPath.contains(node.value)) {
            g.setColor(Color.MAGENTA); // Change color to green if node is in search path
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.setColor(Color.yellow);
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
        g.drawString(Integer.toString(node.value), x - 7, y + 5);

        parentXArray[level] = x;
        parentYArray[level] = y;

        drawNode(g, node.left, x - gap, y + 50, level + 1, x, parentXArray, parentYArray);
        drawNode(g, node.right, x + gap, y + 50, level + 1, x, parentXArray, parentYArray);

        // Set color back to default
        g.setColor(Color.yellow);
    }

    class BST {
        class Node {
            int value;
            Node left, right;

            public Node(int value) {
                this.value = value;
                left = right = null;
            }
        }

        Node root;

        public BST() {
            root = null;
        }

        public void insert(int value) {
            root = insertRec(root, value);
        }

        private Node insertRec(Node root, int value) {
            if (root == null) {
                root = new Node(value);
                return root;
            }
            if (value < root.value)
                root.left = insertRec(root.left, value);
            else if (value > root.value)
                root.right = insertRec(root.right, value);

            return root;
        }

        public void delete(int value) {
            root = deleteRec(root, value);
        }

        private Node deleteRec(Node root, int value) {
            if (root == null) return root;

            if (value < root.value)
                root.left = deleteRec(root.left, value);
            else if (value > root.value)
                root.right = deleteRec(root.right, value);
            else {
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;

                root.value = minValue(root.right);
                root.right = deleteRec(root.right, root.value);
            }

            return root;
        }

        private int minValue(Node root) {
            int minVal = root.value;
            while (root.left != null) {
                root = root.left;
                minVal = root.value;
            }
            return minVal;
        }

        public Integer search(int value, List<Integer> path) {
            return searchRec(root, value, path);
        }

        private Integer searchRec(Node root, int value, List<Integer> path) {
            if (root == null) return null;
            path.add(root.value);
            if (root.value == value) return root.value;
            if (value < root.value) return searchRec(root.left, value, path);
            else return searchRec(root.right, value, path);
        }

        public void inOrderTraversal(Node node, List<Integer> values) {
            if (node != null) {
                inOrderTraversal(node.left, values);
                values.add(node.value);
                inOrderTraversal(node.right, values);
            }
        }

        public void preOrderTraversal(Node node, List<Integer> values) {
            if (node != null) {
                values.add(node.value);
                preOrderTraversal(node.left, values);
                preOrderTraversal(node.right, values);
            }
        }

        public void postOrderTraversal(Node node, List<Integer> values) {
            if (node != null) {
                postOrderTraversal(node.left, values);
                postOrderTraversal(node.right, values);
                values.add(node.value);
            }
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new BSTVisualization();
//            }
//        });
//    }
}
