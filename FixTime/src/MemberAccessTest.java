

public class MemberAccessTest {
	public static void main(String args[]) {
		Time1 time = new Time1(); // create and initialize Time1 object
		System.out.println(time.toString());
		
		time.setHour(7); 
		time.setMinute(15);
		time.setSecond(30);
		
		System.out.println(time.toString());
		
	} // end main
} // end class MemberAccessTest

class Time1 {
	private int hour; // 0 - 23
	private int minute; // 0 - 59
	private int second; // 0 - 59


	public void setHour(int hour1) {
		if (hour1 >= 0 && hour1 <=23) hour = hour1;
		else System.out.println("Hour not in range Error!");
	}

	public void setMinute(int minute1) {
		if (minute1 >= 0 && minute1 <=59) minute = minute1;
		else System.out.println("Minute not in range Error!");
	}

	public void setSecond(int second1) {
		if (second1 >= 0 && second1 <=59) second = second1;
		else System.out.println("Second not in range Error!");
	}

	// set a new time value using universal time; ensure that
	// the data remains consistent by setting invalid values to zero
	public void setTime(int h, int m, int s) {
		hour = ((h >= 0 && h < 24) ? h : 0); // validate hour
		minute = ((m >= 0 && m < 60) ? m : 0); // validate minute
		second = ((s >= 0 && s < 60) ? s : 0); // validate second
	} // end method setTime

	// convert to String in universal-time format (HH:MM:SS)
	public String toUniversalString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	} // end method toUniversalString

	// convert to String in standard-time format (H:MM:SS AM or PM)
	public String toString() {
		return String.format("%d:%02d:%02d %s", ((hour == 0 || hour == 12) ? 12
				: hour % 12), minute, second, (hour < 12 ? "AM" : "PM"));
	} // end method toString
} // end class Time1

