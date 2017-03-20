package artificialintelligence;

import java.util.Random;

public class Guassian {
	public static void GetRandomGuassian(double mean, double stddev, double val1, double val2) {
		Random rnd = new Random();

		double u, v, s, t;

		do {
			u = 2 * rnd.nextDouble() - 1;
			v = 2 * rnd.nextDouble() - 1;

		} while (u * u + v * v > 1 || (u == 0 && v == 0));

		s = u * u + v * v;
		t = Math.sqrt((-2.0 * Math.log(s)) / s);

		val1 = stddev * u + mean;
		val2 = stddev * u + mean;
	}

	private static double GetRandomGuassian(double mean, double stddev) {
		double val1 = 0.0;
		double val2 = 0.0;
		GetRandomGuassian(mean, stddev, val1, val2);

		return val1;
	}

	public static double GetRandomGuassian() {
		return GetRandomGuassian(0.0, 1.0);
	}
}
