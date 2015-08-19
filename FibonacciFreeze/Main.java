import java.util.Scanner; 
import java.math.BigInteger;

class Main {

	public static BigInteger FibonacciNumberAt(int i) {
		BigInteger[] FibonacciSequence =  new BigInteger[i+1];
		FibonacciSequence[0] = BigInteger.valueOf(0);
		
		if(i == 0)
			return FibonacciSequence[i];

		FibonacciSequence[1] = BigInteger.valueOf(1);

		for(int j=2; j<=i; j++) {
			FibonacciSequence[j] = BigInteger.valueOf(0);
			FibonacciSequence[j] = FibonacciSequence[j].add(FibonacciSequence[j-1]);
			FibonacciSequence[j] = FibonacciSequence[j].add(FibonacciSequence[j-2]);
		}
		return FibonacciSequence[i];
	}
	
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		while(input.hasNextInt()) {
			int i = input.nextInt();
			System.out.println("The Fibonacci number for "+i+" is "+FibonacciNumberAt(i));
		}
	}

}