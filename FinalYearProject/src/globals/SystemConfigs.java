package globals;

import enums.*;
import geneticalgorithm.Population;

public class SystemConfigs {
	public static boolean Play = true;
	public static boolean Restart = false;
	public static boolean Pause = false;
	public static boolean EndGeneCycle = false;
	public static boolean SwitchMaze = true;
	
	public static final int FRAME_WIDTH = 1110;
	public static final int FRAME_HEIGHT = 700;
	
	public static final int MAZE_CHANGE_RATE = 50;
	public static Population LoadedPopulation = null;
	public static Population saveablePopulation = null;
	public static CrossoverAlgorithms currAlgorithm = CrossoverAlgorithms.Tournament;
	public static MazeSelection currentMaze = MazeSelection.TestMaze;
	
	//core algorithm settings
	public static FitnessMethods ChosenMethod = FitnessMethods.Default;
	public static boolean MazeSwitching = true;
	
	public static int SystemSpeed = 95; //default speed. the maze speed is 125. min is 5.
	
	public static boolean ResetMaze = false;
}
