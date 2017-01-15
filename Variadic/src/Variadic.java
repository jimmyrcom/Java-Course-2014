
public class Variadic {
	public static void main(String[] args) {
		System.out.println(variadic(6,9));
		System.out.println(variadic(3,2));
		System.out.println(variadic(3));
		// When you pass in one arg it returns
		// That same arg

	}
	public static int variadic(Integer... ints){
		int total=1;
		   for (Integer x : ints) total*=x;
		   return total;
	}
}
