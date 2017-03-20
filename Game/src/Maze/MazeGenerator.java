package Maze;

import java.util.*;
import java.util.List;
import enums.MazeSelection;
import globals.*;

public class MazeGenerator {

	private Maze InitTestMaze() {
		Maze m = new Maze();
		m.MazeFileName = FileNames.TEST_MAZE_1;
		m.PlayerStartX = 7;
		m.PlayerStartY = 0;
		m.XSize = 13;
		m.YSize = 11;
		m.EndXPosition = 7;
		m.EndYPosition = 10;
		return m;
	}

	private Maze InitTrainingMaze1() {
		Maze m = new Maze();
		m.MazeFileName = FileNames.TRAINING_MAZE_1;
		m.PlayerStartX = 8;
		m.PlayerStartY = 0;
		m.XSize = 21;
		m.YSize = 17;
		m.EndXPosition = 12;
		m.EndYPosition = 17;
		return m;
	}

	private Maze InitTrainingMaze2() {
		Maze m = new Maze();
		m.MazeFileName = FileNames.TRAINING_MAZE_2;
		m.PlayerStartX = 9;
		m.PlayerStartY = 0;
		m.XSize = 22;
		m.YSize = 16;
		m.EndYPosition = 15;
		m.EndXPosition = 10;
		return m;
	}

	private Maze InitTrainingMaze3() {
		Maze m = new Maze();
		m.MazeFileName = FileNames.TRAINING_MAZE_3;
		m.PlayerStartX = 9;
		m.PlayerStartY = 0;
		m.XSize = 20;
		m.YSize = 14;
		m.EndXPosition = 2;
		m.EndYPosition = 14;
		return m;
	}

	private Maze InitTestMaze2() {
		Maze m = new Maze();
		m.MazeFileName = FileNames.TEST_MAZE_2;
		m.PlayerStartX = 9;
		m.PlayerStartY = 0;
		m.XSize = 22;
		m.YSize = 12;
		m.EndXPosition = 5;
		m.EndYPosition = 11;
		return m;
	}

	private Maze InitTrainingMaze4() {
		Maze m = new Maze();
		m.MazeFileName = FileNames.TRAINING_MAZE_4;
		m.PlayerStartX = 10;
		m.PlayerStartY = 0;
		m.XSize = 23;
		m.YSize = 18;
		m.EndXPosition = 13;
		m.EndYPosition = 16;
		return m;
	}

	private Maze InitTestMaze3() {
		Maze m = new Maze();
		m.MazeFileName = FileNames.TEST_MAZE_3;
		m.PlayerStartX = 7;
		m.PlayerStartY = 0;
		m.XSize = 17;
		m.YSize = 11;
		m.EndXPosition = 7;
		m.EndYPosition = 10;
		return m;
	}

	public Maze GetMaze(MazeSelection m) {
		switch (m) {
		case TestMaze:
			return InitTestMaze();
		case TrainingMaze1:
			return InitTrainingMaze1();
		case TrainingMaze2:
			return InitTrainingMaze2();
		case TrainingMaze3:
			return InitTrainingMaze3();
		case TestMaze2:
			return InitTestMaze2();
		case TrainingMaze4:
			return InitTrainingMaze4();
		case TestMaze3:
			return InitTestMaze3();
		default:
			return null;
		}
	}
	
	public void GetRandomMaze(){
		List<MazeSelection>result = new ArrayList<>();
		for (MazeSelection m : MazeSelection.class.getEnumConstants()) {
			result.add(m);
		}
		MazeSelection newMaze = null;
		Random rnd = new Random();
		do{
			newMaze = result.get(rnd.nextInt(result.size()));
		}while(newMaze == SystemConfigs.currentMaze || newMaze == null);
		
		SystemConfigs.currentMaze = newMaze;
	}
}
