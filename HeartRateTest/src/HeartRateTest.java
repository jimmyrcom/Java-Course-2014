// By James Ruska
// 3.16 (Target-Heart-Rate Calculator)
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class HeartRateTest {
	public static Scanner input = new Scanner (System.in);
	public static void main(String[] args) {		
		HeartRates me = new HeartRates("James", "Ruska", 10, 20, 1985);
		System.out.printf("My age is: %s\n", me.calculateAge());
		System.out.printf("My first name is: %s\n", me.getFirstName());
		System.out.printf("My last name is: %s\n", me.getLastName());
		System.out.printf("My DOB: %s/%s/%s\n\n", me.getMonth(), me.getDay(), me.getYear());
		
		String firstName = askString("What is your first name?");
		me.setFirstName(firstName);
		String lastName = askString("What is your last name?");
		me.setLastName(lastName);
		Integer month = askNumber("What is your birth month?",1,12);
		me.setMonth(month);
		Integer day = askNumber("What is your birth day?",1,31);
		me.setDay(day);
		Integer year = askNumber("What is your birth year?",1900, 2014);
		me.setYear(year);
		System.out.printf("My age is: %s\n", me.calculateAge());
		System.out.printf("My first name is: %s\n", me.getFirstName());
		System.out.printf("My last name is: %s\n", me.getLastName());
		System.out.printf("My DOB: %s/%s/%s\n", me.getMonth(), me.getDay(), me.getYear());
		System.out.printf("My max heart rate is: %s\n", me.maxHeartRate());
		me.printTargetHeartRate();
		
	}
	// helper function to add text to nextDouble
	public static Integer askNumber(String question, int lower, int upper){		
		System.out.printf("\n%s\n>: ", question);
		try {
			int myint = input.nextInt();
			if (myint >= lower && myint <= upper) return myint;
			throw new Exception();
			}
		catch(Exception e){
				System.out.println("\nBad input try again!\n");
				return askNumber(question, lower, upper);
		}
	}
	public static String askString(String question){
		System.out.printf("\n%s\n>: ", question);
		return input.nextLine();
	}
	public static class HeartRates {
		String firstName, lastName;
		int month, day, year;
		public HeartRates(String firstName, String lastName, int month, int day, int year){
			this.firstName = firstName;
			this.lastName = lastName;
			this.month = month;
			this.day = day;
			this.year = year;
		}
		public int maxHeartRate(){
			return 220 - calculateAge(); 
		}
		public static class TargetHeartRate{
			double lowerRange;
			double upperRange;
			public TargetHeartRate(int max){
				lowerRange=max*0.50;
				upperRange=max*0.85;
			}
		}
		public TargetHeartRate targetHeartRate(){
			return new TargetHeartRate(maxHeartRate());
		}
		public void printTargetHeartRate(){
			TargetHeartRate target = targetHeartRate();
			System.out.printf("Target heart rate is from %s to %s\n"
						, target.lowerRange, target.upperRange);
		}
		public int calculateAge(){
			   Calendar calendar = new GregorianCalendar(year, month-1, day);			   
			   Calendar currently = new GregorianCalendar();
			    int age = currently.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
			    // leap year
			    if ((calendar.get(Calendar.MONTH) > currently.get(Calendar.MONTH))
			        || (calendar.get(Calendar.MONTH) == currently.get(Calendar.MONTH)
			        && calendar.get(Calendar.DAY_OF_MONTH) > currently.get(Calendar.DAY_OF_MONTH))) {
			    	age--;
			    }
			    return age;
		}

		public String getFirstName(){
			return this.firstName;
		}
		public String getLastName(){
			return this.lastName;
		}
		public int getMonth(){
			return this.month;
		}
		public int getDay(){
			return this.day;
		}
		public int getYear(){
			return this.year;
		}
		
		public void setYear(int x){
			year = x;
		}
		public void setMonth(int x){
			month = x;
		}
		public void setDay(int x){
			day = x;
		}
		public void setFirstName(String x){
			firstName = x;
		}
		public void setLastName(String x){
			lastName = x;
		}
	}
}
