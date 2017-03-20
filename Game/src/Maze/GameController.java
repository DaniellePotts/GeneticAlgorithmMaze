package Maze;
import java.awt.event.*;
import helpers.ImageHelper;

public class GameController extends KeyAdapter {
	private Player player;
	private Map map;

	public GameController(Player player, Map map) {
		this.player = player;
		this.map = map;
	}

	public void keyPressed(KeyEvent e) {
		ImageHelper helper = new ImageHelper();
		int keycode = e.getKeyCode();
		player.PlayerSprite = helper.RotateImage(e);
		if (keycode == KeyEvent.VK_W) {
			if (player.GetTileX() == map.GetMazeStartX() && player.GetTileY() == map.GetMazeStartY()) {

			} else if (!map.GetMap(player.GetTileX(), player.GetTileY() - 1).equals("w")) {
				player.Move(0, -1);
			}
		}
		if (keycode == KeyEvent.VK_S) {
			if (!map.GetMap(player.GetTileX(), player.GetTileY() + 1).equals("w")) {
				player.Move(0, 1);
			}
		}
		if (keycode == KeyEvent.VK_D) {
			if (!map.GetMap(player.GetTileX() + 1, player.GetTileY()).equals("w")) {
				player.Move(1, 0);
			}
		}
		if (keycode == KeyEvent.VK_A) {
			if (!map.GetMap(player.GetTileX() - 1, player.GetTileY()).equals("w")) {
				//player.PlayerSprite = ImageHelper.GetImageResource("C://Maze//Player_Left.png");
				player.Move(-1, 0);
				
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}