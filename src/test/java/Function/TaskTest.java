package Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class TaskTest {

    private Task task;
    private JPanel parentPanel;
    private Addtask addtask;

    @BeforeEach
    public void setUp() {
        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        task = new Task("Initial Task", parentPanel);
        parentPanel.add(task.getTaskPanel());
        addtask = new Addtask(); // Instantiate the Addtask object here
        Main.taskPanel = new JPanel();
        Main.taskPanel.setLayout(new BoxLayout(Main.taskPanel, BoxLayout.Y_AXIS));
    }

    @Test
    public void testRenameTask() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user input for new task name
            String newTaskName = "New Task Name";
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(
                    null, 
                    "Enter a New Task Name: ", 
                    "Initial Task"
            )).thenReturn(newTaskName);

            // Invoke renameTask method
            task.renameTask();

            // Verify that the task label was updated
            assertEquals(newTaskName, task.taskLabel.getText());
        }
    }

    @Test
    public void testRenameTaskWithEmptyInput() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user input for empty task name
            String emptyTaskName = "";
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(
                    null, 
                    "Enter a New Task Name: ", 
                    "Initial Task"
            )).thenReturn(emptyTaskName);

            // Invoke renameTask method
            task.renameTask();

            // Verify that the task label was not updated
            assertEquals("Initial Task", task.taskLabel.getText());
        }
    }

    @Test
    public void testRenameTaskWithNullInput() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user input for null task name
            String nullTaskName = null;
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(
                    null, 
                    "Enter a New Task Name: ", 
                    "Initial Task"
            )).thenReturn(nullTaskName);

            // Invoke renameTask method
            task.renameTask();

            // Verify that the task label was not updated
            assertEquals("Initial Task", task.taskLabel.getText());
        }
    }

    @Test
    public void testProvideFeedback() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user input for feedback
            String feedback = "This is feedback";
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(null, "Enter Feedback:"))
                             .thenReturn(feedback);

            // Invoke provideFeedback method
            task.provideFeedback();

            // Verify that the feedback label was updated
            assertEquals(feedback, task.feedbackLabel.getText());
        }
    }

    @Test
    public void testProvideFeedbackWithEmptyInput() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user input for empty feedback
            String feedback = "";
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(null, "Enter Feedback:"))
                             .thenReturn(feedback);

            // Set an initial value for the feedback label
            String initialFeedback = "No feedback yet";
            task.feedbackLabel.setText(initialFeedback);

            // Invoke provideFeedback method
            task.provideFeedback();

            // Verify that the feedback label was not updated
            assertEquals(initialFeedback, task.feedbackLabel.getText());
        }
    }

    @Test
    public void testProvideFeedbackWithNullInput() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user input for null feedback
            String feedback = null;
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(null, "Enter Feedback:"))
                             .thenReturn(feedback);

            // Set an initial value for the feedback label
            String initialFeedback = "No feedback yet";
            task.feedbackLabel.setText(initialFeedback);

            // Invoke provideFeedback method
            task.provideFeedback();

            // Verify that the feedback label was not updated
            assertEquals(initialFeedback, task.feedbackLabel.getText());
        }
    }

    @Test
    public void testDeleteTask() {
        // Verify that the task panel is initially in the parent panel
        assertEquals(1, parentPanel.getComponentCount());

        // Invoke deleteTask method
        task.deleteTask();

        // Verify that the task panel was removed from the parent panel
        assertEquals(0, parentPanel.getComponentCount());
    }
    
    @Test
    public void testPanelInitialization() {
        JPanel panel = addtask.getPanel();
        assertNotNull(panel);
        assertEquals(null, panel.getLayout());
    }

    @Test
    public void testTaskTextFieldInitialization() {
        JTextField taskTextField = addtask.getTaskTextField();
        assertNotNull(taskTextField);
        assertEquals(70, taskTextField.getBounds().x);
        assertEquals(10, taskTextField.getBounds().y);
        assertEquals(200, taskTextField.getBounds().width);
        assertEquals(20, taskTextField.getBounds().height);
    }

    @Test
    public void testFeedbackTextFieldInitialization() {
        JTextField feedbackTextField = addtask.getFeedbackTextField();
        assertNotNull(feedbackTextField);
        assertEquals(80, feedbackTextField.getBounds().x);
        assertEquals(40, feedbackTextField.getBounds().y);
        assertEquals(190, feedbackTextField.getBounds().width);
        assertEquals(20, feedbackTextField.getBounds().height);
    }

    @Test
    public void testAddButtonInitialization() {
        JButton addButton = addtask.getAddButton();
        assertNotNull(addButton);
        assertEquals("Add Task", addButton.getText());
        assertEquals(10, addButton.getBounds().x);
        assertEquals(70, addButton.getBounds().y);
        assertEquals(120, addButton.getBounds().width);
        assertEquals(30, addButton.getBounds().height);
    }

    @Test
    public void testLabelInitialization() {
        Component[] components = addtask.getPanel().getComponents();
        JLabel taskLabel = null;
        JLabel feedbackLabel = null;

        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().equals("Task: ")) {
                    taskLabel = label;
                } else if (label.getText().equals("Feedback: ")) {
                    feedbackLabel = label;
                }
            }
        }

        assertNotNull(taskLabel);
        assertEquals(10, taskLabel.getBounds().x);
        assertEquals(10, taskLabel.getBounds().y);
        assertEquals(50, taskLabel.getBounds().width);
        assertEquals(20, taskLabel.getBounds().height);

        assertNotNull(feedbackLabel);
        assertEquals(10, feedbackLabel.getBounds().x);
        assertEquals(40, feedbackLabel.getBounds().y);
        assertEquals(70, feedbackLabel.getBounds().width);
        assertEquals(20, feedbackLabel.getBounds().height);
    }

    @Test
    public void testComponentsAddedToPanel() {
        Component[] components = addtask.getPanel().getComponents();
        assertEquals(5, components.length);
    }
    @Test
    public void testClearAllTasks() {
        // Add some tasks to the task panel
        Main.taskPanel.add(new JLabel("Task 1"));
        Main.taskPanel.add(new JLabel("Task 2"));
        Main.taskPanel.add(new JLabel("Task 3"));

        // Verify tasks are added
        assertEquals(3, Main.taskPanel.getComponentCount());

        // Invoke clearAllTasks method
        Main.clearAllTasks();

        // Verify that all tasks are removed
        assertEquals(0, Main.taskPanel.getComponentCount());
    }
    
    @Test
    public void testAddNewTask() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            // Mock user input for the task text
            String taskText = "New Task";
            Addtask addTask = new Addtask();

            // Set up the text field with the mock text
            addTask.getTaskTextField().setText(taskText);

            // Mock the JOptionPane to return OK_OPTION when shown
            mockedJOptionPane.when(() -> JOptionPane.showConfirmDialog(
                    null, 
                    addTask.getPanel(), 
                    "Create New Task", 
                    JOptionPane.OK_CANCEL_OPTION
            )).thenReturn(JOptionPane.OK_OPTION);

            // Replace Addtask creation in the method with the mocked instance
            Main.addnewTask(addTask);

            // Verify that the task was added to the task panel
            assertEquals(1, Main.taskPanel.getComponentCount());
            JPanel addedTaskPanel = (JPanel) Main.taskPanel.getComponent(0);
            JLabel taskLabel = (JLabel) addedTaskPanel.getComponent(1);
            assertEquals(taskText, taskLabel.getText());
        }
    }

    @Test
    public void testAddNewTaskCancelled() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user cancelling the dialog
            Addtask addTask = new Addtask();
            mockedJOptionPane.when(() -> JOptionPane.showConfirmDialog(
                    null, 
                    addTask.getPanel(), 
                    "Create New Task", 
                    JOptionPane.OK_CANCEL_OPTION
            )).thenReturn(JOptionPane.CANCEL_OPTION);

            // Invoke addnewTask method
            Main.addnewTask(addTask);

            // Verify that no task was added to the task panel
            assertEquals(0, Main.taskPanel.getComponentCount());
        }
    }

    @Test
    public void testAddNewTaskEmptyText() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Mock user input for the task text
            String taskText = "";
            Addtask addTask = new Addtask();
            mockedJOptionPane.when(() -> JOptionPane.showConfirmDialog(
                    null, 
                    addTask.getPanel(), 
                    "Create New Task", 
                    JOptionPane.OK_CANCEL_OPTION
            )).thenReturn(JOptionPane.OK_OPTION);

            // Mock the text field input
            addTask.getTaskTextField().setText(taskText);

            // Invoke addnewTask method
            Main.addnewTask(addTask);

            // Verify that no task was added to the task panel
            assertEquals(0, Main.taskPanel.getComponentCount());
        }
    }
    
    @Test
    public void testChatMessageConstructor() {
        String sender = "John";
        String message = "Hello, world!";
        ChatMessage chatMessage = new ChatMessage(sender, message);
        
        assertNotNull(chatMessage);
        assertEquals(sender, chatMessage.getSender());
        assertEquals(message, chatMessage.getMessage());
    }

    @Test
    public void testGetSender() {
        String sender = "John";
        String message = "Hello, world!";
        ChatMessage chatMessage = new ChatMessage(sender, message);
        
        assertEquals(sender, chatMessage.getSender());
    }

    @Test
    public void testGetMessage() {
        String sender = "John";
        String message = "Hello, world!";
        ChatMessage chatMessage = new ChatMessage(sender, message);
        
        assertEquals(message, chatMessage.getMessage());
    }

    @Test
    public void testToString() {
        String sender = "John";
        String message = "Hello, world!";
        ChatMessage chatMessage = new ChatMessage(sender, message);
        
        String expected = sender + ": " + message;
        assertEquals(expected, chatMessage.toString());
    }
}
