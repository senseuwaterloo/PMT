package pmt.scripts.mutation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mutant_hit_linear_to_matrix_with_results {

	public static String path_to_linear_csv="/Users/mj/Desktop/ju2jmh-mutants-linear.csv";
	public static String path_to_output_csv="/Users/mj/Desktop/ju2jmh-mutants-linear.csv";

	public static void main(String[] args) throws IOException {

		with_results();
		without_results();
	}

	private static void with_results() throws IOException, FileNotFoundException {
		List<Test_mutant_hitting> test_mutants = new ArrayList<>();
		List<Integer> mutants = new ArrayList<>();
		File a = new File(path_to_linear_csv);

		try (BufferedReader br = new BufferedReader(new FileReader(a))) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");

				Test_mutant_hitting the_test = null;
				boolean found = false;
				for (Test_mutant_hitting rec : test_mutants) {
					if (rec.testName.equals(values[0])) {
						the_test = rec;
						found = true;
						break;
					}
				}
				if (!found) {
					the_test = new Test_mutant_hitting(values[0]);
					String results="";
					for(int i=1;i<=66;i++) {
						results+=values[i]+",";
					}
					the_test.setResults(results);
					test_mutants.add(the_test);
				}

				for (int j = 67; j < values.length; j = j + 2) {

					if (values[j].isEmpty())
						continue;

					Integer mutant_hash = Integer.parseInt(values[j]);
					if (!mutants.contains(mutant_hash)) {
						mutants.add(mutant_hash);
					}
					Integer mutant_counter = Integer.parseInt(values[j + 1]);
					the_test.mutant_hitting.put(mutant_hash, mutant_counter);

				}
			}

		}

		String[][] matrix = new String[test_mutants.size()][mutants.size()];
		for (int i = 0; i < test_mutants.size(); i++) {
			for (int j = 0; j < mutants.size(); j++) {
				if (test_mutants.get(i).mutant_hitting.keySet().contains(mutants.get(j))) {
					matrix[i][j] = Integer.toString(test_mutants.get(i).mutant_hitting.get(mutants.get(j)));
				} else {
					matrix[i][j] = "";
				}
			}
		}

		BufferedWriter br = new BufferedWriter(new FileWriter(path_to_output_csv));
		StringBuilder sb = new StringBuilder();
		sb.append("benchmark,");
		for(int i=1;i<=60;i++) {
			sb.append("iteration_"+i+",");
		}
		sb.append("ci_original_lower"+",");
		sb.append("ci_original_upper"+",");
		sb.append("ci_mutant_lower"+",");
		sb.append("ci_mutant_upper"+",");
		sb.append("rci_lower"+",");
		sb.append("rci_upper"+",");
		for (Integer mutant : mutants) {
			sb.append("mutant#"+mutant+",");
		}
		sb.append("\n");
		for (int i = 0; i < test_mutants.size(); i++) {
			sb.append(test_mutants.get(i).testName + ","+test_mutants.get(i).results);
			for (int j = 0; j < mutants.size(); j++) {
				sb.append(matrix[i][j] + ",");
			}

			sb.append("\n");

		}
		br.write(sb.toString());
		br.close();
	}


	private static void without_results() throws IOException, FileNotFoundException {
		List<Test_mutant_hitting> test_mutants = new ArrayList<>();
		List<Integer> mutants = new ArrayList<>();
		File a = new File(path_to_linear_csv);

		try (BufferedReader br = new BufferedReader(new FileReader(a))) {
			String line;
			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");

				Test_mutant_hitting the_test = null;
				boolean found = false;
				for (Test_mutant_hitting rec : test_mutants) {
					if (rec.testName.equals(values[0])) {
						the_test = rec;
						found = true;
						break;
					}
				}
				if (!found) {
					the_test = new Test_mutant_hitting(values[0]);

					test_mutants.add(the_test);
				}

				for (int j = 1; j < values.length; j = j + 2) {

					if (values[j].isEmpty() || values[j+1].isEmpty())
						continue;

					Integer mutant_hash = Integer.parseInt(values[j]);
					if (!mutants.contains(mutant_hash)) {
						mutants.add(mutant_hash);
					}
					Integer mutant_counter = Integer.parseInt(values[j + 1]);
					the_test.mutant_hitting.put(mutant_hash, mutant_counter);

				}
			}

		}

		String[][] matrix = new String[test_mutants.size()][mutants.size()];
		for (int i = 0; i < test_mutants.size(); i++) {
			for (int j = 0; j < mutants.size(); j++) {
				if (test_mutants.get(i).mutant_hitting.keySet().contains(mutants.get(j))) {
					matrix[i][j] = Integer.toString(test_mutants.get(i).mutant_hitting.get(mutants.get(j)));
				} else {
					matrix[i][j] = "";
				}
			}
		}

		BufferedWriter br = new BufferedWriter(new FileWriter(path_to_output_csv));
		StringBuilder sb = new StringBuilder();
		sb.append("benchmark,");

		for (Integer mutant : mutants) {
			sb.append("mutant#"+mutant+",");
		}
		sb.append("\n");
		for (int i = 0; i < test_mutants.size(); i++) {
			sb.append(test_mutants.get(i).testName + ",");
			for (int j = 0; j < mutants.size(); j++) {
				sb.append(matrix[i][j] + ",");
			}

			sb.append("\n");

		}
		br.write(sb.toString());
		br.close();
	}
}
