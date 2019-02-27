/**
 * 
 * @author conor cook
 *
 */
public class ShuffleRec {
	private static int counter = 0;

	public static void main(String[] args) {
		if (args.length > 0 && args.length == 3) {
			String string1 = args[0];
			String string2 = args[1];
			String string3 = args[2];

			if ((string1.length() + string2.length()) != string3.length()) {
				System.out.println("Incorrect input. String lengths do not match, please try again. ");
				System.exit(0);
			}

			boolean sol = isShuffled(string1, string2, string3, 0, 0, 0);
			if (!sol) {
				System.out.println("No");
			} else {
				System.out.println("Yes");
			}
			System.out.println("Number of Recursive calls: " + counter);
		} else {
			System.out.println("Please enter in the correct arguments.");
			System.exit(0);
		}
	}

	private static boolean isShuffled(String string1, String string2, String string3, int pos1, int pos2, int pos3) {
		counter++;
		if (pos3 >= string3.length()) {
			return true;
		}

		boolean sameChar1 = false;
		boolean sameChar2 = false;

		if (pos1 < string1.length()) {
			sameChar1 = (string3.charAt(pos3) == string1.charAt(pos1));
		}
		if (pos2 < string2.length()) {
			sameChar2 = (string3.charAt(pos3) == string2.charAt(pos2));
		}
		return ((sameChar1 && isShuffled(string1, string2, string3, pos1 + 1, pos2, pos3 + 1))
				|| (sameChar2 && isShuffled(string1, string2, string3, pos1, pos2 + 1, pos3 + 1)));
	}

}
