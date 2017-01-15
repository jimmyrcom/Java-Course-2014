// Get land size in acres
public class AssignmentTwo {
	public static void main(String[] args){
		// size of land in sqrft
		final float tractOfLand=389767;
		// how many sqrfeet per acre
		final float sqrftPerAcre=43560;
	    // calculate how many acres is in tractOfLand. if it was ints, (float) tractOfLand/sqrftPerAcre; 
		final double tractOfLandAcres= tractOfLand/sqrftPerAcre;
		System.out.println("A tract of land with "
				+ (int)tractOfLand
				+ " sqrft is equal to "
				+ tractOfLandAcres
				+ " acres");
	}
}
