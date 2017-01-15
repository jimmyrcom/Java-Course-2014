// calculate average rainfall
// Jimmy Ruska
import java.util.Scanner;
public class Rainfall {
    public static Scanner input = new Scanner (System.in);
    public static void main(String[] args){
        double totalRainfall=0, years, months;
        years=ask("Enter the number of years", 1);
        for (int year=1; year <= years; year++){
            for(int month=1; month<=12; month++){
                totalRainfall+=ask("How much rainfall was there in year "
                                   + (int)year  + " month "
                                   + (int)month + "?", 0);
            }
        }
        months=years*12;
        System.out.println((totalRainfall / years) + " average rainfall per year. "
                           + (totalRainfall / months) + " average rainfall per month for "
                           + (int)months + " months");
    }
    public static double ask(String question, int atLeast){
        System.out.print(question + "\n> ");
        double answer= input.nextDouble();
        if (answer < atLeast){
            System.out.println("Invalid value: "+answer+". You need at least: "+atLeast);
            return ask(question,atLeast);
        }
        return answer;
    }
}

