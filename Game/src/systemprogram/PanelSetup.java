package systemprogram;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;

import javax.swing.*;
import Maze.*;
import enums.*;
import globals.*;
import helpers.*;

public class PanelSetup {
	/*
	 * This class is used to set up the view. It displays details that can be
	 * useful to the user. It also adds the button functionality, i.e, the
	 * ability to change the maze, play, restart and pause the system
	 */

	private static JButton SaveButton;
	private static JButton LoadButton;
	private static JButton BucketMethodButton;
	private static JButton TournamentMethodButton;
	private static JButton OptionsFrame;
	private static JButton InfoFrame;

	private static JLabel speedLbl;
	private static JLabel mazeLbl;
	private static JLabel algorithmLbl;
	private static JLabel generationLbl;
	private static JLabel geneLbl;
	private static JLabel avgFitnessLbl;
	private static JLabel maxFitnessLbl;

	private static JLabel fitnessMethodLbl;
	private static JLabel mazeSwitchingLbl;
	public static JFrame SetupFrame(Screen s) {
		JFrame frame = new JFrame();
		JPanel p = new JPanel();
		s.setLayout(new GridBagLayout());
		p.add(s, BorderLayout.NORTH);
		frame.setTitle("AI Training in Games Software -- GitHub: daniakarick -- Danielle Potts -- CompSci");
		frame.setSize(SystemConfigs.FRAME_WIDTH, SystemConfigs.FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(SouthPanel(), BorderLayout.SOUTH);
		frame.getContentPane().add(EastPanel(), BorderLayout.EAST);
		frame.getContentPane().add(WestPanel(), BorderLayout.WEST);
		JFrame tournamentFrame = CrossoverInfoFrame("Tournament", FileNames.TOURNAMENT_INSTRUCTIONS,
				FileNames.TOURNAMENT_CROSSOVER_DIAGRAM);
		JFrame bucketFrame = CrossoverInfoFrame("Bucket", FileNames.BUCKET_INSTRUCTIONS,
				FileNames.BUCKET_CROSSOVER_DIAGRAM);
		JFrame optionsFrame = OptionsUI();
		JFrame projectInfoFrame = ProjectInfoFrame();

		tournamentFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SystemConfigs.Pause = false;
				SystemConfigs.Play = true;
			}
		});
		bucketFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SystemConfigs.Pause = false;
				SystemConfigs.Play = true;
			}
		});
		optionsFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SystemConfigs.Pause = false;
				SystemConfigs.Play = true;
			}
		});
		projectInfoFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				SystemConfigs.Pause = false;
				SystemConfigs.Play = true;
			}
		});
		InfoFrame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				projectInfoFrame.setVisible(true);
				SystemConfigs.Pause = true;
			}
		});
		TournamentMethodButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tournamentFrame.setVisible(true);
				SystemConfigs.Pause = true;
			}
		});
		BucketMethodButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bucketFrame.setVisible(true);
				SystemConfigs.Pause = true;
			}
		});

		OptionsFrame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				optionsFrame.setVisible(true);
				SystemConfigs.Pause = true;
			}
		});
		frame.getContentPane().add(s);
		frame.setVisible(true);
		frame.setResizable(false);
		SetupSaveLoad();
		return frame;
	}

	private static void SetupLabels(){
		speedLbl = new JLabel("Speed: " + SystemConfigs.SystemSpeed);
		mazeLbl = new JLabel("Current maze: " + SystemConfigs.currentMaze.toString());
		algorithmLbl = new JLabel("Current Algorithm: " + SystemConfigs.currAlgorithm);
		generationLbl = new JLabel("Generation: " + AlgorithmSettings.Generation);
		geneLbl = new JLabel("Current gene: " + AlgorithmSettings.CurrentGene);
		DecimalFormat df = new DecimalFormat("0.000");
		avgFitnessLbl = new JLabel(AlgorithmSettings.Generation == 1 ? "Average Fitness: N/A"
				: "Average Fitness: " + df.format(AlgorithmSettings.AverageFitness));
		maxFitnessLbl = new JLabel(AlgorithmSettings.Generation == 1 ? "Best Fitness: N/A"
				: "Best Fitness: " + df.format(AlgorithmSettings.BestFitness));

		fitnessMethodLbl = new JLabel("Fitness method: " + SystemConfigs.ChosenMethod.toString());
		mazeSwitchingLbl = new JLabel("Maze Switching: " + Boolean.toString(SystemConfigs.MazeSwitching));
	}
	private static JPanel SouthPanel() {
		JComboBox cboBox = new JComboBox();
		List<String> mazes = GetEnums(true, false);
		for (String maze : mazes) {
			cboBox.addItem(maze);
		}

		cboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (MazeSelection.valueOf((String) cboBox.getSelectedItem()) != SystemConfigs.currentMaze) {
					SystemConfigs.currentMaze = MazeSelection.valueOf((String) cboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null,
							"New maze selected. The maze will change on the next gene iteration.");
					mazeLbl.setText("Current maze: " + SystemConfigs.currentMaze.toString());
					SystemConfigs.ResetMaze = true;
				}
			}
		});

		cboBox.setEditable(false);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 20;
		gc.gridy = 10;
		panel.add(cboBox, gc);
		gc.gridx = -20;
		gc.gridy = 10;
		gc.insets = new Insets(10, 10, 10, 10);

		JButton Pause = new JButton("Pause");
		JButton Play = new JButton("Play");
		JButton Restart = new JButton("Restart");
		JButton EndGeneCycle = new JButton("End Gene Cycle");
		JButton slowDown = new JButton("Speed--");
		JButton speedUp = new JButton("Speed++");
		gc.gridx = 1;
		panel.add(Pause, gc);
		gc.gridx = -2;
		panel.add(Play, gc);
		gc.gridx = 15;
		panel.add(Restart, gc);
		gc.gridx = 16;
		panel.add(EndGeneCycle, gc);
		gc.gridy = 10;
		gc.gridx = 40;
		panel.add(slowDown, gc);
		gc.gridx = 42;
		panel.add(speedUp, gc);

		speedUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (SystemConfigs.SystemSpeed != 5) {
					SystemConfigs.SystemSpeed -= 10;
					UpdateLabels();
				}
			}
		});
		slowDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (SystemConfigs.SystemSpeed != 125) {
					SystemConfigs.SystemSpeed += 10;
					UpdateLabels();
				}
			}
		});
		SetupPlayStopPauseButton(Play, Pause, Restart, EndGeneCycle);
		return panel;
	}

	private static JPanel EastPanel() {
		SetupLabels();
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(10, 10, 10, 10);
		gc.gridy = 210;
		panel.add(new JLabel("Mutation Rate: " + AlgorithmSettings.MutationRate), gc);
		gc.gridy = 200;
		panel.add(avgFitnessLbl, gc);
		gc.gridy = 220;
		panel.add(maxFitnessLbl, gc);
		gc.gridy = 230;
		panel.add(generationLbl, gc);
		gc.gridy = 240;
		panel.add(new JLabel("Population Size: " + AlgorithmSettings.PopSize), gc);
		gc.gridy = 260;
		panel.add(geneLbl, gc);
		gc.gridy = 280;
		panel.add(mazeLbl, gc);
		gc.gridy = 290;
		panel.add(algorithmLbl, gc);
		gc.gridy = 310;
		panel.add(speedLbl, gc);
		gc.gridy = 350;
		InfoFrame = new JButton("Project Info");
		panel.add(InfoFrame, gc);
		gc.gridy = 450;
		panel.add(fitnessMethodLbl,gc);
		gc.gridy = 460;
		panel.add(mazeSwitchingLbl,gc);
		return panel;
	}

	public static void UpdateLabels() {
		DecimalFormat df = new DecimalFormat("0.000");
		avgFitnessLbl.setText(AlgorithmSettings.Generation == 1 ? "Average Fitness: N/A"
				: "Average Fitness: " + df.format(AlgorithmSettings.AverageFitness));
		maxFitnessLbl.setText(AlgorithmSettings.Generation == 1 ? "Best Fitness: N/A"
				: "Best Fitness: " + df.format(AlgorithmSettings.BestFitness));
		generationLbl.setText("Generation: " + AlgorithmSettings.Generation);
		geneLbl.setText("Current gene: " + AlgorithmSettings.CurrentGene);
		speedLbl.setText("Speed: " + SystemConfigs.SystemSpeed);
		fitnessMethodLbl.setText("Fitness method: " + SystemConfigs.ChosenMethod.toString());
		mazeSwitchingLbl.setText("Maze Switching: " + Boolean.toString(SystemConfigs.MazeSwitching));
	}

	private static List<String> GetEnums(boolean mazeEnums, boolean crossoverAlgorithms) {
		List<String> result = new ArrayList<>();
		if (mazeEnums) {
			for (MazeSelection m : MazeSelection.class.getEnumConstants()) {
				result.add(m.toString());
			}
		} else {
			for (CrossoverAlgorithms c : CrossoverAlgorithms.class.getEnumConstants()) {
				result.add(c.toString());
			}
		}

		return result;
	}

	private static void SetupPlayStopPauseButton(JButton play, JButton pause, JButton restart, JButton EndGeneCycle) {
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SystemConfigs.Pause = false;
				SystemConfigs.Play = true;
			}
		});

		pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SystemConfigs.Pause = true;
				SystemConfigs.Play = false;
			}
		});

		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SystemConfigs.Restart = true;
				SystemConfigs.Play = false;
				SystemConfigs.Pause = false;
			}
		});

		EndGeneCycle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SystemConfigs.EndGeneCycle = true;
				SystemConfigs.Pause = false;
			}
		});
	}

	private static JPanel WestPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(5, 10, 10, 10);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridx = 15;
		gc.gridy = 16;
		List<String> data = GetEnums(false, true);
		JComboBox algorithms = new JComboBox();
		for (int i = 0; i < data.size(); i++) {
			algorithms.addItem(data.get(i));
		}

		gc.gridy = 13;
		panel.add(new JLabel("Crossover Algorithms"), gc);
		gc.gridy = 14;
		panel.add(algorithms, gc);

		gc.gridy = 15;

		algorithms.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SystemConfigs.currAlgorithm = CrossoverAlgorithms.valueOf((String) algorithms.getSelectedItem());
				algorithmLbl.setText("Current algorithm: " + SystemConfigs.currAlgorithm);
				JOptionPane.showMessageDialog(null,
						"Algorithm was changed. Process will occur during crossover period.");
			}
		});

		SaveButton = new JButton("Save Population");
		LoadButton = new JButton("Load Population");
		BucketMethodButton = new JButton("Bucket Method");
		TournamentMethodButton = new JButton("Tournament Method");
		OptionsFrame = new JButton("Options");
		JLabel explain = new JLabel("Explainations");
		gc.gridy = 40;
		panel.add(SaveButton, gc);
		gc.gridy = 41;
		panel.add(LoadButton, gc);
		gc.gridy = 60;
		panel.add(explain, gc);
		gc.gridy = 61;
		panel.add(BucketMethodButton, gc);
		gc.gridy = 62;
		panel.add(TournamentMethodButton, gc);
		gc.gridy = 90;
		panel.add(OptionsFrame, gc);
		return panel;
	}

	private static JFrame CrossoverInfoFrame(String title, String instructionsFile, String diagramFile) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel labelPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		String[] instructions = TextFileOutput.ReadInFile(instructionsFile);
		int y = 10;
		for (int i = 0; i < instructions.length; i++) {
			if (i != 0) {
				y += 20;
			}

			gc.gridy = y;
			labelPanel.add(new JLabel(instructions[i]), gc);
		}
		panel.add(new JLabel("", ImageHelper.GetImageResize(diagramFile, 400, 400), JLabel.CENTER));
		panel.setBackground(Color.white);
		frame.getContentPane().add(labelPanel, BorderLayout.SOUTH);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		frame.setSize(600, 500);

		frame.setFocusable(true);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);

		frame.setTitle(title);
		return frame;
	}

	private static JFrame OptionsUI() {
		JFrame frame = new JFrame();
		frame.setSize(300, 200);
		frame.setFocusable(true);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new GridBagLayout());

		JRadioButton mazeSwitchOn = new JRadioButton("On");
		JRadioButton mazeSwitchOff = new JRadioButton("Off");

		JRadioButton manhattanDistance = new JRadioButton("Manhattan Distance");
		JRadioButton euclideanDistance = new JRadioButton("Euclidean Distance");
		JRadioButton standardFitnessMethod = new JRadioButton("Standard");
		JRadioButton comboMethods = new JRadioButton("Combo");

		ButtonGroup distanceButtons = new ButtonGroup();

		switch (SystemConfigs.ChosenMethod) {
		case Manhattan:
			manhattanDistance.setSelected(true);
			break;
		case Euclidean:
			euclideanDistance.setSelected(true);
			break;
		case Default:
			standardFitnessMethod.setSelected(true);
			break;
		case Combo:
			comboMethods.setSelected(true);
			break;
		}

		if (SystemConfigs.MazeSwitching) {
			mazeSwitchOn.setSelected(true);
		} else {
			mazeSwitchOff.setSelected(true);
		}

		distanceButtons.add(manhattanDistance);
		distanceButtons.add(euclideanDistance);
		distanceButtons.add(standardFitnessMethod);
		distanceButtons.add(comboMethods);

		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 25;
		gc.gridy = 19;
		leftPanel.add(new JLabel("Fitness Method:"), gc);
		gc.gridy = 20;
		leftPanel.add(manhattanDistance, gc);
		gc.gridy = 21;
		leftPanel.add(euclideanDistance, gc);
		gc.gridy = 22;
		leftPanel.add(standardFitnessMethod, gc);
		gc.gridy = 23;
		leftPanel.add(comboMethods, gc);

		ButtonGroup mazeSwitching = new ButtonGroup();

		mazeSwitching.add(mazeSwitchOff);
		mazeSwitching.add(mazeSwitchOn);
		gc.gridy = 19;
		rightPanel.add(new JLabel("Maze Switching:"), gc);
		gc.gridy = 20;
		rightPanel.add(mazeSwitchOff, gc);
		gc.gridy = 21;
		rightPanel.add(mazeSwitchOn, gc);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(leftPanel, BorderLayout.WEST);

		JButton saveOptions = new JButton("Save");

		saveOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (manhattanDistance.isSelected()) {
					SystemConfigs.ChosenMethod = FitnessMethods.Manhattan;
				} else if (euclideanDistance.isSelected()) {
					SystemConfigs.ChosenMethod = FitnessMethods.Euclidean;
				} else if (standardFitnessMethod.isSelected()) {
					SystemConfigs.ChosenMethod = FitnessMethods.Default;
				} else if (comboMethods.isSelected()) {
					SystemConfigs.ChosenMethod = FitnessMethods.Combo;
				}

				if (mazeSwitchOn.isSelected()) {
					SystemConfigs.MazeSwitching = true;
				} else if (mazeSwitchOff.isSelected()) {
					SystemConfigs.MazeSwitching = false;
				}

				SystemConfigs.Pause = false;
				JOptionPane.showMessageDialog(null, "Options saved.");
				frame.setVisible(false);
				UpdateLabels();
			}
		});

		JPanel southPanel = new JPanel();
		southPanel.add(saveOptions);
		frame.add(southPanel, BorderLayout.SOUTH);
		return frame;
	}
	
	private static void SetupSaveLoad(){
		SerializingHelper sHelper = new SerializingHelper();
		SaveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				sHelper.SavePopulation(SystemConfigs.currAlgorithm, SystemConfigs.saveablePopulation);
				JOptionPane.showMessageDialog(null, "Population saved.");
			}
		});
		LoadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				SystemConfigs.LoadedPopulation = sHelper.LoadPopulation(SystemConfigs.currAlgorithm);
				JOptionPane.showMessageDialog(null, "Population loaded.");
			}
		});
	}
	
	private static JFrame ProjectInfoFrame() {
		TextFileOutput tOutput = new TextFileOutput();
		String[] projectInfo = TextFileOutput.ReadInFile(FileNames.PROJECT_INFO);

		JFrame frame = new JFrame();
		JPanel infoPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		frame.setSize(1500, 250);

		frame.setFocusable(true);
		frame.setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setTitle("Project info");

		gc.gridx = 250;
		gc.gridy = 0;

		for (int i = 0; i < projectInfo.length; i++) {
			infoPanel.add(new JLabel(projectInfo[i]), gc);
			gc.gridy += 1;
		}

		frame.add(infoPanel);

		return frame;
	}
}