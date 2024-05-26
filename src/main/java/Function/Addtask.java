package Function;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Addtask {
    // Instance variables
    JPanel panel;
    JTextField taskTextField;
    JTextField feedbackTextField;
    JButton addButton;

    // Constructor initializes this class
    public Addtask() {
        // Panel for popup to type in task
        panel = new JPanel();
        panel.setLayout(null);

        // Label for task input
        JLabel label = new JLabel("Task: ");
        label.setBounds(10, 10, 50, 20);

        // Create a text field to obtain user input
        taskTextField = new JTextField();
        taskTextField.setBounds(70, 10, 200, 20);

        // Label for feedback input
        JLabel feedbackLabel = new JLabel("Feedback: ");
        feedbackLabel.setBounds(10, 40, 70, 20);

        // Create a text field to obtain feedback input
        feedbackTextField = new JTextField();
        feedbackTextField.setBounds(80, 40, 190, 20);

        // Create the AddTask button with icon
        ImageIcon icon = new ImageIcon("images/add.png");
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create button
        addButton = new JButton("Add Task", scaledIcon);
        addButton.setBounds(10, 70, 120, 30);

        // Set the margin to zero to control look of the button
        addButton.setMargin(new Insets(0, 0, 0, 0));
        addButton.setFocusPainted(false);

        // Add components to the panel
        panel.add(label);
        panel.add(taskTextField);
        panel.add(feedbackLabel);
        panel.add(feedbackTextField);
        panel.add(addButton);
    }

    // Getter methods
    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTaskTextField() {
        return taskTextField;
    }

    public JTextField getFeedbackTextField() {
        return feedbackTextField;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
