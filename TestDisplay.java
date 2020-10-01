package testingInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.management.modelmbean.ModelMBean;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestDisplay extends JPanel {
	
	private static final int FULL_WIDTH = 980;
	private static final int FULL_HEIGHT = 700;
	
	private static final int HALF_WIDTH = 490;
	private static final int INSET_HEIGHT = 660;
	
	private static final int TITLE_WIDTH = 960;
	private static final int TITLE_HEIGHT = 40;
	
	private static final int TRIAL_LABEL_WIDTH = 60;
	private static final int TITLE_LABEL_WIDTH = 130;
	private static final int VALUE_LABEL_WIDTH = 164;
	private static final int PASSING_LABEL_WIDTH = 96;
	private static final int LABEL_HEIGHT = 20;

	private static final int LABEL_TOTAL_WIDTH = 470;
	private static final int LABEL_TOTAL_HEIGHT = 30;
	
	private static final int SCROLLING_WIDTH = 470;
	private static final int SCROLLING_HEIGHT = 620;
	
	private double singleQuarterGap;
	private Border margin;
	private double singleHalfGap;
	
	private Border blackBorder = BorderFactory.createLineBorder(Color.black);
	private Border grayBorder = BorderFactory.createLineBorder(Color.GRAY);
	private Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	private Border bevelBorder = BorderFactory.createLoweredBevelBorder();
	private Border compound;
	
	private List<Test> allTests;
	private List<Test> failedTests;

	
	private JPanel scrollingAll;
	private JPanel scrollingFailed;
	
	public TestDisplay(List<Test> all) {
		this.allTests = new ArrayList<Test>();
		this.failedTests = new ArrayList<Test>();
	
		this.allTests = all;

		for(int i = 0; i<allTests.size(); i++) {
			Test holder = allTests.get(i);
			if(!holder.getPassed()) {
				failedTests.add(holder);
			}
		}
		
		this.compound = BorderFactory.createCompoundBorder(bevelBorder, new EmptyBorder(4, 10, 4, 10));
				
		setPreferredSize(new Dimension(FULL_WIDTH, FULL_HEIGHT));
		setLayout(new BorderLayout());
		setUpTitlePanel();
		setUpAllPanel();
		setUpFailedPanel();
		updates();
	}
	
	private void setUpTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(TITLE_WIDTH, TITLE_HEIGHT + 14));
		titlePanel.setLayout(new BorderLayout());
		
		JLabel title = new JLabel("TESTING INTERFACE");
		title.setFont(new Font("sansserif", Font.BOLD, 18));
		title.setForeground(Color.BLACK);
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		title.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		title.setBorder(new EmptyBorder(6, 400, 0, 0));
		
		JLabel sub1 = new JLabel("ALL TESTS");
		sub1.setFont(new Font("sansserif", Font.BOLD, 14));
		sub1.setForeground(Color.BLACK);
		sub1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sub1.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		sub1.setBorder(new EmptyBorder(2, 60, 6, 0));
		
		JLabel sub2 = new JLabel("FAILED TESTS");
		sub2.setFont(new Font("sansserif", Font.BOLD, 14));
		sub2.setForeground(Color.BLACK);
		sub2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		sub2.setAlignmentY(JLabel.CENTER_ALIGNMENT);
		sub2.setBorder(new EmptyBorder(2, 0, 6, 60));
		
		titlePanel.add(title, BorderLayout.NORTH);
		titlePanel.add(sub1, BorderLayout.WEST);
		titlePanel.add(sub2, BorderLayout.EAST);
		titlePanel.setBorder(loweredetched);
		this.add(titlePanel, BorderLayout.NORTH);
	}
	
	private void setUpAllPanel() {
		JPanel fullTestingPanel = new JPanel();
		fullTestingPanel.setLayout(new BorderLayout());
		fullTestingPanel.setPreferredSize(new Dimension(HALF_WIDTH, INSET_HEIGHT));
		fullTestingPanel.setBorder(compound);
		
		JPanel labelTitles = new JPanel();
		labelTitles.setPreferredSize(new Dimension(LABEL_TOTAL_WIDTH - 80, LABEL_TOTAL_HEIGHT));
		labelTitles.setLayout(new BoxLayout(labelTitles, BoxLayout.X_AXIS));
		
			JPanel lab1 = new JPanel();
			JPanel lab2 = new JPanel();
			JPanel lab3 = new JPanel();
			JPanel lab4 = new JPanel();
			lab1.setLayout(new BorderLayout());
			lab2.setLayout(new BorderLayout());
			lab3.setLayout(new BorderLayout());
			lab4.setLayout(new BorderLayout());
			lab1.setPreferredSize(new Dimension(TRIAL_LABEL_WIDTH + 8, LABEL_HEIGHT));
			lab2.setPreferredSize(new Dimension(TITLE_LABEL_WIDTH, LABEL_HEIGHT));
			lab3.setPreferredSize(new Dimension(VALUE_LABEL_WIDTH, LABEL_HEIGHT));
			lab4.setPreferredSize(new Dimension(PASSING_LABEL_WIDTH, LABEL_HEIGHT));
			
			JLabel trialLab = new JLabel("Test #:");
			JLabel nameLab = new JLabel("Type/Input:");
			JLabel valsLab = new JLabel("Expected/Actual Vals:");
			JLabel passLab = new JLabel("Test Status:");
			
			trialLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			trialLab.setForeground(Color.BLACK);
			trialLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			trialLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			trialLab.setBorder(new EmptyBorder(10,10,10,10));
			
			nameLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			nameLab.setForeground(Color.BLACK);
			nameLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			nameLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			nameLab.setBorder(new EmptyBorder(10,10,10,10));
			
			valsLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			valsLab.setForeground(Color.BLACK);
			valsLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			valsLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			valsLab.setBorder(new EmptyBorder(10,10,10,10));
			
			passLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			passLab.setForeground(Color.BLACK);
			passLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			passLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			passLab.setBorder(new EmptyBorder(10,10,10,10));
			
			lab1.add(trialLab, BorderLayout.CENTER);
			lab2.add(nameLab, BorderLayout.CENTER);
			lab3.add(valsLab, BorderLayout.CENTER);
			lab4.add(passLab, BorderLayout.CENTER);
			
			lab1.setBorder(blackBorder);
			lab2.setBorder(blackBorder);
			lab3.setBorder(blackBorder);
			lab4.setBorder(blackBorder);
			
			labelTitles.add(lab1);
			labelTitles.add(lab2);
			labelTitles.add(lab3);
			labelTitles.add(lab4);
		
			fullTestingPanel.add(labelTitles, BorderLayout.NORTH);
		
		scrollingAll = new JPanel();
		scrollingAll.setLayout(new BoxLayout(scrollingAll, BoxLayout.Y_AXIS));
		
		JScrollPane scrollingPane = new JScrollPane(scrollingAll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
													JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollingPane.setPreferredSize(new Dimension(SCROLLING_WIDTH, SCROLLING_HEIGHT));
		fullTestingPanel.add(scrollingPane, BorderLayout.CENTER);
		
		this.add(fullTestingPanel, BorderLayout.WEST);
		
	}
	
	private void setUpFailedPanel() {
		JPanel failTestingPanel = new JPanel();
		failTestingPanel.setLayout(new BorderLayout());
		failTestingPanel.setPreferredSize(new Dimension(HALF_WIDTH, INSET_HEIGHT));
		failTestingPanel.setBorder(compound);
		
		JPanel labelTitles = new JPanel();
		labelTitles.setPreferredSize(new Dimension(LABEL_TOTAL_WIDTH - 80, LABEL_TOTAL_HEIGHT));
		labelTitles.setLayout(new BoxLayout(labelTitles, BoxLayout.X_AXIS));
		
			JPanel lab1 = new JPanel();
			JPanel lab2 = new JPanel();
			JPanel lab3 = new JPanel();
			JPanel lab4 = new JPanel();
			lab1.setLayout(new BorderLayout());
			lab2.setLayout(new BorderLayout());
			lab3.setLayout(new BorderLayout());
			lab4.setLayout(new BorderLayout());
			lab1.setPreferredSize(new Dimension(TRIAL_LABEL_WIDTH + 8, LABEL_HEIGHT));
			lab2.setPreferredSize(new Dimension(TITLE_LABEL_WIDTH, LABEL_HEIGHT));
			lab3.setPreferredSize(new Dimension(VALUE_LABEL_WIDTH, LABEL_HEIGHT));
			lab4.setPreferredSize(new Dimension(PASSING_LABEL_WIDTH, LABEL_HEIGHT));
			
			JLabel trialLab = new JLabel("Test #:");
			JLabel nameLab = new JLabel("Type/Input:");
			JLabel valsLab = new JLabel("Expected/Actual Vals:");
			JLabel passLab = new JLabel("Test Status:");
			
			trialLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			trialLab.setForeground(Color.BLACK);
			trialLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			trialLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			trialLab.setBorder(new EmptyBorder(10,10,10,10));
			
			nameLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			nameLab.setForeground(Color.BLACK);
			nameLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			nameLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			nameLab.setBorder(new EmptyBorder(10,10,10,10));
			
			valsLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			valsLab.setForeground(Color.BLACK);
			valsLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			valsLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			valsLab.setBorder(new EmptyBorder(10,10,10,10));
			
			passLab.setFont(new Font("sansserif", Font.ITALIC, 13));
			passLab.setForeground(Color.BLACK);
			passLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			passLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
			passLab.setBorder(new EmptyBorder(10,10,10,10));
			
			lab1.add(trialLab, BorderLayout.CENTER);
			lab2.add(nameLab, BorderLayout.CENTER);
			lab3.add(valsLab, BorderLayout.CENTER);
			lab4.add(passLab, BorderLayout.CENTER);
			
			lab1.setBorder(blackBorder);
			lab2.setBorder(blackBorder);
			lab3.setBorder(blackBorder);
			lab4.setBorder(blackBorder);
			
			labelTitles.add(lab1);
			labelTitles.add(lab2);
			labelTitles.add(lab3);
			labelTitles.add(lab4);
		
			failTestingPanel.add(labelTitles, BorderLayout.NORTH);
		
		scrollingFailed = new JPanel();
		scrollingFailed.setLayout(new BoxLayout(scrollingFailed, BoxLayout.Y_AXIS));
		
		JScrollPane scrollingPane = new JScrollPane(scrollingFailed, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
													JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollingPane.setPreferredSize(new Dimension(SCROLLING_WIDTH, SCROLLING_HEIGHT));
		failTestingPanel.add(scrollingPane, BorderLayout.CENTER);
		this.add(failTestingPanel, BorderLayout.EAST);
		
	}

	private void updates() {
		for(int i=0; i<allTests.size(); i++) {
			Test holder = allTests.get(i);
			String[] holderVals = new String[2];
			holderVals = holder.getInfo();
			
			JPanel oneTestPanel = new JPanel();
			oneTestPanel.setPreferredSize(new Dimension(LABEL_TOTAL_WIDTH, LABEL_TOTAL_HEIGHT));
			oneTestPanel.setLayout(new BoxLayout(oneTestPanel, BoxLayout.X_AXIS));
			
				JPanel lab1 = new JPanel();
				JPanel lab2 = new JPanel();
				JPanel lab3 = new JPanel();
				JPanel lab4 = new JPanel();
				lab1.setLayout(new BorderLayout());
				lab2.setLayout(new GridLayout(2, 1, 1, 1));
				lab3.setLayout(new GridLayout(2, 1, 1, 1));
				lab4.setLayout(new BorderLayout());
				lab1.setPreferredSize(new Dimension(TRIAL_LABEL_WIDTH, LABEL_HEIGHT));
				lab2.setPreferredSize(new Dimension(TITLE_LABEL_WIDTH, LABEL_HEIGHT));
				lab3.setPreferredSize(new Dimension(VALUE_LABEL_WIDTH, LABEL_HEIGHT));
				lab4.setPreferredSize(new Dimension(PASSING_LABEL_WIDTH, LABEL_HEIGHT));
				
				JLabel trialLab = new JLabel("" + holder.getTrial() +".)");
				JLabel nameLab = new JLabel("" + holder.getTitle());
				JLabel valsLabE = new JLabel("Expected: " + holderVals[0]);
				JLabel valsLabA = new JLabel("Actual: " + holderVals[1]);

				JLabel inputLab = new JLabel("");
				if(holder.getInput().equals("none")) {
					inputLab.setText("I: No Input");
				} else {
					inputLab.setText("I: " + holder.getInput());
				}
				
				JLabel passLab = new JLabel("");
				if(holder.getPassed()) {
					passLab.setText("Passed!");
					passLab.setForeground(Color.GREEN);
				} else {
					passLab.setText("Failed!");
					passLab.setForeground(Color.RED);
				}
				
				trialLab.setFont(new Font("monospaced", Font.PLAIN, 12));
				trialLab.setForeground(Color.BLACK);
				trialLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				trialLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				trialLab.setBorder(new EmptyBorder(20,8,20,2));
				
				nameLab.setFont(new Font("monospaced", Font.PLAIN, 12));
				nameLab.setForeground(Color.BLACK);
				nameLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				nameLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				nameLab.setBorder(new EmptyBorder(5,10,1,20));
				
				inputLab.setFont(new Font("monospaced", Font.PLAIN, 12));
				inputLab.setForeground(Color.BLACK);
				inputLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				inputLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				inputLab.setBorder(new EmptyBorder(1,10,5,20));
				
				valsLabE.setFont(new Font("monospaced", Font.PLAIN, 12));
				valsLabE.setForeground(Color.BLACK);
				valsLabE.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				valsLabE.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				valsLabE.setBorder(new EmptyBorder(5,10,1,20));
				
				valsLabA.setFont(new Font("monospaced", Font.PLAIN, 12));
				valsLabA.setForeground(Color.BLACK);
				valsLabA.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				valsLabA.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				valsLabA.setBorder(new EmptyBorder(1,10,5,20));
				
				passLab.setFont(new Font("monospaced", Font.PLAIN, 12));
				passLab.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				passLab.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				passLab.setBorder(new EmptyBorder(20,10,20,10));
				
				lab1.add(trialLab, BorderLayout.CENTER);
				lab2.add(nameLab);
				lab2.add(inputLab);
				lab3.add(valsLabE);
				lab3.add(valsLabA);
				lab4.add(passLab, BorderLayout.CENTER);
				
				lab1.setBorder(blackBorder);
				lab2.setBorder(blackBorder);
				lab3.setBorder(blackBorder);
				lab4.setBorder(blackBorder);
				
				oneTestPanel.add(lab1);
				oneTestPanel.add(lab2);
				oneTestPanel.add(lab3);
				oneTestPanel.add(lab4);
				
			scrollingAll.add(oneTestPanel);
		}
		
		for(int j=0; j<failedTests.size(); j++) {
			Test holderF = failedTests.get(j);
			String[] holderValsF = new String[2];
			holderValsF = holderF.getInfo();
			
			JPanel oneTestPanelF = new JPanel();
			oneTestPanelF.setPreferredSize(new Dimension(LABEL_TOTAL_WIDTH, LABEL_TOTAL_HEIGHT));
			oneTestPanelF.setLayout(new BoxLayout(oneTestPanelF, BoxLayout.X_AXIS));
			
				JPanel lab1F = new JPanel();
				JPanel lab2F = new JPanel();
				JPanel lab3F = new JPanel();
				JPanel lab4F = new JPanel();
				lab1F.setLayout(new BorderLayout());
				lab2F.setLayout(new GridLayout(2, 1, 1, 1));
				lab3F.setLayout(new GridLayout(2, 1, 1, 1));
				lab4F.setLayout(new BorderLayout());
				lab1F.setPreferredSize(new Dimension(TRIAL_LABEL_WIDTH, LABEL_HEIGHT));
				lab2F.setPreferredSize(new Dimension(TITLE_LABEL_WIDTH, LABEL_HEIGHT));
				lab3F.setPreferredSize(new Dimension(VALUE_LABEL_WIDTH, LABEL_HEIGHT));
				lab4F.setPreferredSize(new Dimension(PASSING_LABEL_WIDTH, LABEL_HEIGHT));
				
				JLabel trialLabF = new JLabel("" + holderF.getTrial() +".)");
				JLabel nameLabF = new JLabel("" + holderF.getTitle());
				JLabel valsLabEF = new JLabel("Expected: " + holderValsF[0]);
				JLabel valsLabAF = new JLabel("Actual: " + holderValsF[1]);

				JLabel inputLabF = new JLabel("");
				if(holderF.getInput().equals("none")) {
					inputLabF.setText("I: No Input");
				} else {
					inputLabF.setText("I: " + holderF.getInput());
				}
				
				JLabel passLabF = new JLabel("");
				if(holderF.getPassed()) {
					passLabF.setText("Passed!");
					passLabF.setForeground(Color.GREEN);
				} else {
					passLabF.setText("Failed!");
					passLabF.setForeground(Color.RED);
				}
				
				trialLabF.setFont(new Font("monospaced", Font.PLAIN, 12));
				trialLabF.setForeground(Color.BLACK);
				trialLabF.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				trialLabF.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				trialLabF.setBorder(new EmptyBorder(20,4,10,2));
				
				nameLabF.setFont(new Font("monospaced", Font.PLAIN, 12));
				nameLabF.setForeground(Color.BLACK);
				nameLabF.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				nameLabF.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				nameLabF.setBorder(new EmptyBorder(5,10,1,20));
				
				inputLabF.setFont(new Font("monospaced", Font.PLAIN, 12));
				inputLabF.setForeground(Color.BLACK);
				inputLabF.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				inputLabF.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				inputLabF.setBorder(new EmptyBorder(1,10,5,20));
				
				valsLabEF.setFont(new Font("monospaced", Font.PLAIN, 12));
				valsLabEF.setForeground(Color.BLACK);
				valsLabEF.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				valsLabEF.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				valsLabEF.setBorder(new EmptyBorder(5,10,1,20));
				
				valsLabAF.setFont(new Font("monospaced", Font.PLAIN, 12));
				valsLabAF.setForeground(Color.BLACK);
				valsLabAF.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				valsLabAF.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				valsLabAF.setBorder(new EmptyBorder(1,10,5,20));
				
				passLabF.setFont(new Font("monospaced", Font.PLAIN, 12));
				passLabF.setAlignmentX(JLabel.CENTER_ALIGNMENT);
				passLabF.setAlignmentY(JLabel.CENTER_ALIGNMENT);
				passLabF.setBorder(new EmptyBorder(20,10,20,10));
				
				lab1F.add(trialLabF, BorderLayout.CENTER);
				lab2F.add(nameLabF);
				lab2F.add(inputLabF);
				lab3F.add(valsLabEF);
				lab3F.add(valsLabAF);
				lab4F.add(passLabF, BorderLayout.CENTER);
				
				lab1F.setBorder(blackBorder);
				lab2F.setBorder(blackBorder);
				lab3F.setBorder(blackBorder);
				lab4F.setBorder(blackBorder);
				
				oneTestPanelF.add(lab1F);
				oneTestPanelF.add(lab2F);
				oneTestPanelF.add(lab3F);
				oneTestPanelF.add(lab4F);
				
			scrollingFailed.add(oneTestPanelF);
			
		}
	}


}