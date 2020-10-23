import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Scheduling {
	
	// Efficiency: O(n + nlgn + 2m)
	// n = total number of assignments given, m = assignments able to be completed
	// nlgn is the assumed efficiency for the Collections sorting algorithm
	static void assigns(char a[], List<Integer> due, int weight[]) {
		int sum = 0, min, maxAssigns = Collections.max(due);
		StringBuilder output = new StringBuilder("");
		Character a2[] = new Character[maxAssigns];

		List<Integer> weightsToComplete = new ArrayList<>();

		int j = 0, day = 5;
		j = maxAssigns - 1;

		// Start at the assignment that is due latest
		for (int i = weight.length - 1; i >= 0; i--) {
			// If the list of assignments to complete is less than the max
			// assignments possible to complete, keep adding to list of
			// assignments to complete
			if (weightsToComplete.size() < maxAssigns) {
				weightsToComplete.add(weight[i]);
				a2[j--] = a[i];
				day = due.get(i);

			} else {
				min = Collections.min(weightsToComplete);
				// If the weight of the current assignment is worth more
				// than the smallest weight currently in the list,
				// check the due date
				if (weight[i] > min) {
					// If the due date is past the current date, skip it.
					// Otherwise replace the smallest weight in the list with
					// the current assignment weight
					if (day <= due.get(i)) {
						
						continue;
					}
					day = due.get(i);
					weightsToComplete.remove(weightsToComplete.indexOf(min));
					weightsToComplete.add(weight[i]);
					a2[weightsToComplete.indexOf(Collections.min(weightsToComplete))] = a[i];
				}
			}
		}

		// Add up the total percentage from assignments able to be completed
		for (int i : weightsToComplete) {
			sum += i;
		}

		// Assignment names stored and sorted for output
		List<Character> toComplete = Arrays.asList(a2);
		Collections.sort(toComplete);

		for (char c : toComplete) {
			output.append(c);
			output.append(" ");
		}

		System.out.println("Assignment Order: " + output);
		System.out.println("Total Percentage: " + sum);
	}

	public static void main(String args[]) {
		char assignments[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
		int due2[] = { 1, 1, 2, 3, 3, 4, 5, 5 };
		List<Integer> due = new ArrayList<>();
		for (int i : due2) {
			due.add(i);
		}
		int weight[] = { 16, 15, 5, 8, 20, 10, 5, 15 };
		assigns(assignments, due, weight);
	}
}
