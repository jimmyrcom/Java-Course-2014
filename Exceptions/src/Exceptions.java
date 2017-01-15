// This function throws an exception and catches it
public class Exceptions {
	public static double calcAverage(double[] testScores){
		int total=0;
		// add up all tests and divide by the length, or return exception if <0 >100
		for (int i=0; i<testScores.length; i++){
			if (testScores[i]<0 || testScores[i]>100)
				throw new IllegalArgumentException("Incorrect Grade");
			total+=testScores[i];
		}
		return ((float) total / testScores.length);
	}
	public static void main(String[] args){
		double[] good={0,2,3,4,100};
		double[] bad={0,2,3,4,101};
		// This will work because everything is in range
		System.out.println("The average of the good set is "+Exceptions.calcAverage(good)
				+".\nNow trying the bad set");
		try {
			// This will throw an exception
			System.out.println(Exceptions.calcAverage(bad));
		}
		catch (IllegalArgumentException e){
			System.out.println("Exception: "+e.getMessage());
		}
		
	}	
}
