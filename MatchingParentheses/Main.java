import java.util.Stack;
import java.util.Scanner;

class Main {

	public static int IsProperParentheses(char[] charArray) {
		
		Stack<Integer> S = new Stack<Integer>();
		
		int n = charArray.length;
		
		int[] rIndex = new int[n];
		
		int j = 1;
		for(int i=0; i<n; i++) {
			if(charArray[i] == '*') {
				if(i > 0) {
					if(charArray[i-1] == '(') {
						rIndex[i] = rIndex[i-1];
						j = rIndex[i]+1;
						continue;
					}
				}
				if (i < charArray.length-1) {
					if(charArray[i+1] == ')') {
						rIndex[i] = j;
						continue;
					}
				}
				rIndex[i] = j;
				j++;
			} else {
				rIndex[i] = j;
				j++;
			}
 		}

 		// for(int i=0; i<n; i++) {
 		// 	System.out.println(charArray[i]+" : "+ rIndex[i]);
 		// }
 		// System.out.println();

		for(int i=0; i<n; i++) {
			if (charArray[i] == '(') {
				S.push(i);
			} else if (charArray[i] == '[') {
				S.push(i);
			} else if (charArray[i] == '{') {
				S.push(i);
			} else if (charArray[i] == '<') {
				S.push(i);
			} else if (charArray[i] == '*') {
				if(i > 0) {
					if(charArray[i-1] == '(') {
						S.push(i);
						continue;
					}
				}
				if (i < charArray.length-1) {
					if(charArray[i+1] == ')') {
						if(S.empty()) {
							return rIndex[i];
						} else {
							if(charArray[S.pop()] != '*')
								return rIndex[i];
						}
					}
				}
			} else if (charArray[i] == ')') {
				if(S.empty()) {
					return rIndex[i];
				} else {
					if(charArray[S.pop()] != '(')
						return rIndex[i];
				}
			} else if (charArray[i] == ']') {
				if(S.empty()) {
					return rIndex[i];
				} else {
					if(charArray[S.pop()] != '[')
						return rIndex[i];
				}
			} else if (charArray[i] == '}') {
				if(S.empty()) {
					return rIndex[i];
				} else {
					if(charArray[S.pop()] != '{')
						return rIndex[i];
				}
			} else if (charArray[i] == '>') {
				if(S.empty()) {
					return rIndex[i];
				} else {
					if(charArray[S.pop()] != '<')
						return rIndex[i];
				}
			}
		}
		if(S.empty()) {
			return -1; 
		} else {
			return rIndex[n-1]+1;
		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNextLine()) {
			String line = input.nextLine();
			char[] charArray = line.toCharArray();
			int ret = IsProperParentheses(charArray);
			if(ret == -1) {
				System.out.println("YES");
			} else {
				System.out.println("NO "+ret);
			}
		}
	}
}