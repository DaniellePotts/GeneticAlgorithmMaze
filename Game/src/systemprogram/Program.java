package systemprogram;

public class Program {
	public static void main(String[] args) {
		try {
			AITraining.Run();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
}