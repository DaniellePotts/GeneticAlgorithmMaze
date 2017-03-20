package artificialintelligence;

import enums.TransferFunction;

public class NeuralNetwork {

	private int LayerCount;
	private int InputSize;
	private int[] LayerSize;

	private double[][] LayerOutput;
	private double[][] LayerInput;

	private double[][][] Weights;
	private double[][][] PreviousWeightDelta;

	private TransferFunction[] TFuncs;

	public NeuralNetwork(int[] lSize, TransferFunction[] Tf) {
		LayerCount = lSize.length - 1;
		InputSize = lSize[0];
		LayerSize = new int[LayerCount];

		TFuncs = new TransferFunction[LayerCount];

		Weights = new double[LayerCount][][];
		PreviousWeightDelta = new double[LayerCount][][];

		for (int i = 0; i < LayerCount; i++) {
			LayerSize[i] = lSize[i + 1];
			TFuncs[i] = Tf[i];
		}

		LayerOutput = new double[LayerCount][];
		LayerInput = new double[LayerCount][];

		for (int i = 0; i < LayerCount; i++) {
			LayerOutput[i] = new double[LayerSize[i]];
			LayerInput[i] = new double[LayerSize[i]];

			Weights[i] = new double[i == 0 ? InputSize : LayerSize[i - 1]][];
			PreviousWeightDelta[i] = new double[i == 0 ? InputSize : LayerSize[i - 1]][];

			for (int j = 0; j < (i == 0 ? InputSize : LayerSize[i - 1]); j++) {
				Weights[i][j] = new double[LayerSize[i]];
				PreviousWeightDelta[i][j] = new double[LayerSize[i]];
			}
		}

		for (int i = 0; i < LayerCount; i++) {
			for (int j = 0; j < LayerSize[i]; j++) {
				LayerOutput[i][j] = 0.0;
				LayerInput[i][j] = 0.0;
			}

			for (int j = 0; j < (i == 0 ? InputSize : LayerSize[i - 1]); j++) {
				for (int k = 0; k < LayerSize[i]; k++) {
					Weights[i][j][k] = Guassian.GetRandomGuassian(); // guassian
					PreviousWeightDelta[i][j][k] = 0.0;
				}

			}
		}
	}
}
