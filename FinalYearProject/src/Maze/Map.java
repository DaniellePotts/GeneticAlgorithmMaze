package Maze;

import java.awt.Image;
import java.io.File;
import java.util.Scanner;

import globals.FileNames;
import helpers.ImageHelper;

public class Map {
	private Scanner scanner;
	private String Map[];

	private Image background, wall;

	public Maze maze;

	public Map(Maze maze) {
		this.background = ImageHelper.GetImageResource(FileNames.TILE_GRAPHIC_FILE_PATH);
		this.wall = ImageHelper.GetImageResource(FileNames.WALL_GRAPHIC_FILE_PATH);
		this.maze = maze;
		Map = new String[maze.YSize];
		OpenFile();
		ReadFile();
		scanner.close();
	}

	public int GetMazeStartX() {
		return maze.PlayerStartX;
	}

	public int GetMazeStartY() {
		return maze.PlayerStartY;
	}
	
	public int GetMazeEndX(){
		return maze.EndXPosition;
	}
	
	public int GetMazeEndY(){
		return maze.EndYPosition;
	}

	public Image GetBackground() {
		return background;
	}

	public Image GetWall() {
		return wall;
	}

	public String GetMap(int x, int y) {
		return Map[y].substring(x, x + 1);
	}

	public void OpenFile() {
		try {
			scanner = new Scanner(new File(maze.MazeFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ReadFile() {
		while (scanner.hasNext()) {
			for (int i = 0; i < maze.YSize; i++) {
				Map[i] = scanner.next();
			}
		}
	}
}
