package artificialintelligence;

import enums.TransferFunction;

public class TransferFunctions {

	public static double Evaluate(TransferFunction tFunc, double input) {
		switch (tFunc) {
		case Sigmoid:
			return Sigmoid(input);
		case Linear:
			return Linear(input);
		case Guassian:
			return Guassian(input);
		case RationalSigmoid:
			return RationalSigmoid(input);
		case None:
		default:
			return 0.0;
		}
	}

	public static double EvaluateDerivative(TransferFunction tFunc, double input) {
		switch (tFunc) {
		case Sigmoid:
			return SigmoidDerivative(input);
		case Linear:
			return LinearDerivative(input);
		case Guassian:
			return GuassianDerivative(input);
		case RationalSigmoid:
			return RationalSigmoidDerivative(input);
		case None:
		default:
			return 0.0;
		}
	}

	private static double Sigmoid(double x) {
		return 1.0 / (1.0 + Math.exp(-x)); // exp refers to Euler's number, is
											// the value of:
											// 2.7182818284590452353602874713527
	}

	private static double SigmoidDerivative(double x) {
		return Sigmoid(x) * (1 - Sigmoid(x));
	}

	public static double Linear(double x) {
		return x;
	}

	public static double LinearDerivative(double x) {
		return 0.1;
	}

	private static double Guassian(double x) {
		return Math.exp(-Math.pow(x, 2));
	}

	private static double GuassianDerivative(double x) {
		return -2.0 * x * Guassian(x);
	}

	private static double RationalSigmoid(double x) {
		return x / (1.0 + Math.sqrt(1.0 + x * x));
	}

	private static double RationalSigmoidDerivative(double x) {
		double val = Math.sqrt(1.0 + x * x);
		return 1.0 / val * (1 + val);
	}
}
