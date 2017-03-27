package Maze;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import globals.*;

public class Screen extends JPanel implements ActionListener {
	private Timer timer;
	public Map map;
	public Maze maze;
	public Player player; //the player is the current gene that is running at a given time
	public GameController gameController;
	private MazeGenerator mazeGen;

	public Screen() {
		Init();
	}

	public void Init() {
		mazeGen = new MazeGenerator();
		maze = mazeGen.GetMaze(SystemConfigs.currentMaze);
		map = new Map(maze);
		player = new Player(maze.PlayerStartX, maze.PlayerStartY);
		gameController = new GameController(player, map);
		SetupScreen();
	}
	
	public void Reset(){
		player = new Player(maze.PlayerStartX,maze.PlayerStartY);
		map = new Map(maze);
		gameController = new GameController(player,map);
	}
	
	private void SetupScreen(){
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		setLayout(new GridBagLayout());
		addKeyListener(gameController);
		setFocusable(true);
		timer = new Timer(25, this);
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		for (int y = 0; y < maze.YSize; y++) {
			for (int x = 0; x < maze.XSize; x++) {
				if (map.GetMap(x, y).equals("-")) {
					g.drawImage(map.GetBackground(), x * 32, y * 32, null);
				}
				if (map.GetMap(x, y).equals("w")) {
					g.drawImage(map.GetWall(), x * 32, y * 32, null);
				}
			}
		}

		g.drawImage(player.GetPlayer(), player.GetTileX() * 32, player.GetTileY() * 32, null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
}
