// By James Ruska

package mathquiz;
import java.util.Random;
import java.util.Scanner;

public class MathQuiz {
	public static Scanner input = new Scanner(System.in);
	public static Random rand = new Random();
	public static void main(String[] args) {
		State state = new State();
		print("Welcome to Jimmy Ruska's Speed math game!\n");
		// Finite state machine
		while (true){
			if (state.state.equals("menu")) menuState(state);
			else if (state.state.equals("op")) opState(state);
			else if (state.state.equals("highlow")) highlowState(state);
			else if (state.state.equals("reset")) resetState(state);
			else if (state.state.equals("quiz")){
				int x= randomInt(state.min,state.max);
				int y= randomInt(state.min,state.max);
				state.x=x; state.y=y;
				quizState(state);
			}
			else if (state.state.equals("redo")) quizState(state);
			else break;
		}
	}
	public static void quizState(State state){
		int x=state.x, y=state.y, total=state.wrong+state.correct;
		double score = (total==0) ? 0 : ((double)state.correct/total)*100;
		print("|| Scores || Correct: "+state.correct 
				+" ("+ String.format("%.2f", score) + "%"+")"
				+ " / Incorrect: "+state.wrong
				+" / Total: "+ total);
		String response=ask("Enter the answer or q to quit.\nMath puzzle: "
					+x+" "+(char)state.op+" "+y+" = ");
		int answer = (state.op=='*') ? x*y
					: (state.op=='-') ? x-y
					: x+y;
		
		if (response.equals("q")) state.state="menu"; 
		else if (answer==Integer.parseInt(response)){
			if (state.attempt==0) state.correct++;
			state.attempt=0;
			write("Excellent!!!!");
			state.state="quiz";
		}
		else{
			if (state.attempt==0) state.wrong++;
			state.attempt++;
			write("Incorrect. Try again.");
			state.state="redo";
		}
	}
	public static void highlowState(State state){
		state.min=Integer.parseInt(ask("Enter the lowest possible number"));
		state.max=Integer.parseInt(ask("Enter the highest possible number"));
		write("Min and Max values configured successfully!");
		state.state="menu";
	}
	public static void opState(State state){
			String op=ask("Please select an operator, choose from +, -, *");
			write("Operator values configured successfully!");
			state.op = op.charAt(0);
			state.state="menu";
			
	}
	public static void resetState(State state){
		state.wrong = state.correct = 0;
		write("Scores reset successfully!");
		state.state="menu";
	}
	public static void menuState(State state){
		print("Option menu: \n"
				+ "1. Play!\n"
				+ "2. Configure number range\n"
				+ "3. Configure operation\n"
				+ "4. Reset Scores\n"
				+ "5. Quit\n");
		Integer a = Integer.parseInt(ask("Please select an option"));
		state.state = (a==1) ? "quiz"
					: (a==2) ? "highlow"
					: (a==3) ? "op"
					: (a==4) ? "reset"
					: "quit";
	}
	public static String ask(String question){
		System.out.print(question + "\n> ");
		return input.nextLine();
	}
	public static int randomInt(int min, int max){
		return rand.nextInt(max-min+1)+min;
	}
	public static void write(String what){
		// for a more alive congratulations
		what="\n"+what+"\n\n--------------\n";
		for(int i=0; i<what.length(); i++){
			System.out.print(what.charAt(i));
			try{ Thread.sleep(33); } catch(Exception e){}
		}
	}
	public static void print(String Str){
		System.out.println("\n"+Str);
	}
}
class State{
	public int min=3, max=12, x, y, op='+', attempt=0, correct=0, wrong=0;
	public String state="menu";
}
