package Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Task {
    // Instance variables
    private JPanel taskPanel;
    private JCheckBox checkBox;
    JLabel taskLabel;
    private JPanel parentPanel;
    JLabel feedbackLabel;
    private ArrayList<String> chatMessages;
    private JTextArea chatArea;

    // Constructor method for Task class
    public Task(String taskText, JPanel parentPanel) {
        this.parentPanel = parentPanel;
        this.chatMessages = new ArrayList<>();
        taskPanel = new JPanel();
        taskPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create a checkbox and label for task
        checkBox = new JCheckBox();
        taskLabel = new JLabel(taskText);
        feedbackLabel = new JLabel("This is feedback");

        // Add actionListener to checkbox to update task status and move finished tasks to bottom
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTaskStatus();
                moveCompletedTaskToEnd();
            }
        });

        // Add components to task panel
        taskPanel.add(checkBox);
        taskPanel.add(taskLabel);
        taskPanel.add(feedbackLabel);

        // Add right-click menu for deleting, renaming, and providing feedback on a task
        JPopupMenu popupMenu = new JPopupMenu();

        // Add a delete option to the right-click menu
        popupMenu.add("Delete").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        // Add a feedback option to the right-click menu
        popupMenu.add("Feedback").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provideFeedback();
            }
        });

        // Add a rename option to the right-click menu
        popupMenu.add("Rename").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renameTask();
            }
        });

        // Add a chat option to the right-click menu
        popupMenu.add("Chat").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openChatWindow();
            }
        });

        taskPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(taskPanel, e.getX(), e.getY());
                }
            }
        });
    }

    // Method to update task status and change visual of task
    private void updateTaskStatus() {
        if (checkBox.isSelected()) {
            taskLabel.setForeground(Color.GRAY);
            taskLabel.setFont(new Font(taskLabel.getFont().getName(), Font.ITALIC, taskLabel.getFont().getSize()));
        } else {
            taskLabel.setForeground(Color.BLACK);
            taskLabel.setFont(new Font(taskLabel.getFont().getName(), Font.PLAIN, taskLabel.getFont().getSize()));
        }
    }

    // Method to move task to end of list
    private void moveCompletedTaskToEnd() {
        if (checkBox.isSelected()) {
            parentPanel.remove(taskPanel);
            parentPanel.add(taskPanel);
            parentPanel.revalidate();
            parentPanel.repaint();
        }
    }

    // Method to rename the task
    void renameTask() {
        // Prompt user to enter a new task
        String newTaskText = JOptionPane.showInputDialog(null, "Enter a New Task Name: ", taskLabel.getText());
        // If valid input is provided, update the task label
        if (newTaskText != null && !newTaskText.isEmpty()) {
            taskLabel.setText(newTaskText);
        }
    }

    // Method to provide feedback
    void provideFeedback() {
        // Prompt user to enter feedback
        String feedback = JOptionPane.showInputDialog(null, "Enter Feedback: ");
        // If valid input is provided, update the feedback label
        if (feedback != null && !feedback.isEmpty()) {
            feedbackLabel.setText(feedback);
        }
    }

    // Method to open chat window
    private void openChatWindow() {
        JFrame chatFrame = new JFrame("Chat");
        chatFrame.setSize(300, 200);
        chatFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        for (String message : chatMessages) {
            chatArea.append(message + "\n");
        }

        JTextField chatInput = new JTextField();
        chatInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chatInput.getText();
                if (!message.isEmpty()) {
                    addChatMessage("Student: " + message);
                    chatInput.setText("");
                    chatArea.append("Student: " + message + "\n");
                }
            }
        });

        chatFrame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        chatFrame.add(chatInput, BorderLayout.SOUTH);
        chatFrame.setVisible(true);
    }

    // Method to add a chat message
    public void addChatMessage(String message) {
        chatMessages.add(message);
    }

    // Method to delete task
    void deleteTask() {
        parentPanel.remove(taskPanel);
        parentPanel.revalidate();
        parentPanel.repaint();
    }

    // Getter method
    public JPanel getTaskPanel() {
        return taskPanel;
    }
}