// James Ruska
// Learn about multi dimensional arrays through test grading system
// This program prompts for the teacher to enter in grades and then calculates averages
// for each student
import java.util.Scanner;
public class TestGrades {
	public static Scanner input = new Scanner (System.in);
	public static String[] names =new String[5];
	public static char[] grades=new char[5];
	public static double[] averages=new double[5];
	public static double[][] scores=new double[5][4];

	public static void main(String[] args){
		// for each student prompt for the grades
		for (int i=0;i<5; i++){
			double total=0;
			names[i] = ask("Enter the student name");
			System.out.println("Enter the student's 4 grades:\n\n");
			for (int k=0; k<4; total+=scores[i][k], k++)
				scores[i][k]=validateGrade("Grade #"+ (k+1));
			double average = total / 4.0;
			averages[i] = average;
			// turn a number into a letter grade. Could have made this a method
			if (average>=90) grades[i] = 'A';
			else if (average>=80) grades[i] = 'B';
			else if (average>=70) grades[i] = 'C';
			else if (average>=60) grades[i] = 'D';
			else grades[i] = 'F';
		}
		System.out.println("\nResults!\n\n");
		// display results
		for (int i=0; i<5; i++) showStudent(i);
	}
	// this happens at the end, it just dumps what you imput
	public static void showStudent(int id){
		double[] score=scores[id];
		System.out.println("Student name: "+names[id]+"\n"
				+"Student grade: "+ grades[id]+", Average: "+averages[id]+"\n"
				+"Student test scores: "+score[1]+" "+score[2]+" "+score[3]
						+" "+score[3]+"\n\n");
	}
	// simple input prompt wrapper
	public static String ask(String question){
		System.out.print(question + "\n> ");
		return input.nextLine();
	}
	// make sure the input is not bad data
	public static int validateGrade(String question){
		String grade=ask(question);
		if (grade.equals("")){
			System.out.println("Bad value!");
			grade=ask(question);
		}
		int grade1=Integer.parseInt(grade);
		if (grade1<0 || grade1>100){
			System.out.println("Bad value!");
			return validateGrade(question);
		}
		return grade1;
	}
}
	
