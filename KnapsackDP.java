import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author conor cook
 *
 */
public class KnapsackDP {
	private static int N, W;
	private static boolean[][] keep;
	private static int[] values, weights;
	private static ArrayList<Integer> sol = new ArrayList<Integer>();

	private static int counter = 0;

	public static void main(String[] args) {
		if (args.length > 0 && args.length == 4) {
			N = Integer.parseInt(args[0]);
			W = Integer.parseInt(args[1]);
			File weightF = new File(args[2]);
			File valueF = new File(args[3]);

			values = new int[N];
			weights = new int[N];

			weights = parseFile(weightF, getArraySizeFromFile(weightF));
			values = parseFile(valueF, getArraySizeFromFile(valueF));

			keep = knapsack();
			printSolution(keep);
		} else {
			System.out.println("Please enter in the correct arguments.");
			System.out.println("The correct format is: ");
			System.out.println(
					"java KnapsackDP <N (number of items)> <W (max weight)> <w.txt (weights text file)> <v.txt (values text file)>");
			System.exit(0);
		}
	}

	private static boolean[][] knapsack() {
		int[][] table = new int[N + 1][W + 1];
		boolean[][] keep = new boolean[N][W + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= W; j++) {
				counter++;
				if (weights[i - 1] > j) {
					table[i][j] = table[i - 1][j];
				} else {
					int take = values[i - 1] + table[i - 1][j - weights[i - 1]];
					int dontTake = table[i - 1][j];
					if (take > dontTake) {
						counter++;
						keep[i - 1][j] = true;
						table[i][j] = take;
					} else {
						counter++;
						table[i][j] = dontTake;
					}
				}
			}
		}
		System.out.println("Dynamic Programming Table:\n");
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				System.out.printf("%3d ", table[i][j]);
			}
			System.out.println("");
		}
		return keep;
	}

	private static void printSolution(boolean[][] keep) {
		System.out.println("\nOptimal Solution: ");
		int totalWeight = 0;
		int optVal = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (keep[i][W] == true) {
				sol.add(0, i + 1);
				totalWeight += weights[i];
				optVal += values[i];
				W = W - weights[i];
			}
		}
		System.out.println(sol);
		System.out.println("Optimal Value: " + optVal);
		System.out.println("Total Weight: " + totalWeight);
		System.out.println("Total Table References: " + counter);
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
