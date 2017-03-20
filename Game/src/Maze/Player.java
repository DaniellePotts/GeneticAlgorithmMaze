package Maze;

import java.awt.Image;

import globals.FileNames;
import helpers.ImageHelper;

public class Player {
	public Image PlayerSprite;
	private int TilePositionX, TilePositionY;

	public Player(int tileX, int tileY) {		
		PlayerSprite = ImageHelper.GetImageResource(FileNames.PLAYER_DOWN_GRAPHIC_FILE_PATH);  //default

		TilePositionX = tileX;
		TilePositionY = tileY;
	}

	public void SetTileX(int x) {
		TilePositionX = x;
	}

	public void SetTileY(int y) {
		TilePositionY = y;
	}

	public Image GetPlayer() {
		return PlayerSprite;
	}

	public int GetTileX() {
		return TilePositionX;
	}

	public int GetTileY() {
		return TilePositionY;
	}

	public void Move(int dx, int dy) {
		TilePositionX += dx;
		TilePositionY += dy;
	}
}
