package testingInterface;

import java.awt.BorderLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestRun {
	public static void main(String[] args) {
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Test Frame");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);

		TestContainer containedTests = new TestContainer();
		TestDisplay viewer = new TestDisplay(containedTests.getTests());
		

		top_panel.add(viewer, BorderLayout.CENTER);

		main_frame.pack();
		main_frame.setVisible(true);
		
	}
}