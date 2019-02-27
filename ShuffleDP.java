/**
 * 
 * @author conor cook
 *
 */
public class ShuffleDP {
	private static int X, Y, Z;
	private static char[] charArray1, charArray2, charArray3;
	private static int counter = 0;
	private static boolean[][] sol;

	public static void main(String[] args) {
		if (args.length > 0 && args.length == 3) {
			String string1 = args[0];
			String string2 = args[1];
			String string3 = args[2];

			if ((string1.length() + string2.length()) != string3.length()) {
				System.out.println("Incorrect input. String lengths do not match, please try again. ");
				System.exit(0);
			}

			charArray1 = string1.toCharArray();
			charArray2 = string2.toCharArray();
			charArray3 = string3.toCharArray();

			X = charArray1.length;
			Y = charArray2.length;
			Z = charArray3.length;

			if (!isShuffled(charArray1, charArray2, charArray3)) {
				printSol();
				System.out.println("No");
			} else {
				printSol();
				System.out.println("Yes");
			}
			System.out.println("Number of table references: " + counter);
		} else {
			System.out.println("Please enter in the correct arguments.");
			System.exit(0);
		}
	}

	private static boolean isShuffled(char[] s1, char[] s2, char[] s3) {
		if (X == 0 && Y == 0 && Z == 0) {
			return true;
		}

		sol = new boolean[X + 1][Y + 1];
		sol[0][0] = true;

		for (int i = 1; i <= X; ++i) {
			if (s1[i - 1] == s3[i - 1]) {
				counter++;
				sol[i][0] = true;
			} else {
				break;
			}
		}

		for (int i = 1; i <= Y; ++i) {
			if (s2[i - 1] == s3[i - 1]) {
				counter++;
				sol[0][i] = true;
			} else {
				break;
			}
		}

		for (int i = 1; i <= X; ++i) {
			for (int j = 1; j <= Y; ++j) {
				if (s1[i - 1] == s3[i + j - 1]) {
					counter++;
					sol[i][j] = sol[i - 1][j] || sol[i][j];
				}
				if (s2[j - 1] == s3[i + j - 1]) {
					counter++;
					sol[i][j] = sol[i][j - 1] || sol[i][j];
				}
			}
		}
		return sol[X][Y];
	}

	private static void printSol() {
		for (int i = 0; i <= X; ++i) {
			for (int j = 0; j <= Y; ++j) {
				if (sol[i][j] == true) {
					System.out.print("1 ");
				} else if (sol[i][j] == false) {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}
}
