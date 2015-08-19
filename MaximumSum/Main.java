import java.util.Scanner;


class Main {

	public static int Kadence(int[] A)
	{
		int n = A.length;
		int end = A[0];
		int max = end;

		for(int i=1; i<n; i++) {
			end = Math.max(A[i], end+A[i]);
			max = Math.max(max, end);
		}
		return max;
	}	

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);

		while(input.hasNext()){

			int n = input.nextInt();

			int[][] M = new int[n][n];

			for(int i=0; i<n; i++) 
				for(int j=0; j<n; j++)
					M[i][j] = input.nextInt();

			int ans = -127;

			for(int i=1; i<n; i++) 
				for(int j=0; j<n; j++)
					M[i][j] += M[i-1][j];


			for(int i=0; i<n; i++)
				for(int j=i; j<n; j++) {
					int[] R = new int[n];
					if(i==0) {
						for(int k=0; k<n; k++) {
							R[k] = M[j][k];
						}
					}
					else {
						for(int k=0; k<n; k++) {
							R[k] = M[j][k] - M[i-1][k];
						}
					}
					ans = Math.max(ans, Kadence(R)); 
				}

			System.out.println(ans);
		}

	}
}