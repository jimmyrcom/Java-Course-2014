// Calculates the area of different geometric shapes
// The formula provided was the formula for volume a cylinder and it matches
// the signature of area of a rectangle
// I have made area of rectangle ints to differentiate the two.
// It still should show I understand the concept of overloading.
public class Overloading {
	// area of circle
	public static double area(double radius){
		return Math.PI*radius*radius;
	}
	// area of rect
	public static int area(int width, int length){
		return width*length;
	}
	// volume of cylinder
	public static double area(double radius, double height){
		return Math.PI * (radius*radius) * height;
	}
	
	public static void main(String[] args) {
		System.out.println("The area of a rectangle with width 5 and length 4 is "+area(5,4));
		System.out.println("The area of a circle with radius 10 is "+area(10));
		System.out.println("The area of a cylinder with radius 5 and height 3 is "+area(5.0,3.0));
	}

}
