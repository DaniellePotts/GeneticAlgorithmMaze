package tests;

import static org.junit.Assert.*;
import java.awt.Image;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import Maze.*;
import enums.MazeSelection;
import geneticalgorithm.*;
import globals.FileNames;
import helpers.*;

public class GeneralTests {

	@Test
	public void TestPlayerSpritePath() {
		Image img = ImageHelper.GetImageResource(FileNames.PLAYER_DOWN_GRAPHIC_FILE_PATH);
		assertNotNull(img);
	}

	@Test
	public void TestWallSpritePath() {
		Image img = ImageHelper.GetImageResource(FileNames.WALL_GRAPHIC_FILE_PATH);
		assertNotNull(img);
	}

	@Test
	public void TestBackgroundSpritePath() {
		Image img = ImageHelper.GetImageResource(FileNames.BACKGROUND_GRAPHIC_FILE_PATH);
		assertNotNull(img);
	}

	@Test
	public void ReadInMazes() {
		String[] filePaths = new String[] { FileNames.TEST_MAZE_1, FileNames.TEST_MAZE_2,
				FileNames.TRAINING_MAZE_1, FileNames.TRAINING_MAZE_2, FileNames.TRAINING_MAZE_2,
				FileNames.TRAINING_MAZE_3 };
		boolean pathsOk = false;
		try {
			Scanner scanner = null;
			for (int i = 0; i < filePaths.length; i++) {
				scanner = new Scanner(new File(filePaths[i]));

				if (scanner != null && scanner.hasNext()) {
					pathsOk = true;
				} else {
					pathsOk = false;
				}
			}

			assertTrue(pathsOk);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestFileReader() {
		LoadPossibitilies lPos = new LoadPossibitilies();
		List<PossibleMovements> pMoves = lPos.CreatePossibleMovements();
		assertEquals(pMoves.size(), 64);
	}

	@Test
	public void TestSerializer() {

		int popSize = 10;
		Population pop = new Population(popSize);
		SerializingHelper sHelper = new SerializingHelper();
		sHelper.SavePopulation(null, pop);
		assertNotNull(pop.Genes);
	}

	@Test
	public void TestTextFileCode() {
		boolean finished = false;
		try {
			TextFileOutput textOutput = new TextFileOutput();
			Gene g = new Gene();
			MazeGenerator mG = new MazeGenerator();
			g.ID = 5;
			g.Fitness = 100;
			textOutput.Write(g,mG.GetMaze(MazeSelection.TestMaze));
			finished = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(true, finished);
	}

}
