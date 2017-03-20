package helpers;

import java.io.*;

import Maze.Maze;
import geneticalgorithm.*;
import globals.AlgorithmSettings;

public class TextFileOutput {

	String fileName = "winnerGene.txt";

	public void Write(Gene g,Maze m){
		BufferedWriter bw = null;
		FileWriter fw = null;
		String textString = "Gene: " + AlgorithmSettings.CurrentGene;
		textString += " Generation: " + AlgorithmSettings.Generation;
		Fitness fitness = new Fitness();
		textString += " Fitness of gene: " + fitness.StandardFitness(g.x, g.y, m.EndXPosition, m.EndYPosition);
		try{
			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			bw.write(textString);
			bw.close();
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String[] ReadInFile(String file) {
		BufferedReader br = null;
		FileReader fr = null;	
		String result = "";
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			String sCurrLine = "";
			br = new BufferedReader(new FileReader(file));
			while ((sCurrLine = br.readLine()) != null) {
				result += sCurrLine;
			}

			br.close();
			fr.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result.split(",");
	}
}