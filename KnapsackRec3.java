import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author conor cook
 *
 */
public class KnapsackRec3 {
	private static int N, W;
	private static int[] weights, values, optSol;
	private static int counter, totalW = 0;

	public static void main(String[] args) {
		if (args.length > 0 && args.length == 4) {
			N = Integer.parseInt(args[0]);
			W = Integer.parseInt(args[1]);
			File weightF = new File(args[2]);
			File valueF = new File(args[3]);

			weights = parseFile(weightF, getSize(weightF));
			values = parseFile(valueF, getSize(valueF));

			System.out.println("Optimal Solution: ");
			System.out.println("Total Weight: " + totalW);
			System.out.println("Optimal Value: " + maxItem(W, weights, N, values));
			System.out.println("Number of recursive calls: " + counter);
		} else {
			System.out.println("Please enter in the correct arguments.");
			System.out.println("The correct format is: ");
			System.out.println(
					"java KnapsackRec <N (number of items)> <W (max weight)> <w.txt (weights text file)> <v.txt (values text file)>");
			System.exit(0);
		}
	}

	private static int getSize(File file) {
		int val = 0;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextInt()) {
				scanner.next();
				val++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File Not Found. ");
			System.exit(0);
		}
		return val;
	}

	private static int[] parseFile(File file, int size) {
		int[] array = new int[size];
		try {
			int index = 0;
			Scanner scan = new Scanner(file);

			while (scan.hasNextInt()) {
				array[index++] = scan.nextInt();
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File Not Found. ");
			System.exit(0);
		}
		return array;
	}

	private static int max(int x, int y) {
		return (x > y) ? x : y;
	}

	private static int maxItem(int W, int weights[], int N, int values[]) {
		counter++;
		if (N == 0 || W == 0) {
			return 0;
		}

		if (weights[N - 1] > W) {
			return maxItem(W, weights, N - 1, values);
		} else {
			return max(values[N - 1] + maxItem(W - weights[N - 1], weights, N - 1, values),
					maxItem(W, weights, N - 1, values));
		}
	}
}