package systemprogram;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import Maze.*;
import geneticalgorithm.*;
import globals.*;
import helpers.TextFileOutput;

public class ControlAI {

	private Player player;
	private Map map;
	private GameController controller;

	private boolean terminate = false;
	private List<Coords> coords = null;
	private String endReason = "";

	public ControlAI(Player p, Map m, GameController gc) {
		this.player = p;
		this.map = m;
		this.controller = gc;
	}

	public void RunAI(Gene gene) throws InterruptedException {
		Thread.sleep(1 * SystemConfigs.SystemSpeed);
		while (!terminate) {
			while (SystemConfigs.Pause) {
				System.out.println("System paused");
			}

			if (SystemConfigs.Restart) {
				terminate = true;
				break;
			}

			if (SystemConfigs.EndGeneCycle) {
				terminate = true;
				SystemConfigs.EndGeneCycle = !SystemConfigs.EndGeneCycle;
				break;
			}
			CheckCoordsCount();
			if (player.GetTileX() == map.maze.EndXPosition && player.GetTileY() == map.maze.EndYPosition) {
				endReason = "Gene:" + gene.ID + " of generation " + AlgorithmSettings.Generation + " reached the end!";
				TextFileOutput tOutput = new TextFileOutput();
				tOutput.Write(gene, map.maze);
				terminate = true;
			} else {
				String pos = GetPosition();
				String nextMovement = null;

				if (coords == null) {
					coords = new ArrayList<>();

					Coords newCoords = new Coords();
					newCoords.x = player.GetTileX();
					newCoords.y = player.GetTileY();
					newCoords.count = 1;
					coords.add(newCoords);

				} else {
					boolean found = false;

					for (int i = 0; i < coords.size(); i++) {
						if (coords.get(i).x == player.GetTileX() && coords.get(i).y == player.GetTileY()) {
							coords.get(i).count++;
							found = true;
						}
					}
					if (!found) {
						Coords newCoords = new Coords();
						newCoords.x = player.GetTileX();
						newCoords.y = player.GetTileY();
						newCoords.count = 1;
						coords.add(newCoords);
					}
				}

				for (PossibleMovements ps : gene.possibleMovements) {
					if (gene.LastMovement == null) {
						if (ps.Possibility.equals(pos) && ps.PreviousMovement.equals("D")) {
							nextMovement = ps.Movement;
						}
					} else if (ps.Possibility.equals(pos) && ps.PreviousMovement.equals(gene.LastMovement)) {
						nextMovement = ps.Movement;
					}
				}

				SendMovement(nextMovement);
				gene.LastMovement = nextMovement;
				CheckCoordsCount();
			}
		}

		gene.x = player.GetTileX();
		gene.y = player.GetTileY();

		System.out.println("End of cycle. Reason for ending: " + endReason);
		terminate = false;
		coords = null;
	}

	private void SendMovement(String nextMovement) throws InterruptedException {
		KeyEvent ke = null;
		Button d = new Button("click");
		if (nextMovement != null) {
			if (nextMovement.equals("L")) {
				ke = new KeyEvent(d, 1, 20, 1, 65, 'A');
			} else if (nextMovement.equals("R")) {
				ke = new KeyEvent(d, 1, 20, 1, 68, 'D');
			} else if (nextMovement.equals("U")) {
				ke = new KeyEvent(d, 1, 20, 1, 87, 'W');
			} else if (nextMovement.equals("D")) {
				ke = new KeyEvent(d, 1, 20, 1, 83, 'S');
			}

			controller.keyPressed(ke);
			controller.keyReleased(ke);

			Thread.sleep(1 * SystemConfigs.SystemSpeed);
		}
	}

	private String GetPosition() {
		String soroundings = "";
		if (!map.GetMap(player.GetTileX() - 1, player.GetTileY()).equals("w")) {
			soroundings = "S";
		} else {
			soroundings = "L";
		}
		if (!map.GetMap(player.GetTileX() + 1, player.GetTileY()).equals("w")) {
			soroundings += "S";
		} else {
			soroundings += "R";
		}
		if (player.GetTileX() == map.GetMazeStartX() && player.GetTileY() == map.GetMazeStartY()) {
			soroundings += "U";
		} else if (!map.GetMap(player.GetTileX(), player.GetTileY() - 1).equals("w")) {
			soroundings += "S";
		} else {
			soroundings += "U";
		}
		if (player.GetTileY() == map.GetMazeEndX() && player.GetTileX() == map.GetMazeEndY()) {
			soroundings += "D";
		} else if (!map.GetMap(player.GetTileX(), player.GetTileY() + 1).equals("w")) {
			soroundings += "S";
		} else {
			soroundings += "D";
		}

		return soroundings;
	}

	private void CheckCoordsCount() {
		if (coords == null) {
			coords = new ArrayList<>();
		}
		for (int i = 0; i < coords.size(); i++) {
			if (coords.get(i).count == AlgorithmSettings.DupliacateLocationLimit) {
				endReason = "Player has met this spot " + AlgorithmSettings.DupliacateLocationLimit + " times.";
				terminate = true;
				return;
			}
		}
	}
}

class Coords {
	int x, y, count;
}