package helpers;

import java.io.*;

import enums.CrossoverAlgorithms;
import geneticalgorithm.*;
import globals.*;

public class SerializingHelper {
	private boolean SavePop(Population trainedPop,String fileName) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(PrepareData(trainedPop));
			out.close();
			fileOut.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private Population LoadPop(String fileName) {
		SavedPopulation result = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			result = (SavedPopulation)in.readObject();
			in.close();
			fileIn.close();
			return Parse(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void SavePopulation(CrossoverAlgorithms currAlgorithm,Population pop){
		if(currAlgorithm != null){
			switch(currAlgorithm){
			case BucketMethod:
				SavePop(pop,FileNames.Bucket_Trained_Population);
				break;
			case Tournament:
				SavePop(pop,FileNames.Tournament_Trained_Population);
				break;
			}
		}else{
			SavePop(pop,FileNames.TEST_FILE);
		}
	}
	
	public Population LoadPopulation(CrossoverAlgorithms currAlgorithm){
		if(currAlgorithm != null){
			switch(currAlgorithm){
			case BucketMethod:
				return LoadPop(FileNames.Bucket_Trained_Population);
			case Tournament:
				return LoadPop(FileNames.Tournament_Trained_Population);
			}
		}else{
			return LoadPop(FileNames.TEST_FILE);
		}
		return null;
	}
	
	private SavedPopulation PrepareData(Population pop){
		SavedPopulation savedPop = new SavedPopulation(pop.PopSize());
		savedPop.Genes = pop.Genes;
		savedPop.MaxFitness = AlgorithmSettings.Generation > 1 ? AlgorithmSettings.BestFitness : 0.0;
		savedPop.AverageFitness = AlgorithmSettings.Generation > 1 ? AlgorithmSettings.AverageFitness : 0.0;
		savedPop.Generation = AlgorithmSettings.Generation;
		
		return savedPop;
	}
	
	private Population Parse(SavedPopulation pop){
		Population newPop = new Population(pop.Genes.size());
		newPop.Genes = pop.Genes;
		AlgorithmSettings.BestFitness = pop.MaxFitness;
		AlgorithmSettings.AverageFitness = pop.AverageFitness;
		AlgorithmSettings.Generation = pop.Generation;
		AlgorithmSettings.PopSize = pop.Genes.size();
		return newPop;
	}
}
