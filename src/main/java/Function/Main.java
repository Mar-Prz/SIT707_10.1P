package Function;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main {
	
	//instance variable
	static JPanel taskPanel;

	public static void main(String[] args) {
		//Main frame for program created
		JFrame frame = new JFrame("Ontrack Function");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(null);
		
		//Panel for tasks
		taskPanel = new JPanel();
		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
		//Scroller
		JScrollPane scrollPane = new JScrollPane(taskPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 460, 400);
		
		//Addtask class instance to handle task addition
		Addtask addTask = new Addtask();
		JButton addButton = addTask.getAddButton();
		
		//add ActionListener to Add task button
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addnewTask(addTask);
				
			}
			
		});
		
		//Clear All button and ActionListener to clear tasks
		JButton clearButton = new JButton("Clear All");
		clearButton.setBounds(250, 420, 120, 30);
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearAllTasks();
			}
			
		});
		
		//set and add components to frame
		addButton.setBounds(120, 420, 120, 30);
		frame.add(scrollPane);
		frame.add(clearButton);
		frame.add(addButton);
		
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
	
	//method for adding new tasks
	 static void addnewTask(Addtask addTask) {
	        int result = JOptionPane.showConfirmDialog(null, addTask.getPanel(), "Create New Task", JOptionPane.OK_CANCEL_OPTION);
	        
	        if (result == JOptionPane.OK_OPTION) {
	            String taskText = addTask.getTaskTextField().getText();
	            if (!taskText.isEmpty()) {
	                Task newTask = new Task(taskText, taskPanel);
	                taskPanel.add(newTask.getTaskPanel());
	                taskPanel.revalidate();
	                taskPanel.repaint();
	            }
	        }
	    }
	
	//Clear all tasks method
	static void clearAllTasks() {
		//remove all tasks from task panel
		taskPanel.removeAll();
		taskPanel.revalidate();
		taskPanel.repaint();
	}

}