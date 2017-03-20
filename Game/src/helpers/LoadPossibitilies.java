package helpers;

import java.io.*;
import java.util.*;
import java.util.List;
import geneticalgorithm.PossibleMovements;
import globals.FileNames;

public class LoadPossibitilies {
	private String[] movements = new String[] { "L", "R", "U", "D" };
	private List<String> poss;

	public LoadPossibitilies() {
		poss = new ArrayList<>();
		poss = ReadPossibilities();
	}

	private List<String> ReadPossibilities(){
		BufferedReader br = null;
		FileReader fr = null;
		
		try{
			fr = new FileReader(FileNames.PossibilitiesFile);
			br = new BufferedReader(fr);
			String currLine;
			br = new BufferedReader(new FileReader(FileNames.PossibilitiesFile));
			List<String>possibilities = new ArrayList<>();
			while((currLine = br.readLine()) != null){
				possibilities.add(currLine);
			}
			return possibilities;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<PossibleMovements> CreatePossibleMovements() {
		List<PossibleMovements> possMovements = new ArrayList<>();
		Random rnd = new Random();
		for (int i = 0; i < poss.size(); i++) {
			for (int j = 0; j < movements.length; j++) {
				PossibleMovements newPoss = new PossibleMovements();
				newPoss.Possibility = poss.get(i);
				newPoss.PreviousMovement = movements[j];
				newPoss.Movement = movements[rnd.nextInt(movements.length)];
				possMovements.add(newPoss);
			}
		}

		return possMovements;
	}
}
