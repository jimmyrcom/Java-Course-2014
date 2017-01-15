// GPA grade calculator
import java.util.Scanner;
public class MyFirstProgram {
	public static Scanner input = new Scanner(System.in);
	public static void main(String[] args){
		double total=0;
		int count=0;
		int[] gradeCount = new int[5]; 
		
		while(true){	
			int x= qualityPoints();
			if (x==-1) break;
			gradeCount[x]++;
			total+=x;
			count++;
		}
		
		System.out.println("\n\nGrade\n-------------------");
		for (int i=0; i<gradeCount.length;i++){
			String stars="";
			for (;gradeCount[i]>0;gradeCount[i]--) stars+="*";
			System.out.println(i + ": "+stars);
		}
		System.out.println("---------------------\n"
				+"Avg GPA: "+total/count + " from " + count+" grades.");
	}
	public static int qualityPoints(){
		System.out.print("\nEnter a grade or type q to quit: ");
		String answer = input.nextLine();
		if (answer.equals("q")) return -1;
		double x = Double.parseDouble(answer);
		return (x>=90) ? 4 : (x>=80) ? 3 : (x>=70) ? 2 : (x>=60) ? 1 : 0;
	}
}
