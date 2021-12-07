package java_programs;

import java.util.ArrayList;
import java.util.List;

public class Fibbonacci {
	public static void main(String... args) {
		List<Integer> fib = new ArrayList<Integer>();
		int n1 = 0, n2 = 1, n3;
		fib.add(n1);
		fib.add(n2);
		for (int i = 2; i < 20; i++) {
			n3 = n1 + n2;
			fib.add(n3);
			n1 = n2;
			n2 = n3;
		}
		
		System.out.println(fib);
	}

}
