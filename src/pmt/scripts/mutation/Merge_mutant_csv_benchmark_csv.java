package pmt.scripts.mutation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Merge_mutant_csv_benchmark_csv {

	public static String path_to_benchmark_result = "/Users/massi/Desktop/tmp/benchmark-result.csv";
	public static String path_to_mutant_hits = "/Users/massi/Desktop/tmp/mutant-hit.csv";
	public static String path_to_output_csv = "/Users/massi/Desktop/tmp/output.csv";

	public static void main(String[] args) throws IOException {
		merge_2_csv();

	}

	private static void merge_2_csv() throws IOException, FileNotFoundException {
		Map<String, String> test_and_mutants = new HashMap<>();

		File testResults = new File(path_to_benchmark_result);
		File mutantHit = new File(path_to_mutant_hits);

		try (BufferedReader brtestResults = new BufferedReader(new FileReader(testResults))) {
			try (BufferedReader brmutantHit = new BufferedReader(new FileReader(mutantHit))) {
				String line;
				while ((line = brmutantHit.readLine()) != null) {

					String[] values = line.split(",");

					test_and_mutants.put(values[0], line.replace(values[0] + ",", ""));

				}

				StringBuilder sb = new StringBuilder();
				while ((line = brtestResults.readLine()) != null) {

					String[] values = line.split(",");
					String testName = values[0].toString();

					for (String obj : test_and_mutants.keySet()) {

						if (testName.equals(obj.toString())) {
							sb.append(line + ",");
							sb.append(test_and_mutants.get(obj));
							sb.append("\n");
							break;

						}

					}

				}

				BufferedWriter br = new BufferedWriter(new FileWriter(path_to_output_csv));

				br.write(sb.toString());

				br.close();
			}
		}
	}

}
