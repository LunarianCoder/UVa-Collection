import java.util.Scanner;

class Main {

	public static int compare(int x, int y) {
		if ((x == -1) && (y == -1)) {
			return 0;
		} else if (x == -1) {
			return 1;
		} else if (y == -1) {
			return 0;
		} else {
			if(x > y)
				return 1;
			else
				return 0;
		}
	}

	public static int mergeCost(int[][] D, int[][] I, 
									int[] B, int i, int j, int k) {
		if((D[i][k] == -1) || (D[k][j] == -1)) {
			return -1;
		} else {
			int ret = D[i][k] + D[k][j];
			if(I[i][k]+I[k][j] > 1) {
				ret = ret + B[k];
			}
			return ret;
		}
	}

	public static void producePath(final int[][] A,
									int[][] N, int[][] D, int s, int t) {
		if(N[s][t] == -1)
			return;
		System.out.println("From "+(s+1)+" to "+(t+1)+" :");
		System.out.print("Path: "+(s+1));
		int n = s;
		while(n != t) {
			System.out.print("-->");
			n = N[n][t];
			System.out.print(n+1);
		}
		System.out.println();
		System.out.println("Total cost : "+D[s][t]);
	}

	public static void minimumAllPairsPath(final int[][] A, 
									final int[] B, int[][] N, int[][] D) {
		int n = B.length;
		int[][] I = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				D[i][j] = A[i][j];
				
				if(i == j)
					I[i][j] = 0;
				else
					I[i][j] = 1;
				
				N[i][j] = j;	
			}
		}

		for(int k=0; k<n; k++) 
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++) {
					int res = mergeCost(D, I, B, i, j, k);
					if(compare(D[i][j],res) > 0) {
						D[i][j] = res;
						N[i][j] = N[i][k];
					 	if(!((i == k) && (k == j))) 
					 		I[i][j]++;
					}
					
				}
	}

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		String line = input.nextLine();
		int m = Integer.parseInt(line);

		int k = 1;
		line = input.nextLine();
			
		while(k <= m) {
			line = input.nextLine();
			String[] tokens = line.split("\\s+");
			int n = tokens.length;

			int[][] A = new int[n][n];
			int[] B = new int[n];

			for(int j=0; j<n; j++)
				A[0][j] = Integer.parseInt(tokens[j]);

			for(int i=1; i<n; i++) {
				line = input.nextLine();
				tokens = line.split("\\s+");
				for(int j=0; j<n; j++)
					A[i][j] = Integer.parseInt(tokens[j]);
			}

			line = input.nextLine();
			tokens = line.split("\\s+");
			for(int j=0; j<n; j++)
				B[j] = Integer.parseInt(tokens[j]);

			int[][] N = new int[n][n];
			int[][] D = new int[n][n];
			minimumAllPairsPath(A, B, N, D);

			while(input.hasNextLine()) {
				line = input.nextLine();
				tokens = line.split("\\s+");
				if(tokens.length != 2)
					break;
				int s = Integer.parseInt(tokens[0])-1;
				int t = Integer.parseInt(tokens[1])-1;
				producePath(A, N, D, s, t);
				if(input.hasNextLine())
					System.out.println();
			}
			k++;
		}

	}

}