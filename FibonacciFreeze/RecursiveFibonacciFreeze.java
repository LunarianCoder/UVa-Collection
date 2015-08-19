import java.util.Scanner; 

class RecursiveFibonacciFreeze {

	public static int FibonacciNumberAt(int i) {
		if(i == 0) {
			return 0;
		} else if (i == 1) {
			return 1;
		} else {
			return FibonacciNumberAt(i-1)+FibonacciNumberAt(i-2);
		}
	}
	
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		while(input.hasNextInt()) {
			int i = input.nextInt();
			System.out.println(FibonacciNumberAt(i));
		}
	}

}