package geneticalgorithm;

import java.util.List;

import globals.SystemConfigs;

public class Fitness {
	public double StandardFitness(int currX, int currY, int goalX, int goalY) {
		return Math.sqrt((goalX * goalY) + (currX * currY));
	}
	
	public double ManhattanDistance(int currX, int currY, int goalX, int goalY){
		return Math.abs(goalX - currX) + Math.abs(goalY - currY);
	}
	
	public double EuclideanDistance(int currX, int currY, int goalX, int goalY){
		double x = Math.pow((goalX - currX),2);
		double y = Math.pow((goalY - currY),2);
		return Math.sqrt(x+y);
	}
	
	public double CosineDistance(int currX, int currY, int goalX, int goalY){
		double x = (Math.cos(currX) * Math.cos(goalX));
		double y =  + (Math.sin(currY) * Math.sin(goalY));
		return Math.cos(x - y);
	}

	public double MaxFitness(List<Gene> Pop) {
		double result = 0;

		for (int i = 0; i < Pop.size(); i++) {
			if (Pop.get(i).Fitness > result) {
				result = Pop.get(i).Fitness;
			}
		}

		return result;
	}

	public double AvgFitness(List<Gene> Pop) {
		double sum = 0;

		for (int i = 0; i < Pop.size(); i++) {
			sum += Pop.get(i).Fitness;
		}

		return sum / Pop.size();
	}
	
	public double ComboDistance(int currX, int currY, int goalX, int goalY){
		double result = 0;
		result = StandardFitness(currX,currY,goalX,goalY);
		result += ManhattanDistance(currX,currY,goalX,goalY);
		result += EuclideanDistance(currX,currY,goalX,goalY);
		result = result / 3;
		return result;
	}
	
	public double CalcFitness(int currX, int currY, int goalX, int goalY){
		switch(SystemConfigs.ChosenMethod){
		case Default:
			return StandardFitness(currX,currY,goalX,goalY);
		case Manhattan:
			return ManhattanDistance(currX,currY,goalX,goalY);
		case Euclidean:
			return EuclideanDistance(currX,currY,goalX,goalY);
		case Combo:
			return ComboDistance(currX,currY,goalX,goalY);
		}
		return StandardFitness(currX,currY,goalX,goalY);
	}
}
