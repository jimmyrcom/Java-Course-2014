package myPackage;

import java.math.BigDecimal;
import java.text.NumberFormat;
/*
Reasons for polymorphism
*/
import static java.lang.Math.*;

public class Game{
	static class Foo{
		
	}
	public static void main(String[] args) {
		Math.cos(1.0);
		BigDecimal x = new BigDecimal("100");
		System.out.println(NumberFormat.getCurrencyInstance().format(x));
		String.format("Foobar");
	}

}