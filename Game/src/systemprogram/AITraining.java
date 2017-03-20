package systemprogram;

import java.awt.AWTException;
import javax.swing.*;
import Maze.*;
import enums.CrossoverAlgorithms;
import geneticalgorithm.*;
import globals.*;

public class AITraining {
	private static ControlAI controlAI;
	private static Screen screen;
	private static JFrame frame;
	private static PanelSetup panel;
	private static int changeMaze = 1;
	private static Population pop;
	private static boolean popLoaded = false;

	public static void Run() throws AWTException, InterruptedException {
		Setup();
		while (!SystemConfigs.Restart) {
			GameController gc = screen.gameController;
			RestartScreen(gc);
			controlAI = new ControlAI(screen.player, screen.map, gc);
			for (int i = 0; i < pop.PopSize(); i++) {
				popLoaded = false;
				ChangeMaze();
				AlgorithmSettings.CurrentGene = i;
				panel.UpdateLabels();
				controlAI.RunAI(pop.GetGene(i));
				RestartScreen(gc);
				if (SystemConfigs.Restart) {
					break;
				}
				if (SystemConfigs.LoadedPopulation != null) {
					pop = SystemConfigs.LoadedPopulation;
					panel.UpdateLabels();
					popLoaded = true;
					SystemConfigs.LoadedPopulation = null;
					break;
				}
			}

			if (SystemConfigs.Restart) {
				break;
			}
			if (!popLoaded && !SystemConfigs.Restart) {
				Fitness fitness = new Fitness();
				for (int i = 0; i < pop.PopSize(); i++) {
					pop.Genes.get(i).Fitness = fitness.CalcFitness(pop.GetGene(i).x, pop.GetGene(i).y,
							screen.maze.EndXPosition, screen.maze.EndYPosition);
				}

				SystemConfigs.saveablePopulation = pop;
				AlgorithmSettings.BestFitness = fitness.MaxFitness(pop.Genes);
				AlgorithmSettings.AverageFitness = fitness.AvgFitness(pop.Genes);

				if (SystemConfigs.currAlgorithm == CrossoverAlgorithms.BucketMethod) {
					BucketCrossover bc = new BucketCrossover();
					bc.Mate(pop.Genes);
				} else if (SystemConfigs.currAlgorithm == CrossoverAlgorithms.Tournament) {
					TournamentCrossover tc = new TournamentCrossover();
					pop = tc.EvolvePop(pop);
				}

				AlgorithmSettings.Generation++;
				if (SystemConfigs.MazeSwitching) {
					changeMaze++;
				}
				panel.UpdateLabels();
				pop.Generation = AlgorithmSettings.Generation;
				if (pop.Generation == 3000) {
					break;
				}
				frame.add(screen);
			}
		}
		Run();
	}

	private static void RestartScreen(GameController gc) {
		if (SystemConfigs.ResetMaze || SystemConfigs.Restart) {
			frame.dispose();
			screen = new Screen();
			SystemConfigs.ResetMaze = false;
			frame.add(screen);
			frame = PanelSetup.SetupFrame(screen);
		} else {
			screen.Reset();
			frame.add(screen);
			gc = screen.gameController;
		}

		controlAI = new ControlAI(screen.player, screen.map, gc);
	}

	private static void Setup(){
		screen = new Screen();
		frame = PanelSetup.SetupFrame(screen);
		panel = new PanelSetup();
		changeMaze = 1;
		controlAI = new ControlAI(screen.player,screen.map,screen.gameController);
		pop = new Population(AlgorithmSettings.PopSize);
		SystemConfigs.saveablePopulation = pop;
		if(SystemConfigs.Restart){
			RestartScreen(screen.gameController);
			AlgorithmSettings.Generation = 1;
			panel.UpdateLabels();
			SystemConfigs.Restart = false;
		}
	}

	private static void ChangeMaze() {
		if (changeMaze == SystemConfigs.MAZE_CHANGE_RATE && SystemConfigs.MazeSwitching) {
			changeMaze = 0;
			MazeGenerator mg = new MazeGenerator();
			SystemConfigs.ResetMaze = true;
			mg.GetRandomMaze();
		}
	}
}
