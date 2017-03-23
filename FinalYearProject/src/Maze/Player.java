package Maze;

import java.awt.Image;

import geneticalgorithm.Gene;
import globals.FileNames;
import helpers.ImageHelper;

public class Player extends Gene {
	public Image PlayerSprite;

	public Player(int tileX, int tileY) {		
		PlayerSprite = ImageHelper.GetImageResource(FileNames.PLAYER_DOWN_GRAPHIC_FILE_PATH);  //default
		this.x = tileX;
		this.y = tileY;
	}

	public void SetTileX(int x) {
		this.x = x;
	}

	public void SetTileY(int y) {
		this.y = y;
	}

	public Image GetPlayer() {
		return PlayerSprite;
	}

	public int GetTileX() {
		return this.x;
	}

	public int GetTileY() {
		return this.y;
	}

	public void Move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
}
