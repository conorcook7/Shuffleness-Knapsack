import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author conor cook
 *
 */
public class KnapsackRec {
	private static int N, W;
	private static int[][] seen, keep;
	private static int[] values, weights;
	private static ArrayList<Integer> sol = new ArrayList<Integer>();
	private static int counter = 0;

	public static void main(String args[]) {

		if (args.length > 0 && args.length == 4) {
			N = Integer.parseInt(args[0]);
			W = Integer.parseInt(args[1]);
			File weightF = new File(args[2]);
			File valueF = new File(args[3]);

			values = new int[N];
			weights = new int[N];

			weights = parseFile(weightF, getArraySizeFromFile(weightF));
			values = parseFile(valueF, getArraySizeFromFile(valueF));

			seen = new int[W + 1][W + 1];
			keep = new int[W + 1][W + 1];

			System.out.println("Optimal Value: " + knapsack(N - 1, W));
			System.out.println("Optimal Solution:");

			int totalWeight = 0;
			while (N >= 0) {
				if (keep[N][W] == 1) {
					sol.add(0, N + 1);
					totalWeight += weights[N];
					W -= weights[N];
					N--;
				} else {
					N--;
				}
			}
			System.out.println(sol);
			System.out.println("Total Weight: " + totalWeight);
			System.out.println("Number of recursive calls: " + counter);
		} else {
			System.out.println("Please enter in the correct arguments.");
			System.out.println("The correct format is: ");
			System.out.println(
					"java KnapsackRec <N (number of items)> <W (max weight)> <w.txt (weights text file)> <v.txt (values text file)>");
			System.exit(0);
		}
	}

	private static int knapsack(int index, int W) {
		counter++;
		int take = 0;
		int dontTake = 0;

		// 1st item
		if (index == 0) {
			// capacity of the knapsack wont be exceeded if 1st item is picked
			if (weights[0] <= W) {
				counter++;
				// item picked
				keep[index][W] = 1;
				seen[index][W] = values[0];
				return values[0];
			} else {
				// item not picked, doesn't fit in knapsack
				counter++;
				keep[index][W] = 0;
				seen[index][W] = 0;
				return 0;
			}
		}

		if (weights[index] <= W) {
			take = knapsack(index - 1, W - weights[index]) + values[index];
		}

		dontTake = knapsack(index - 1, W);
		seen[index][W] = Math.max(take, dontTake);
		if (take > dontTake) {
			keep[index][W] = 1;
		} else {
			counter++;
			keep[index][W] = 0;
		}
		return seen[index][W];
	}

	private static int getArraySizeFromFile(File file) {
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
}